package com.ruoyi.generator.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.base.BaseService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.domain.QGenTable;
import com.ruoyi.generator.repository.GenTableColumnRepository;
import com.ruoyi.generator.repository.GenTableRepository;
import com.ruoyi.generator.service.IGenTableService;
import com.ruoyi.generator.util.GenUtils;
import com.ruoyi.generator.util.VelocityInitializer;
import com.ruoyi.generator.util.VelocityUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 业务 服务层实现
 *
 * @author ruoyi
 */
@Service
public class GenTableServiceImpl extends BaseService implements IGenTableService {

    private static final String QUARTZ_TABLE_PREFIX = "qrtz_";
    private static final String GEN_TABLE_PREFIX = "gen_";
    private static final Logger log = LoggerFactory.getLogger(GenTableServiceImpl.class);

    @Autowired
    private GenTableRepository genTableRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GenTableColumnRepository genTableColumnRepository;

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public GenTable selectGenTableById(Long id) {
        GenTable genTable = genTableRepository.findById(id).get();
        setTableFromOptions(genTable);
        return genTable;
    }

    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    @Override
    public Page<GenTable> selectGenTableList(GenTable genTable, Pageable pageable) {
        return genTableRepository.findAll(getPredicate(genTable), pageable);
    }

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    public Page<GenTable> selectDbTableList(GenTable genTable, Pageable pageable) {
        String sql = " select table_name, table_comment, create_time, update_time from information_schema.tables " +
                " where table_schema = (select database()) " +
                " AND table_name NOT LIKE '"+QUARTZ_TABLE_PREFIX+"%' AND table_name NOT LIKE '"+GEN_TABLE_PREFIX+"%' " +
                " AND table_name NOT IN (select table_name from gen_table) ";
        if(StringUtils.isNotEmpty(genTable.getTableName())){
            sql += " AND lower(table_name) like lower(concat('%', " + genTable.getTableName()+ ", '%')) ";
        }
        if(StringUtils.isNotEmpty(genTable.getTableName())){
            sql += " AND lower(table_comment) like lower(concat('%', " + genTable.getTableComment() + ", '%'))";
        }
        String countSql = "select count(0) from ( " + sql + " ) t";
        if(pageable.isPaged()){
            int page = pageable.getPageNumber();
            int size = pageable.getPageSize();
            int start = 0;
            if(page > 0){
                start = page * size ;
            }
            sql += " limit " + start + "," + size;
        }
        List<GenTable> tables = jdbcTemplate.query(sql, new BeanPropertyRowMapper<GenTable>(GenTable.class));
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);
        return new PageImpl<GenTable>(tables, pageable, count);
    }

    private Predicate getPredicate(GenTable genTable){
        QGenTable qGenTable = QGenTable.genTable;
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(notStartWith(qGenTable.tableName, QUARTZ_TABLE_PREFIX));
        predicates.add(notStartWith(qGenTable.tableName, GEN_TABLE_PREFIX));
        if(StringUtils.isNotEmpty(genTable.getTableName())){
            predicates.add(buildLike(qGenTable.tableName, genTable.getTableName()));
        }
        if(StringUtils.isNotEmpty(genTable.getTableComment())){
            predicates.add(buildLike(qGenTable.tableComment, genTable.getTableComment()));
        }
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableListByNames(String[] tableNames) {
        String sql = "select table_name, table_comment, create_time, update_time from information_schema.tables " +
                " where table_name NOT LIKE '"+QUARTZ_TABLE_PREFIX+"%' and table_name NOT LIKE '"+GEN_TABLE_PREFIX+"%' and table_schema = (select database()) " +
                " and table_name in (";
        for(int i=0;i<tableNames.length;i++){
            sql += "'" +tableNames[i]+"'";
            if(i != tableNames.length - 1){
                sql += ",";
            }
        }
        sql += ")";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GenTable.class));
    }

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    @Override
    @Transactional
    public void updateGenTable(GenTable genTable) {
        String options = JSON.toJSONString(genTable.getParams());
        genTable.setOptions(options);
        genTableRepository.save(genTable);
        for (GenTableColumn cenTableColumn : genTable.getColumns()) {
            genTableColumnRepository.save(cenTableColumn);
        }
    }

    /**
     * 删除业务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public void deleteGenTableByIds(String ids) {
        for(Long id : Convert.toLongArray(ids)){
            genTableRepository.deleteById(id);
        }
    }

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     * @param operName  操作人员
     */
    @Override
    @Transactional
    public void importGenTable(List<GenTable> tableList, String operName) {
        for (GenTable table : tableList) {
            try {
                GenUtils.initTable(table, operName);
                List<GenTableColumn> columns = new ArrayList<>();
                String sql = "select column_name, (case when (is_nullable = 'no' && column_key != 'PRI') then '1' else null end) as is_required, (case when column_key = 'PRI' then '1' else '0' end) as is_pk, ordinal_position as sort, column_comment, (case when extra = 'auto_increment' then '1' else '0' end) as is_increment, column_type " +
                        " from information_schema.columns where table_schema = (select database()) and table_name =  '" + table.getTableName() + "'" +
                        " order by ordinal_position";
                List<GenTableColumn> genTableColumns = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GenTableColumn.class));
                for (GenTableColumn column : genTableColumns) {
                    GenUtils.initColumnField(column, table);
                    columns.add(column);
                }
                table.setColumns(columns);
                genTableRepository.save(table);
            } catch (Exception e) {
                log.error("表名 " + table.getTableName() + " 导入失败：", e);
            }
        }
    }

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    public Map<String, String> previewCode(Long tableId) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        GenTable table = genTableRepository.findById(tableId).get();
        // 查询列信息
        List<GenTableColumn> columns = table.getColumns();
        setPkColumn(table, columns);
        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(tableName, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            generatorCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        // 查询表信息
        GenTable table = genTableRepository.findFirstByTableName(tableName);
        // 查询列信息
        List<GenTableColumn> columns = table.getColumns();
        setPkColumn(table, columns);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, Constants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    public void validateEdit(GenTable genTable) {
        if (GenConstants.TPL_TREE.equals(genTable.getTplCategory())) {
            String options = JSON.toJSONString(genTable.getParams());
            JSONObject paramsObj = JSONObject.parseObject(options);
            if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_CODE))) {
                throw new BusinessException("树编码字段不能为空");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_PARENT_CODE))) {
                throw new BusinessException("树父编码字段不能为空");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_NAME))) {
                throw new BusinessException("树名称字段不能为空");
            }
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     * @param columns  业务字段列表
     */
    public void setPkColumn(GenTable table, List<GenTableColumn> columns) {
        for (GenTableColumn column : columns) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (StringUtils.isNull(table.getPkColumn())) {
            table.setPkColumn(columns.get(0));
        }
    }

    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTable genTable) {
        JSONObject paramsObj = JSONObject.parseObject(genTable.getOptions());
        if (StringUtils.isNotNull(paramsObj)) {
            String treeCode = paramsObj.getString(GenConstants.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenConstants.TREE_NAME);
            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
        }
    }
}
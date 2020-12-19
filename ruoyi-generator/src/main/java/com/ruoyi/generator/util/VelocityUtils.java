package com.ruoyi.generator.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.generator.config.GenConfig;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.system.domain.RelevTable;
import org.apache.velocity.VelocityContext;

import java.util.*;
import java.util.stream.Collectors;

public class VelocityUtils {

    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";

    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /**
     * html空间路径
     */
    private static final String TEMPLATES_PATH = "main/resources/templates";

    /**
     * 默认上级菜单，系统工具
     */
    private static final String DEFAULT_PARENT_MENU_ID = "3";

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable,List<RelevTable> lstRelevTb) {
        String moduleName = genTable.getModuleName();
        String businessName = genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getTplCategory();
        String functionName = genTable.getFunctionName();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getTplCategory());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
        velocityContext.put("moduleName", genTable.getModuleName());
        velocityContext.put("businessName", genTable.getBusinessName());
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        velocityContext.put("packageName", packageName);
        velocityContext.put("author", genTable.getFunctionAuthor());
        velocityContext.put("datetime", DateUtils.getDate());
        velocityContext.put("pkColumn", genTable.getPkColumn());
        velocityContext.put("dspRepeatColumn", genTable.getDspColumn());
        velocityContext.put("importList", getImportList(genTable));
        velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));

        velocityContext.put("billPrefix", genTable.getBillPrefix());  // 单据前缀


        // 取出页面需要的字段ing
        List<GenTableColumn> tempcolumns = genTable.getColumns();
        List<GenTableColumn> effectivecols = new ArrayList<GenTableColumn>();//定义一个list对象
        List<GenTableColumn> effectiveceditols = new ArrayList<GenTableColumn>();//定义一个list对象
        List<GenTableColumn> fieldcols = new ArrayList<GenTableColumn>();// 模板的变量   colums
        List<GenTableColumn> hiddenleftjoinfiledcols = new ArrayList<GenTableColumn>();// 模板隐藏变量 hiddenfiledcols

        Integer icount = 0 ;

            for (GenTableColumn tcolumn : tempcolumns) {

            if ( !StringUtil.isEmpty( tcolumn.getRelevEntity() ) && !tcolumn.isPk() ) {
                // 添加 关联字段 信息
                try {
                    // 根据关联字段 查找 ....
                    // RelevTable relevTb = null ; // lstRelevTb
                    List<RelevTable> lstcurRelevTb = lstRelevTb.stream().filter( s->s.getRelevEntity().equals(tcolumn.getRelevEntity()) ).collect(Collectors.toList());
                    //输出查找结果

                    tcolumn.setRelevEntityId( lstcurRelevTb.get(0).getRelevEntityId() );
                    tcolumn.setRelevEntityName( lstcurRelevTb.get(0).getRelevEntityName() );
                    tcolumn.setRelevTable(lstcurRelevTb.get(0).getRelevTable());
                    tcolumn.setRelevTableName(lstcurRelevTb.get(0).getRelevTableName());
                    tcolumn.setRelevTableId(lstcurRelevTb.get(0).getRelevTableId());

                    // 取得 关联实体信息
                    GenTableColumn relevColumn = (GenTableColumn) tcolumn.clone();
                    relevColumn.setJavaField( tcolumn.getJavaField().concat( relevColumn.getRelevEntityName() ));
                    fieldcols.add(relevColumn) ;

                    tcolumn.setIsRelevByHidden("1");
                    tcolumn.setColumnComment(tcolumn.getColumnComment().concat("ID"));
                    tcolumn.setRelevjavafiledname( tcolumn.getJavaField().concat( relevColumn.getRelevEntityName() ) );

                    icount ++ ;
                    tcolumn.setRelevAlias("a".concat(icount.toString()));
                    tcolumn.setRelevcolumnalias(tcolumn.getRelevAlias().concat(tcolumn.getRelevEntity().toLowerCase()));

                    hiddenleftjoinfiledcols.add(tcolumn) ;
                    fieldcols.add(tcolumn) ;

                    if (relevColumn.isInsert() && !relevColumn.isPk())
                        if (relevColumn.isUsableColumn() || !relevColumn.isSuperColumn())
                            effectivecols.add(relevColumn);

                    if (relevColumn.isEdit() && !relevColumn.isPk())
                        if (relevColumn.isUsableColumn() || !relevColumn.isSuperColumn())
                            effectiveceditols.add(relevColumn);

                }catch (Exception e){

                }

            } else {

                fieldcols.add(tcolumn) ;

                if (tcolumn.isInsert() && !tcolumn.isPk())
                    if (tcolumn.isUsableColumn() || !tcolumn.isSuperColumn())
                        effectivecols.add(tcolumn);

                if (tcolumn.isEdit() && !tcolumn.isPk())
                    if (tcolumn.isUsableColumn() || !tcolumn.isSuperColumn())
                        effectiveceditols.add(tcolumn);
            }
        };

            // 关联字段的 实体类
        Map<String,GenTableColumn> conctrolmodelsmap = new HashMap<>();
        for (GenTableColumn hcolumn : hiddenleftjoinfiledcols) {
            if (!conctrolmodelsmap.containsKey(hcolumn.getRelevEntity())) {
                conctrolmodelsmap.put(hcolumn.getRelevEntity(),hcolumn) ;
            }
        }

          //
        velocityContext.put("conctrolmodelsmap", conctrolmodelsmap);
        velocityContext.put("effectivecols", effectivecols);
        velocityContext.put("effectiveeditcols", effectiveceditols);
          // 在界面上 要隐藏的ID
        velocityContext.put("hiddenleftjoinfiledcols", hiddenleftjoinfiledcols);
        velocityContext.put("mappercols", genTable.getColumns() ); //
        velocityContext.put("columns", fieldcols ); // genTable.getColumns()

        velocityContext.put("table", genTable);
        setMenuVelocityContext(velocityContext, genTable);
        if (GenConstants.TPL_TREE.equals(tplCategory)) {
            setTreeVelocityContext(velocityContext, genTable);
        }
        if (GenConstants.TPL_SUB.equals(tplCategory)) {
            setSubVelocityContext(velocityContext, genTable);
        }
        return velocityContext;
    }

    public static void setMenuVelocityContext(VelocityContext context, GenTable genTable) {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String parentMenuId = getParentMenuId(paramsObj);
        context.put("parentMenuId", parentMenuId);
    }

    public static void setTreeVelocityContext(VelocityContext context, GenTable genTable) {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeCode = getTreecode(paramsObj);
        String treeParentCode = getTreeParentCode(paramsObj);
        String treeName = getTreeName(paramsObj);

        context.put("treeCode", treeCode);
        context.put("treeParentCode", treeParentCode);
        context.put("treeName", treeName);
        context.put("expandColumn", getExpandColumn(genTable));
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
            context.put("tree_parent_code", paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
            context.put("tree_name", paramsObj.getString(GenConstants.TREE_NAME));
        }
    }

    public static void setSubVelocityContext(VelocityContext context, GenTable genTable) {
        GenTable subTable = genTable.getSubTable();
        String subTableName = genTable.getSubTableName();
        String subTableFkName = genTable.getSubTableFkName();
        String subClassName = genTable.getSubTable().getClassName();
        String subTableFkClassName = StringUtils.convertToCamelCase(subTableFkName);

        context.put("subTable", subTable);
        context.put("subTableName", subTableName);
        context.put("subTableFkName", subTableFkName);
        context.put("subTableFkClassName", subTableFkClassName);
        context.put("subTableFkclassName", StringUtils.uncapitalize(subTableFkClassName));
        context.put("subClassName", subClassName);
        context.put("subclassName", StringUtils.uncapitalize(subClassName));
        context.put("subImportList", getImportList(genTable.getSubTable()));
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory) {
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
        if (GenConstants.TPL_CRUD.equals(tplCategory)) {
            templates.add("vm/html/list.html.vm");
        } else if (GenConstants.TPL_TREE.equals(tplCategory)) {
            templates.add("vm/html/tree.html.vm");
            templates.add("vm/html/list-tree.html.vm");
        } else if (GenConstants.TPL_SUB.equals(tplCategory)) {
            templates.add("vm/html/list.html.vm");
            templates.add("vm/java/sub-domain.java.vm");
        }
        templates.add("vm/html/add.html.vm");
        templates.add("vm/html/edit.html.vm");
        templates.add("vm/sql/sql.vm");
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenTable genTable) {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();
        // 模块名
        String moduleName = genTable.getModuleName();
        // 大写类名
        String className = genTable.getClassName();
        // 业务名称
        String businessName = genTable.getBusinessName();

        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
        String mybatisPath = MYBATIS_PATH + "/" + moduleName;
        String htmlPath = TEMPLATES_PATH + "/" + moduleName + "/" + businessName;

        if (template.contains("domain.java.vm")) {
            fileName = StringUtils.format("{}/domain/{}.java", javaPath, className);
        }
        if (template.contains("sub-domain.java.vm") && StringUtils.equals(GenConstants.TPL_SUB, genTable.getTplCategory())) {
            fileName = StringUtils.format("{}/domain/{}.java", javaPath, genTable.getSubTable().getClassName());
        } else if (template.contains("mapper.java.vm")) {
            fileName = StringUtils.format("{}/mapper/{}Mapper.java", javaPath, className);
        } else if (template.contains("service.java.vm")) {
            fileName = StringUtils.format("{}/service/I{}Service.java", javaPath, className);
        } else if (template.contains("serviceImpl.java.vm")) {
            fileName = StringUtils.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
        } else if (template.contains("controller.java.vm")) {
            fileName = StringUtils.format("{}/controller/{}Controller.java", javaPath, className);
        } else if (template.contains("mapper.xml.vm")) {
            fileName = StringUtils.format("{}/{}Mapper.xml", mybatisPath, className);
        } else if (template.contains("list.html.vm")) {
            fileName = StringUtils.format("{}/{}.html", htmlPath, businessName);
        } else if (template.contains("list-tree.html.vm")) {
            fileName = StringUtils.format("{}/{}.html", htmlPath, businessName);
        } else if (template.contains("tree.html.vm")) {
            fileName = StringUtils.format("{}/tree.html", htmlPath);
        } else if (template.contains("add.html.vm")) {
            fileName = StringUtils.format("{}/add.html", htmlPath);
        } else if (template.contains("edit.html.vm")) {
            fileName = StringUtils.format("{}/edit.html", htmlPath);
        } else if (template.contains("sql.vm")) {
            fileName = businessName + "Menu.sql";
        }
        return fileName;
    }

    /**
     * 获取项目文件路径
     *
     * @return 路径
     */
    public static String getProjectPath() {
        String packageName = GenConfig.getPackageName();
        StringBuffer projectPath = new StringBuffer();
        projectPath.append("main/java/");
        projectPath.append(packageName.replace(".", "/"));
        projectPath.append("/");
        return projectPath.toString();
    }

    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        String basePackage = StringUtils.substring(packageName, 0, lastIndex);
        return basePackage;
    }

    /**
     * 根据列类型获取导入包
     *
     * @param genTable 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();
        GenTable subGenTable = genTable.getSubTable();
        HashSet<String> importList = new HashSet<String>();
        if (StringUtils.isNotNull(subGenTable)) {
            importList.add("java.util.List");
        }
        for (GenTableColumn column : columns) {
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.util.Date");
            } else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 获取权限前缀
     *
     * @param moduleName   模块名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String businessName) {
        return StringUtils.format("{}:{}", moduleName, businessName);
    }

    /**
     * 获取上级菜单ID字段
     *
     * @param options 生成其他选项
     * @return 上级菜单ID字段
     */
    public static String getParentMenuId(JSONObject paramsObj) {
        if (StringUtils.isNotEmpty(paramsObj) && paramsObj.containsKey(GenConstants.PARENT_MENU_ID)) {
            return paramsObj.getString(GenConstants.PARENT_MENU_ID);
        }
        return DEFAULT_PARENT_MENU_ID;
    }

    /**
     * 获取树编码
     *
     * @param options 生成其他选项
     * @return 树编码
     */
    public static String getTreecode(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_CODE)) {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_CODE));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取树父编码
     *
     * @param options 生成其他选项
     * @return 树父编码
     */
    public static String getTreeParentCode(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取树名称
     *
     * @param options 生成其他选项
     * @return 树名称
     */
    public static String getTreeName(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_NAME));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取需要在哪一列上面显示展开按钮
     *
     * @param genTable 业务表对象
     * @return 展开按钮列序号
     */
    public static int getExpandColumn(GenTable genTable) {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeName = paramsObj.getString(GenConstants.TREE_NAME);
        int num = 0;
        for (GenTableColumn column : genTable.getColumns()) {
            if (column.isList()) {
                num++;
                String columnName = column.getColumnName();
                if (columnName.equals(treeName)) {
                    break;
                }
            }
        }
        return num;
    }
}
package com.ruoyi.generator.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.repository.GenTableColumnRepository;
import com.ruoyi.generator.service.IGenTableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务字段 服务层实现
 *
 * @author ruoyi
 */
@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService {
    @Autowired
    private GenTableColumnRepository genTableColumnRepository;

    /**
     * 查询业务字段列表
     *
     * @param genTable 业务字段信息
     * @return 业务字段集合
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(GenTable genTable) {
        return genTableColumnRepository.findByTable(genTable);
    }

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertGenTableColumn(GenTableColumn genTableColumn) {
        genTableColumnRepository.save(genTableColumn);
        return 1;
    }

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateGenTableColumn(GenTableColumn genTableColumn) {
        genTableColumnRepository.save(genTableColumn);
        return 1;
    }

    /**
     * 删除业务字段对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteGenTableColumnByIds(String ids) {
        for(Long id : Convert.toLongArray(ids)){
            genTableColumnRepository.deleteById(id);
        }
        return 1;
    }
}
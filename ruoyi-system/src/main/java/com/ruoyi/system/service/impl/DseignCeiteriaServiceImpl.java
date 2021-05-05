package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DseignCeiteriaMapper;
import com.ruoyi.system.domain.DseignCeiteria;
import com.ruoyi.system.service.IDseignCeiteriaService;
import com.ruoyi.common.core.text.Convert;

/**
 * 设计标准Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class DseignCeiteriaServiceImpl implements IDseignCeiteriaService 
{
    @Autowired
    private DseignCeiteriaMapper dseignCeiteriaMapper;

    /**
     * 查询设计标准
     * 
     * @param designStandardNumber 设计标准ID
     * @return 设计标准
     */
    @Override
    public DseignCeiteria selectDseignCeiteriaById(Long designStandardNumber)
    {
        return dseignCeiteriaMapper.selectDseignCeiteriaById(designStandardNumber);
    }

    /**
     * 查询设计标准列表
     * 
     * @param dseignCeiteria 设计标准
     * @return 设计标准
     */
    @Override
    public List<DseignCeiteria> selectDseignCeiteriaList(DseignCeiteria dseignCeiteria)
    {
        return dseignCeiteriaMapper.selectDseignCeiteriaList(dseignCeiteria);
    }

    /**
     * 新增设计标准
     * 
     * @param dseignCeiteria 设计标准
     * @return 结果
     */
    @Override
    public int insertDseignCeiteria(DseignCeiteria dseignCeiteria)
    {
        return dseignCeiteriaMapper.insertDseignCeiteria(dseignCeiteria);
    }

    /**
     * 修改设计标准
     * 
     * @param dseignCeiteria 设计标准
     * @return 结果
     */
    @Override
    public int updateDseignCeiteria(DseignCeiteria dseignCeiteria)
    {
        return dseignCeiteriaMapper.updateDseignCeiteria(dseignCeiteria);
    }

    /**
     * 删除设计标准对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDseignCeiteriaByIds(String ids)
    {
        return dseignCeiteriaMapper.deleteDseignCeiteriaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设计标准信息
     * 
     * @param designStandardNumber 设计标准ID
     * @return 结果
     */
    @Override
    public int deleteDseignCeiteriaById(Long designStandardNumber)
    {
        return dseignCeiteriaMapper.deleteDseignCeiteriaById(designStandardNumber);
    }
}

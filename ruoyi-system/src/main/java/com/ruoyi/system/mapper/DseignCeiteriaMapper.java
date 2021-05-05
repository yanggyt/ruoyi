package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DseignCeiteria;

/**
 * 设计标准Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface DseignCeiteriaMapper 
{
    /**
     * 查询设计标准
     * 
     * @param designStandardNumber 设计标准ID
     * @return 设计标准
     */
    public DseignCeiteria selectDseignCeiteriaById(Long designStandardNumber);

    /**
     * 查询设计标准列表
     * 
     * @param dseignCeiteria 设计标准
     * @return 设计标准集合
     */
    public List<DseignCeiteria> selectDseignCeiteriaList(DseignCeiteria dseignCeiteria);

    /**
     * 新增设计标准
     * 
     * @param dseignCeiteria 设计标准
     * @return 结果
     */
    public int insertDseignCeiteria(DseignCeiteria dseignCeiteria);

    /**
     * 修改设计标准
     * 
     * @param dseignCeiteria 设计标准
     * @return 结果
     */
    public int updateDseignCeiteria(DseignCeiteria dseignCeiteria);

    /**
     * 删除设计标准
     * 
     * @param designStandardNumber 设计标准ID
     * @return 结果
     */
    public int deleteDseignCeiteriaById(Long designStandardNumber);

    /**
     * 批量删除设计标准
     * 
     * @param designStandardNumbers 需要删除的数据ID
     * @return 结果
     */
    public int deleteDseignCeiteriaByIds(String[] designStandardNumbers);
}

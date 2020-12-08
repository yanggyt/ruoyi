package com.ruoyi.province.platform.mapper;

import java.util.List;
import com.ruoyi.province.platform.domain.EconType;

/**
 * 经济类型Mapper接口
 * 
 * @author dalin
 * @date 2020-12-08
 */
public interface EconTypeMapper 
{
    /**
     * 查询经济类型
     * 
     * @param econId 经济类型ID
     * @return 经济类型
     */
    public EconType selectEconTypeById(Integer econId);

    /**
     * 查询经济类型列表
     * 
     * @param econType 经济类型
     * @return 经济类型集合
     */
    public List<EconType> selectEconTypeList(EconType econType);

    /**
     * 新增经济类型
     * 
     * @param econType 经济类型
     * @return 结果
     */
    public int insertEconType(EconType econType);

    /**
     * 修改经济类型
     * 
     * @param econType 经济类型
     * @return 结果
     */
    public int updateEconType(EconType econType);

    /**
     * 删除经济类型
     * 
     * @param econId 经济类型ID
     * @return 结果
     */
    public int deleteEconTypeById(Integer econId);

    /**
     * 批量删除经济类型
     * 
     * @param econIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEconTypeByIds(String[] econIds);
}

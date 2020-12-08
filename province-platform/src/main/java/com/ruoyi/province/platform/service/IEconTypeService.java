package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.EconType;

/**
 * 经济类型Service接口
 * 
 * @author dalin
 * @date 2020-12-08
 */
public interface IEconTypeService 
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
     * 批量删除经济类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEconTypeByIds(String ids);

    /**
     * 删除经济类型信息
     * 
     * @param econId 经济类型ID
     * @return 结果
     */
    public int deleteEconTypeById(Integer econId);
}

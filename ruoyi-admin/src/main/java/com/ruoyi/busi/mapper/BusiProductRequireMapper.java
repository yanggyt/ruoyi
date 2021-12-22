package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiProductRequire;
import com.ruoyi.busi.domain.BusiMaterialRequire;

/**
 * 产品需求Mapper接口
 * 
 * @author WangCL
 * @date 2021-12-22
 */
public interface BusiProductRequireMapper 
{
    /**
     * 查询产品需求
     * 
     * @param id 产品需求主键
     * @return 产品需求
     */
    public BusiProductRequire selectBusiProductRequireById(String id);

    /**
     * 查询产品需求列表
     * 
     * @param busiProductRequire 产品需求
     * @return 产品需求集合
     */
    public List<BusiProductRequire> selectBusiProductRequireList(BusiProductRequire busiProductRequire);

    /**
     * 新增产品需求
     * 
     * @param busiProductRequire 产品需求
     * @return 结果
     */
    public int insertBusiProductRequire(BusiProductRequire busiProductRequire);

    /**
     * 修改产品需求
     * 
     * @param busiProductRequire 产品需求
     * @return 结果
     */
    public int updateBusiProductRequire(BusiProductRequire busiProductRequire);

    /**
     * 删除产品需求
     * 
     * @param id 产品需求主键
     * @return 结果
     */
    public int deleteBusiProductRequireById(String id);

    /**
     * 批量删除产品需求
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiProductRequireByIds(String[] ids);

    /**
     * 批量删除物料需求
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiMaterialRequireByProductRequireIds(String[] ids);
    
    /**
     * 批量新增物料需求
     * 
     * @param busiMaterialRequireList 物料需求列表
     * @return 结果
     */
    public int batchBusiMaterialRequire(List<BusiMaterialRequire> busiMaterialRequireList);
    

    /**
     * 通过产品需求主键删除物料需求信息
     * 
     * @param id 产品需求ID
     * @return 结果
     */
    public int deleteBusiMaterialRequireByProductRequireId(String id);
}

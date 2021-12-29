package com.ruoyi.busi.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.busi.domain.BusiProductRequire;

/**
 * 产品需求Service接口
 * 
 * @author WangCL
 * @date 2021-12-22
 */
public interface IBusiProductRequireService 
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
     * 批量删除产品需求
     * 
     * @param ids 需要删除的产品需求主键集合
     * @return 结果
     */
    public int deleteBusiProductRequireByIds(String ids);

    /**
     * 删除产品需求信息
     * 
     * @param id 产品需求主键
     * @return 结果
     */
    public int deleteBusiProductRequireById(String id);

    List<Map<String, String>> selMaterialTypeListByOrderId(String orderId);

    List<Map<String, String>> selMaterialColorListByOrderIdAndType(Map<String, String> queryMap);

    List<Map<String, String>> selMaterialTypeListByProductId(String productId);

    List<Map<String, String>> selMaterialColorListByProductIdAndType(Map<String, String> queryMap);
}

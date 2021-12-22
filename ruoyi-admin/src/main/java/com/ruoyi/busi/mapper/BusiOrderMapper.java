package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiOrder;
import com.ruoyi.busi.domain.BusiProductRequire;

/**
 * 订单Mapper接口
 * 
 * @author WangCL
 * @date 2021-12-21
 */
public interface BusiOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public BusiOrder selectBusiOrderById(String id);

    /**
     * 查询订单列表
     * 
     * @param busiOrder 订单
     * @return 订单集合
     */
    public List<BusiOrder> selectBusiOrderList(BusiOrder busiOrder);

    /**
     * 新增订单
     * 
     * @param busiOrder 订单
     * @return 结果
     */
    public int insertBusiOrder(BusiOrder busiOrder);

    /**
     * 修改订单
     * 
     * @param busiOrder 订单
     * @return 结果
     */
    public int updateBusiOrder(BusiOrder busiOrder);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteBusiOrderById(String id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiOrderByIds(String[] ids);

    /**
     * 批量删除产品需求
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiProductRequireByOrderIds(String[] ids);
    
    /**
     * 批量新增产品需求
     * 
     * @param busiProductRequireList 产品需求列表
     * @return 结果
     */
    public int batchBusiProductRequire(List<BusiProductRequire> busiProductRequireList);
    

    /**
     * 通过订单主键删除产品需求信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteBusiProductRequireByOrderId(String id);
}

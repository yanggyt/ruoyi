package com.ruoyi.busi.service;

import java.util.List;
import com.ruoyi.busi.domain.BusiOrder;

/**
 * 订单Service接口
 * 
 * @author WangCL
 * @date 2021-12-21
 */
public interface IBusiOrderService 
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
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteBusiOrderByIds(String ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteBusiOrderById(String id);
}

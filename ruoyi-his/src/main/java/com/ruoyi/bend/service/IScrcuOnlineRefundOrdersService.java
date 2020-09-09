package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.ScrcuOnlineRefundOrders;

/**
 * 退款订单Service接口
 * 
 * @author bend
 * @date 2020-09-01
 */
public interface IScrcuOnlineRefundOrdersService 
{
    /**
     * 查询退款订单
     * 
     * @param id 退款订单ID
     * @return 退款订单
     */
    public ScrcuOnlineRefundOrders selectScrcuOnlineRefundOrdersById(Long id);

    /**
     * 查询退款订单列表
     * 
     * @param scrcuOnlineRefundOrders 退款订单
     * @return 退款订单集合
     */
    public List<ScrcuOnlineRefundOrders> selectScrcuOnlineRefundOrdersList(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders);

    /**
     * 新增退款订单
     * 
     * @param scrcuOnlineRefundOrders 退款订单
     * @return 结果
     */
    public int insertScrcuOnlineRefundOrders(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders);

    /**
     * 修改退款订单
     * 
     * @param scrcuOnlineRefundOrders 退款订单
     * @return 结果
     */
    public int updateScrcuOnlineRefundOrders(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders);

    /**
     * 批量删除退款订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScrcuOnlineRefundOrdersByIds(String ids);

    /**
     * 删除退款订单信息
     * 
     * @param id 退款订单ID
     * @return 结果
     */
    public int deleteScrcuOnlineRefundOrdersById(Long id);
}

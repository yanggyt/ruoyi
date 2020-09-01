package com.ruoyi.bend.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.bend.domain.ScrcuOnlineRefundOrders;
import java.util.List;

/**
 * 退款订单Mapper接口
 * 
 * @author bend
 * @date 2020-09-01
 */
public interface ScrcuOnlineRefundOrdersMapper extends RuoYiBaseMapper<ScrcuOnlineRefundOrders>
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
     * 删除退款订单
     * 
     * @param id 退款订单ID
     * @return 结果
     */
    public int deleteScrcuOnlineRefundOrdersById(Long id);

    /**
     * 批量删除退款订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScrcuOnlineRefundOrdersByIds(String[] ids);
}

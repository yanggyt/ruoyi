package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.ScrcuOfflineRefundOrders;

/**
 * 扫码退款Service接口
 * 
 * @author bend
 * @date 2020-09-01
 */
public interface IScrcuOfflineRefundOrdersService 
{
    /**
     * 查询扫码退款
     * 
     * @param id 扫码退款ID
     * @return 扫码退款
     */
    public ScrcuOfflineRefundOrders selectScrcuOfflineRefundOrdersById(Long id);

    /**
     * 查询扫码退款列表
     * 
     * @param scrcuOfflineRefundOrders 扫码退款
     * @return 扫码退款集合
     */
    public List<ScrcuOfflineRefundOrders> selectScrcuOfflineRefundOrdersList(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders);

    /**
     * 新增扫码退款
     * 
     * @param scrcuOfflineRefundOrders 扫码退款
     * @return 结果
     */
    public int insertScrcuOfflineRefundOrders(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders);

    /**
     * 修改扫码退款
     * 
     * @param scrcuOfflineRefundOrders 扫码退款
     * @return 结果
     */
    public int updateScrcuOfflineRefundOrders(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders);

    /**
     * 批量删除扫码退款
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScrcuOfflineRefundOrdersByIds(String ids);

    /**
     * 删除扫码退款信息
     * 
     * @param id 扫码退款ID
     * @return 结果
     */
    public int deleteScrcuOfflineRefundOrdersById(Long id);
}

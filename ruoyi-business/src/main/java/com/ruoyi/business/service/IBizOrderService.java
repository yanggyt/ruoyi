package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizOrder;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2020-09-09
 */
public interface IBizOrderService 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public BizOrder selectBizOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param bizOrder 订单
     * @return 订单集合
     */
    public List<BizOrder> selectBizOrderList(BizOrder bizOrder);

    /**
     * 新增订单
     * 
     * @param bizOrder 订单
     * @return 结果
     */
    public int insertBizOrder(BizOrder bizOrder);

    /**
     * 修改订单
     * 
     * @param bizOrder 订单
     * @return 结果
     */
    public int updateBizOrder(BizOrder bizOrder);

    /**
     * 订单发货
     *
     * @param orderID 订单ID
     * @return 结果
     */
    public int deliverBizOrder(Long orderID);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizOrderByIds(String ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteBizOrderById(Long id);

    /**
     * 添加订单
     *
     * @param
     * @return AjaxResult
     */
    public AjaxResult orderAdd(Long memberID, Long productID, int productNum, Long addressID, String remark);

    /**
     * 订单收货
     *
     * @param
     * @return AjaxResult
     */
    public AjaxResult orderConfirm(Long userID, Long orderID);
}

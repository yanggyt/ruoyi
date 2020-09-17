package com.ruoyi.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.business.domain.*;
import com.ruoyi.business.mapper.BizMemberAddressMapper;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.business.mapper.BizProductMapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizOrderMapper;
import com.ruoyi.business.service.IBizOrderService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-09
 */
@Service
public class BizOrderServiceImpl implements IBizOrderService 
{
    @Autowired
    private BizOrderMapper bizOrderMapper;

    @Autowired
    private BizProductMapper bizProductMapper;

    @Autowired
    private BizMemberMapper bizMemberMapper;

    @Autowired
    private BizMemberAddressMapper bizMemberAddressMapper;

    @Autowired
    private BizAccountServiceImpl bizAccountService;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public BizOrder selectBizOrderById(Long id)
    {
        return bizOrderMapper.selectBizOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param bizOrder 订单
     * @return 订单
     */
    @Override
    public List<BizOrder> selectBizOrderList(BizOrder bizOrder)
    {
        return bizOrderMapper.selectBizOrderList(bizOrder);
    }

    /**
     * 新增订单
     * 
     * @param bizOrder 订单
     * @return 结果
     */
    @Override
    public int insertBizOrder(BizOrder bizOrder)
    {
        bizOrder.setCreateTime(DateUtils.getNowDate());
        return bizOrderMapper.insertBizOrder(bizOrder);
    }

    /**
     * 修改订单
     * 
     * @param bizOrder 订单
     * @return 结果
     */
    @Override
    public int updateBizOrder(BizOrder bizOrder)
    {
        bizOrder.setUpdateTime(DateUtils.getNowDate());
        return bizOrderMapper.updateBizOrder(bizOrder);
    }

    /**
     * 订单发货
     *
     * @param orderID 订单ID
     * @return 结果
     */
    @Override
    public int deliverBizOrder(Long orderID)
    {
        BizOrder bizOrder = bizOrderMapper.selectBizOrderById(orderID);
        if (bizOrder == null) {
            return 0;
        }
        //校验订单状态
        if (bizOrder.getOrderStatus() != BizOrder.STATUS_PAYED) {
            return 0;
        }
        bizOrder.setOrderStatus(BizOrder.STATUS_DELIVERY);
        bizOrder.setUpdateBy(ShiroUtils.getLoginName());
        bizOrder.setUpdateTime(new Date());
        return bizOrderMapper.updateBizOrder(bizOrder);
    }

    /**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizOrderByIds(String ids)
    {
        return bizOrderMapper.deleteBizOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteBizOrderById(Long id)
    {
        return bizOrderMapper.deleteBizOrderById(id);
    }

    /**
     * 添加订单
     *
     * @param
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult orderAdd(Long memberID, Long productID, int productNum, Long addressID, String remark)
    {
        if (productNum <= 0 || productNum > 99) {    //检测数量
            return AjaxResult.error("商品数目异常");
        }
        if (StringUtils.isEmpty(remark) || remark.length() > 30) {    //检测备注
            return AjaxResult.error("备注信息异常");
        }
        //取出商品
        BizProduct product = bizProductMapper.selectBizProductById(productID);
        if (product == null || product.getOnlineStatus() == 0) {    //检测上架
            return AjaxResult.error("该商品不存在");
        }
        //订单总价
        Long amount = product.getAmount();
        Long orderTotal = amount * productNum;
        //判断余额
        BizMember member = bizMemberMapper.selectBizMemberById(memberID);
        Long douBalance = member.getDouBalance();
        if (douBalance < orderTotal) {
            return AjaxResult.error("福豆余额不足");
        }
        Long cashbackAmount = product.getCashbackAmount() * productNum;
        //TODO cashbackAmount 专项划拨金额等级判断

        //判断地址
        BizMemberAddress address = bizMemberAddressMapper.selectBizMemberAddressById(addressID);
        if (address == null || !address.getMemberID().equals(memberID)) {
            return AjaxResult.error("收货地址不正确");
        }

        //创建订单
        BizOrder order = new BizOrder();
        order.setOrderSn("ORD" + DateUtils.getMilliTime());
        order.setMemberId(memberID);
        order.setMobile(address.getMobile());
        order.setMemberName(member.getMemberName());
        order.setOrderAmount(new BigDecimal(orderTotal));
        order.setOrderStatus(BizOrder.STATUS_PAYED);    //已支付
        order.setRemark(remark);
        order.setAddressDetail(address.getAddress());
        order.setAddressId(addressID);
        insertBizOrder(order);
        //订单详情
        BizOrderDetail orderDetail = new BizOrderDetail();
        orderDetail.setOrderId(order.getId());
        orderDetail.setOrderSn(order.getOrderSn());
        orderDetail.setProductId(productID);
        orderDetail.setProductCode(product.getProductName());
        orderDetail.setProductCount(productNum);
        orderDetail.setProductAmount(new BigDecimal(amount));
        bizOrderMapper.insertBizOrderDetail(orderDetail);
        //减去福豆余额账户
        boolean result = bizAccountService.accountChange(memberID, BizAccount.DOU_BALANCE, BizAccountDetail.DOU_DETAIL_TYPE_ORDER, -orderTotal, String.valueOf(order.getId()), BizAccountDetail.DOU_DESC_ORDER);
        if (!result) {
            return AjaxResult.error("扣款失败,请联系管理员");
        }
        //增加专项账户
        if(cashbackAmount > 0) {
            result = bizAccountService.accountChange(memberID, BizAccount.DOU_SPECIAL, BizAccountDetail.DOU_DETAIL_TYPE_CHARGE, cashbackAmount, String.valueOf(order.getId()), BizAccountDetail.DOU_DESC_SPECIAL1);
            if (!result) {
                return AjaxResult.error("扣款失败,请联系管理员");
            }
        }
        return AjaxResult.success();
    }

    /**
     * 订单收货
     *
     * @param
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult orderConfirm(Long userID, Long orderID)
    {
        BizOrder order = selectBizOrderById(orderID);
        //验证
        if (order == null || order.getMemberId() != userID || order.getOrderStatus() != BizOrder.STATUS_DELIVERY) {
            return AjaxResult.error("订单操作失败");
        }
        order.setOrderStatus(BizOrder.STATUS_COMPLETED);
        order.setUpdateBy(String.valueOf(userID));
        updateBizOrder(order);
        return AjaxResult.success();
    }
}

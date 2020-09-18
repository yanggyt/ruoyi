package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.*;
import com.ruoyi.business.mapper.BizMemberAddressMapper;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.business.mapper.BizOrderMapper;
import com.ruoyi.business.mapper.BizProductMapper;
import com.ruoyi.business.service.IBizOrderService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.utils.DictUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-09
 */
@Service
public class BizOrderServiceImpl implements IBizOrderService 
{
    @Resource
    private BizOrderMapper bizOrderMapper;

    @Resource
    private BizProductMapper bizProductMapper;

    @Resource
    private BizMemberMapper bizMemberMapper;

    @Resource
    private BizMemberAddressMapper bizMemberAddressMapper;

    @Resource
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
        BigDecimal amount = product.getAmount();
        BigDecimal orderTotal = amount.multiply(new BigDecimal(productNum));
        //判断余额
        BizMember member = bizMemberMapper.selectBizMemberById(memberID);
        Long douBalance = member.getDouBalance();
        if (douBalance < orderTotal.longValue()) {
            return AjaxResult.error("福豆余额不足");
        }
        BigDecimal cashbackAmount = product.getCashbackAmount().multiply(new BigDecimal(productNum));
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
        order.setOrderAmount(orderTotal);
        order.setOrderStatus(BizOrder.STATUS_PAYED);    //已支付
        order.setIsTeam(cashbackAmount.longValue() > 0 ? 1 : 0);  //是否团队福豆影响订单
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
        orderDetail.setProductAmount(amount);
        bizOrderMapper.insertBizOrderDetail(orderDetail);

        String businessCode = String.valueOf(order.getOrderSn());
        //减去福豆余额账户
        // TODO 类型不对，同步完数据后在修改
        boolean result = bizAccountService.accountChange(memberID, BizAccount.DOU_BALANCE, BizAccountDetail.DOU_DETAIL_TYPE_ORDER, -orderTotal.longValue(), businessCode, BizAccountDetail.DOU_DESC_ORDER);
        if (!result) {
            return AjaxResult.error("扣款失败,请联系管理员");
        }
        //增加专项账户
        if(cashbackAmount.longValue() > 0) {
            // TODO 类型不对，同步完数据后在修改
            result = bizAccountService.accountChange(memberID, BizAccount.DOU_SPECIAL, BizAccountDetail.DOU_DETAIL_TYPE_CHARGE, cashbackAmount.longValue(), businessCode, BizAccountDetail.DOU_DESC_SPECIAL1);
            if (!result) {
                return AjaxResult.error("扣款失败,请联系管理员");
            }

            //增加直推奖励(团队福豆账户)
            Long recMemberID = member.getRecommendId();
            if (recMemberID != null && recMemberID != 0) {
                //取出直推奖励金额
                String award1 = DictUtils.getDictLabel("busi_recommend_award", "1");
                result = bizAccountService.accountChange(memberID, BizAccount.DOU_TEAM, BizAccountDetail.DOU_DETAIL_TYPE_CHARGE, Long.parseLong(award1), businessCode, BizAccountDetail.DOU_DESC_RECOMM);
                if (!result) {
                    return AjaxResult.error("扣款失败,请联系管理员");
                }
                //判断二级直推(需要3个下级)
                BizMember recommendMember = bizMemberMapper.selectBizMemberSimple(recMemberID);
                Long topMemberID = recommendMember.getRecommendId();
                //判断有效下级数不少于三个
                if (bizMemberMapper.getValidChildCount(topMemberID) >= BizAccount.SECOND_AWARD_CHILD_LIMIT) {
                    String award2 = DictUtils.getDictLabel("busi_recommend_award", "2");
                    result = bizAccountService.accountChange(memberID, BizAccount.DOU_TEAM, BizAccountDetail.DOU_DETAIL_TYPE_CHARGE, Long.parseLong(award2), businessCode, BizAccountDetail.DOU_DESC_SECOND);
                    if (!result) {
                        return AjaxResult.error("扣款失败,请联系管理员");
                    }
                }
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
        if (order == null || !order.getMemberId().equals(userID) || order.getOrderStatus() != BizOrder.STATUS_DELIVERY) {
            return AjaxResult.error("订单操作失败");
        }
        order.setOrderStatus(BizOrder.STATUS_COMPLETED);
        order.setUpdateBy(String.valueOf(userID));
        updateBizOrder(order);
        return AjaxResult.success();
    }
}

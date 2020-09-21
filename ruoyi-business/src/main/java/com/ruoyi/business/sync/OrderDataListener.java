package com.ruoyi.business.sync;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import com.ruoyi.business.domain.*;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.business.mapper.BizOrderMapper;
import com.ruoyi.business.mapper.BizProductMapper;
import com.ruoyi.business.mapper.BizProductTypeMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class OrderDataListener extends AnalysisEventListener<OrderData> {

    private BizOrderMapper orderMapper;

    private BizMemberMapper memberMapper;

    public OrderDataListener(BizOrderMapper orderMapper, BizMemberMapper memberMapper) {
        this.orderMapper = orderMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    public void invoke(OrderData orderData, AnalysisContext analysisContext) {
        BizOrder order = new BizOrder();
        order.setId(Long.valueOf(orderData.getId()));
        order.setOrderSn(orderData.getOrderNumber());
        order.setMobile(orderData.getMobile());
        order.setOrderAmount(new BigDecimal(orderData.getPayCount()));
        order.setOrderStatus(Integer.parseInt(orderData.getOrderStatus()));
        order.setCreateBy("admin");
        order.setCreateTime(DateUtils.parseDate(orderData.getAddtime()));
        order.setAddressId(StringUtils.isBlank(orderData.getAddID()) ? 0L : Long.parseLong(orderData.getAddID()));
        order.setAddressDetail(orderData.getDetailsAddress());
        order.setRemark(orderData.getOrderRemark());

        BizMember member = memberMapper.selectBizMemberByMobile(order.getMobile());
        if (Objects.isNull(member)) {
            order.setMemberId(0L);
            order.setMemberName("");
        } else {
            order.setMemberId(member.getId());
            order.setMemberName(member.getMemberName());
        }
        orderMapper.insertBizOrder(order);

        BizOrderDetail orderDetail = new BizOrderDetail();
        orderDetail.setOrderId(order.getId());
        orderDetail.setOrderSn(order.getOrderSn());
        orderDetail.setProductAmount(new BigDecimal(orderData.getGoodsUnitPrice()));
        orderDetail.setProductCode(orderData.getGoodsId());
        orderDetail.setProductCount(Integer.parseInt(orderData.getBuyGoodsNums()));
        orderDetail.setCreateBy("admin");
        orderDetail.setCreateTime(order.getCreateTime());
        orderDetail.setProductId(0L);
        orderMapper.insertBizOrderDetail(orderDetail);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

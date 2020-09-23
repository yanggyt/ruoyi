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

    private BizProductMapper productMapper;

    public OrderDataListener(BizOrderMapper orderMapper, BizMemberMapper memberMapper, BizProductMapper productMapper) {
        this.orderMapper = orderMapper;
        this.memberMapper = memberMapper;
        this.productMapper = productMapper;
    }

    @Override
    public void invoke(OrderData orderData, AnalysisContext analysisContext) {
        BizOrder order = new BizOrder();
        order.setId(Long.valueOf(orderData.getId()));
        order.setOrderSn(orderData.getOrderNumber());
        order.setMobile(orderData.getUserTelephone());
        order.setOrderAmount(new BigDecimal(orderData.getPayCount()));

        int status;
        if ("2".equals(orderData.getOrderStatus())) {
            status = 3;
        } else if ("3".equals(orderData.getOrderStatus())) {
            status = 4;
        } else {
            status = Integer.parseInt(orderData.getOrderStatus());
        }
        order.setOrderStatus(status);
        order.setCreateBy("admin");
        order.setCreateTime(DateUtils.parseDate(orderData.getAddtime()));
        order.setAddressId(StringUtils.isBlank(orderData.getAddId()) ? 0L : Long.parseLong(orderData.getAddId()));
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

        BizProduct product = productMapper.selectBizProductByCode(orderData.getGoodsId());
        if (Objects.isNull(product)) {
            orderDetail.setProductId(0L);
        } else {
            orderDetail.setProductId(product.getId());
        }

        orderMapper.insertBizOrderDetail(orderDetail);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

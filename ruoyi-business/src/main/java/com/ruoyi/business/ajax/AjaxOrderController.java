package com.ruoyi.business.ajax;

import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.domain.BizMemberAddress;
import com.ruoyi.business.domain.BizOrder;
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.service.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ajax/order")
public class AjaxOrderController extends AuthController {

    @Autowired
    private IBizOrderService bizOrderService;

    @Autowired
    private IBizProductService bizProductService;

    @Autowired
    private IBizMemberService bizMemberService;

    @Autowired
    private IBizMemberAddressService bizMemberAddressService;


    //我的订单(status-1取全部订单)
    @PostMapping("/orderList")
    public AjaxResult orderList(Integer status)
    {
        Long userID = getUserID();
        BizOrder order = new BizOrder();
        order.setMemberId(userID);
        order.setOrderStatus(status);
        return AjaxResult.success(bizOrderService.selectBizOrderList(order));
    }

    //我的订单详情
    @PostMapping("/orderDetail")
    public AjaxResult orderDetail(Long orderID)
    {
        Long userID = getUserID();
        BizOrder order = bizOrderService.selectBizOrderById(orderID);
        if (order == null || userID != order.getMemberId()) {
            return AjaxResult.error();
        }
        return AjaxResult.success(order);
    }

    //订单结账
    @PostMapping("/orderConclude")
    public AjaxResult orderConclude(Long productID, Integer productNum)
    {
        Long userID = getUserID();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //取出福豆余额
        resultMap.put("douBalance", bizMemberService.selectBizMemberDou(userID, BizMember.DOU_BALANCE));
        //取出默认地址
        BizMemberAddress defaultAddress = bizMemberAddressService.selectDefaultAddressByMemberId(userID);
        resultMap.put("defaultAddress", defaultAddress);
        //取出商品
        BizProduct product = bizProductService.selectBizProductById(productID);
        if (product == null || product.getOnlineStatus() == 0) {    //检测上架
            return AjaxResult.error("该商品不存在");
        }
        resultMap.put("productName", product.getProductName());
        resultMap.put("productNum", productNum);
        resultMap.put("productPrice", product.getAmount());
        resultMap.put("orderPrice", product.getAmount() * productNum);
        return AjaxResult.success(resultMap);
    }

    //下订单
    @PostMapping("/orderAdd")
    public AjaxResult orderAdd(Long productID, Integer productNum, Long addressID, String remark)
    {
        Long userID = getUserID();


        return AjaxResult.success();
    }
}

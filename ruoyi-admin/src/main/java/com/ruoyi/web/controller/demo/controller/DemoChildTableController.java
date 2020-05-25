package com.ruoyi.web.controller.demo.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 父子表CRUD
 * @author ln
 * @version 2020-5-23
 */
@Controller
@RequestMapping("/demo/table/child")
public class DemoChildTableController  extends BaseController {

    private String prefix = "demo/table/child";
    private AtomicLong order_id = new AtomicLong(6);
    private AtomicLong good_id = new AtomicLong(10);

    private static List<ChildTableModel> orders = new ArrayList<>();
    {

        orders.add(new ChildTableModel(1L, "1000001", "测试1", "A店铺", "15888888888", "北京", new BigDecimal(150.0), "0"));
        orders.add(new ChildTableModel(2L, "1000002", "测试2", "A店铺", "15666666666", "北京", new BigDecimal(180.0), "1"));
        orders.add(new ChildTableModel(3L, "1000003", "测试3", "A店铺", "15666666666", "北京", new BigDecimal(110.0), "1"));
        orders.add(new ChildTableModel(4L, "1000004", "测试4", "A店铺", "15666666666", "北京", new BigDecimal(220.0), "1"));
        orders.add(new ChildTableModel(5L, "1000005", "测试5", "A店铺", "15666666666", "北京", new BigDecimal(140.0), "1"));
        orders.add(new ChildTableModel(6L, "1000006", "测试6", "A店铺", "15666666666", "北京", new BigDecimal(330.0), "1"));
    }

    private static List<Goods> goodsList = new ArrayList<>();
    {

        goodsList.add(new Goods(1L, "1000001", "1001", "小浣熊A", 1, new BigDecimal(150.0), "0"));
        goodsList.add(new Goods(2L, "1000002", "1002", "小浣熊A", 2, new BigDecimal(180.0), "1"));
        goodsList.add(new Goods(3L, "1000003", "1003", "小浣熊A", 3, new BigDecimal(110.0), "1"));
        goodsList.add(new Goods(4L, "1000004", "1004", "小浣熊A", 4, new BigDecimal(220.0), "1"));
        goodsList.add(new Goods(5L, "1000005", "1005", "小浣熊A", 5, new BigDecimal(140.0), "1"));
        goodsList.add(new Goods(6L, "1000006", "1006", "小浣熊A", 5, new BigDecimal(330.0), "1"));
        goodsList.add(new Goods(7L, "1000001", "1001", "小浣熊A", 1, new BigDecimal(150.0), "0"));
        goodsList.add(new Goods(8L, "1000001", "1001", "小浣熊A", 1, new BigDecimal(150.0), "0"));
        goodsList.add(new Goods(9L, "1000002", "1002", "小浣熊A", 2, new BigDecimal(180.0), "1"));
        goodsList.add(new Goods(10L, "1000002", "1002", "小浣熊A", 2, new BigDecimal(180.0), "1"));
    }

    /**
     * 表格父子视图
     */
    @GetMapping("/childTable")
    public String childTable()
    {
        return prefix + "/childTable";
    }

    /**
     * 查询数据
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChildTableModel userModel)
    {
        TableDataInfo rspData = new TableDataInfo();
        List<ChildTableModel> orderList = new ArrayList<>(Arrays.asList(new ChildTableModel[orders.size()]));
        Collections.copy(orderList, orders);

        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > orderList.size())
        {
            pageSize = orderList.size();
        }
        rspData.setRows(orderList.subList(pageNum, pageSize));
        rspData.setTotal(orderList.size());
        return rspData;
    }

    /**
     * 查询子表数据
     */
    @PostMapping("/childList")
    @ResponseBody
    public TableDataInfo childList(ChildTableModel userModel){

        List<Goods> goodsList1 = new ArrayList<>(Arrays.asList(new Goods[goodsList.size()]));
        Collections.copy(goodsList1, goodsList);
        // 查询条件过滤
        if (StringUtils.isNotEmpty(userModel.getOrderCode()))
        {
            goodsList1.clear();
            for (Goods goods : goodsList) {
                if (goods.getOrderCode().equals(userModel.getOrderCode()))
                {
                    goodsList1.add(goods);
                }
            }
        }
        return new TableDataInfo(goodsList1,goodsList1.size());
    }

    /**
     * 新增界面
     */
    @GetMapping("/addParent")
    public String addParent()
    {
        return prefix + "/addParent";
    }

    /**
     * 新增保存父表
     */
    @PostMapping("/addSaveParent")
    @ResponseBody
    public AjaxResult addSaveParent(ChildTableModel parent) {
        parent.setOrderId(order_id.incrementAndGet());
        return toAjax(orders.add(parent));
    }

    /**
     * 修改
     */
    @GetMapping("/editParent/{orderId}")
    public String editParent(@PathVariable("orderId") Long orderId, ModelMap mmap)
    {
        for (ChildTableModel order : orders) {
            if (orderId.equals(order.getOrderId())) {
                mmap.put("order", order);
            }
        }
        return prefix + "/editParent";
    }

    /**
     * 修改保存
     */
    @PostMapping("/editSaveParent")
    @ResponseBody
    public AjaxResult editSaveParent(@Validated ChildTableModel parent)
    {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(parent.getOrderId())) {
                orders.set(i, parent);
            }
        }
        return success();
    }

    /**
     * 删除父表
     */
    @PostMapping("/removeParent")
    @ResponseBody
    public AjaxResult removeParent(String ids)
    {
        Set<Long> idSet = new HashSet<>(Arrays.asList(Convert.toLongArray(ids)));
        for (ChildTableModel order : orders) {
            if (idSet.contains(order.getOrderId())) {
                for (Goods goods : goodsList) {
                    if (goods.getOrderCode().equals(order.getOrderCode())) {
                        return error("存在详情，禁止删除");
                    }
                }
            }
        }
        return toAjax(orders.removeIf(childTableModel -> idSet.contains(childTableModel.getOrderId())));
    }

    /**
     * 新增界面
     */
    @GetMapping("/addChild/{orderCode}")
    public String addChild(@PathVariable String orderCode, ModelMap mmap) {
        mmap.put("orderCode", orderCode);
        return prefix + "/addChild";
    }

    /**
     * 新增保存父表
     */
    @PostMapping("/addSaveChild")
    @ResponseBody
    public AjaxResult addSaveChild(Goods child) {
        child.setGoodId(good_id.incrementAndGet());
        return toAjax(goodsList.add(child));
    }

    /**
     * 修改
     */
    @GetMapping("/editChild/{goodsId}")
    public String editChild(@PathVariable("goodsId") Long goodsId, ModelMap mmap)
    {
        for (Goods goods : goodsList) {
            if (goodsId.equals(goods.getGoodId())) {
                mmap.put("goods", goods);
            }
        }
        return prefix + "/editChild";
    }

    /**
     * 修改保存
     */
    @PostMapping("/editChild")
    @ResponseBody
    public AjaxResult editSaveChild(@Validated Goods child)
    {
        for (int i = 0; i < goodsList.size(); i++) {
            if (goodsList.get(i).getGoodId().equals(child.getGoodId())) {
                goodsList.set(i, child);
            }
        }
        return success();
    }

    /**
     * 删除父表
     */
    @PostMapping("/removeChild")
    @ResponseBody
    public AjaxResult removeChild(String id)
    {
        return toAjax(goodsList.removeIf(goods -> Long.parseLong(id) == goods.getGoodId()));
    }

}


class ChildTableModel
{
    /** 订单ID */
    private Long orderId;

    /** 订单编号 */
    private String orderCode;

    /** 订单名称 */
    private String orderName;

    /** 商家名称 */
    private String merchantName;

    /** 用户手机 */
    private String userPhone;

    /** 用户地址 */
    private String userAddress;

    /** 总金额 */
    private BigDecimal amount;

    /** 用户状态（0正常 1停用） */
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public ChildTableModel(Long orderId, String orderCode, String orderName, String merchantName, String userPhone, String userAddress, BigDecimal amount, String status) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.orderName = orderName;
        this.merchantName = merchantName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.amount = amount;
        this.status = status;
        this.createTime = DateUtils.getNowDate();;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orderId", orderId)
                .append("orderCode", orderCode)
                .append("orderName", orderName)
                .append("merchantName", merchantName)
                .append("userPhone", userPhone)
                .append("userAddress", userAddress)
                .append("amount", amount)
                .append("status", status)
                .append("createTime", createTime)
                .toString();
    }
}

class Goods {

    private Long goodId;

    private String goodCode;

    private String goodName;

    private int num;

    private BigDecimal amount;

    private String orderCode;

    private String status;

    public Goods() {
    }

    public Goods(Long goodId, String orderCode, String goodCode, String goodName, int num, BigDecimal amount, String status) {
        this.goodId = goodId;
        this.orderCode = orderCode;
        this.goodCode = goodCode;
        this.goodName = goodName;
        this.num = num;
        this.amount = amount;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("goodId", goodId)
                .append("goodCode", goodCode)
                .append("goodName", goodName)
                .append("num", num)
                .append("amount", amount)
                .append("orderCode", orderCode)
                .append("status", status)
                .toString();
    }
}

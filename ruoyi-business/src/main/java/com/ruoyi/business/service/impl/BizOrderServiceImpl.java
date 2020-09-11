package com.ruoyi.business.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizOrderMapper;
import com.ruoyi.business.domain.BizOrder;
import com.ruoyi.business.service.IBizOrderService;
import com.ruoyi.common.core.text.Convert;

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
}

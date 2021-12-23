package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.busi.domain.BusiProductRequire;
import com.ruoyi.busi.mapper.BusiOrderMapper;
import com.ruoyi.busi.domain.BusiOrder;
import com.ruoyi.busi.service.IBusiOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 订单Service业务层处理
 * 
 * @author WangCL
 * @date 2021-12-21
 */
@Service
public class BusiOrderServiceImpl implements IBusiOrderService 
{
    @Autowired
    private BusiOrderMapper busiOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public BusiOrder selectBusiOrderById(String id)
    {
        return busiOrderMapper.selectBusiOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param busiOrder 订单
     * @return 订单
     */
    @Override
    public List<BusiOrder> selectBusiOrderList(BusiOrder busiOrder)
    {
        return busiOrderMapper.selectBusiOrderList(busiOrder);
    }

    /**
     * 新增订单
     * 
     * @param busiOrder 订单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBusiOrder(BusiOrder busiOrder)
    {
        busiOrder.setCreateTime(DateUtils.getNowDate());
        int rows = busiOrderMapper.insertBusiOrder(busiOrder);
        insertBusiProductRequire(busiOrder);
        return rows;
    }

    /**
     * 修改订单
     * 
     * @param busiOrder 订单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBusiOrder(BusiOrder busiOrder)
    {
        busiOrder.setUpdateTime(DateUtils.getNowDate());
        // 更新时，由于会导致产品需求ID变更，所以这里不在更新产品需求。
//        busiOrderMapper.deleteBusiProductRequireByOrderId(busiOrder.getId());
//        insertBusiProductRequire(busiOrder);
        return busiOrderMapper.updateBusiOrder(busiOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBusiOrderByIds(String ids)
    {
        busiOrderMapper.deleteBusiProductRequireByOrderIds(Convert.toStrArray(ids));
        return busiOrderMapper.deleteBusiOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteBusiOrderById(String id)
    {
        busiOrderMapper.deleteBusiProductRequireByOrderId(id);
        return busiOrderMapper.deleteBusiOrderById(id);
    }

    /**
     * 新增产品需求信息
     * 
     * @param busiOrder 订单对象
     */
    public void insertBusiProductRequire(BusiOrder busiOrder)
    {
        List<BusiProductRequire> busiProductRequireList = busiOrder.getBusiProductRequireList();
        String id = busiOrder.getId();
        if (StringUtils.isNotNull(busiProductRequireList))
        {
            List<BusiProductRequire> list = new ArrayList<BusiProductRequire>();
            for (BusiProductRequire busiProductRequire : busiProductRequireList)
            {
                busiProductRequire.setOrderId(id);
                list.add(busiProductRequire);
            }
            if (list.size() > 0)
            {
                busiOrderMapper.batchBusiProductRequire(list);
            }
        }
    }
}

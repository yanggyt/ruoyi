package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.ScrcuOnlineRefundOrdersMapper;
import com.ruoyi.bend.domain.ScrcuOnlineRefundOrders;
import com.ruoyi.bend.service.IScrcuOnlineRefundOrdersService;
import com.ruoyi.common.core.text.Convert;

/**
 * 退款订单Service业务层处理
 * 
 * @author bend
 * @date 2020-09-01
 */
@Service
public class ScrcuOnlineRefundOrdersServiceImpl implements IScrcuOnlineRefundOrdersService 
{
    @Autowired
    private ScrcuOnlineRefundOrdersMapper scrcuOnlineRefundOrdersMapper;

    /**
     * 查询退款订单
     * 
     * @param id 退款订单ID
     * @return 退款订单
     */
    @Override
    public ScrcuOnlineRefundOrders selectScrcuOnlineRefundOrdersById(Long id)
    {
        return scrcuOnlineRefundOrdersMapper.selectScrcuOnlineRefundOrdersById(id);
    }

    /**
     * 查询退款订单列表
     * 
     * @param scrcuOnlineRefundOrders 退款订单
     * @return 退款订单
     */
    @Override
    public List<ScrcuOnlineRefundOrders> selectScrcuOnlineRefundOrdersList(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders)
    {
        return scrcuOnlineRefundOrdersMapper.selectScrcuOnlineRefundOrdersList(scrcuOnlineRefundOrders);
    }

    /**
     * 新增退款订单
     * 
     * @param scrcuOnlineRefundOrders 退款订单
     * @return 结果
     */
    @Override
    public int insertScrcuOnlineRefundOrders(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders)
    {
        scrcuOnlineRefundOrders.setCreateTime(DateUtils.getNowDate());
        return scrcuOnlineRefundOrdersMapper.insertScrcuOnlineRefundOrders(scrcuOnlineRefundOrders);
    }

    /**
     * 修改退款订单
     * 
     * @param scrcuOnlineRefundOrders 退款订单
     * @return 结果
     */
    @Override
    public int updateScrcuOnlineRefundOrders(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders)
    {
        return scrcuOnlineRefundOrdersMapper.updateScrcuOnlineRefundOrders(scrcuOnlineRefundOrders);
    }

    /**
     * 删除退款订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOnlineRefundOrdersByIds(String ids)
    {
        return scrcuOnlineRefundOrdersMapper.deleteScrcuOnlineRefundOrdersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退款订单信息
     * 
     * @param id 退款订单ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOnlineRefundOrdersById(Long id)
    {
        return scrcuOnlineRefundOrdersMapper.deleteScrcuOnlineRefundOrdersById(id);
    }
}

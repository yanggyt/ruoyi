package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.ScrcuOfflineOrdersMapper;
import com.ruoyi.bend.domain.ScrcuOfflineOrders;
import com.ruoyi.bend.service.IScrcuOfflineOrdersService;
import com.ruoyi.common.core.text.Convert;

/**
 * 线下订单Service业务层处理
 * 
 * @author bend
 * @date 2020-09-01
 */
@Service
public class ScrcuOfflineOrdersServiceImpl implements IScrcuOfflineOrdersService 
{
    @Autowired
    private ScrcuOfflineOrdersMapper scrcuOfflineOrdersMapper;

    /**
     * 查询线下订单
     * 
     * @param id 线下订单ID
     * @return 线下订单
     */
    @Override
    public ScrcuOfflineOrders selectScrcuOfflineOrdersById(Long id)
    {
        return scrcuOfflineOrdersMapper.selectScrcuOfflineOrdersById(id);
    }

    /**
     * 查询线下订单列表
     * 
     * @param scrcuOfflineOrders 线下订单
     * @return 线下订单
     */
    @Override
    public List<ScrcuOfflineOrders> selectScrcuOfflineOrdersList(ScrcuOfflineOrders scrcuOfflineOrders)
    {
        return scrcuOfflineOrdersMapper.selectScrcuOfflineOrdersList(scrcuOfflineOrders);
    }

    /**
     * 新增线下订单
     * 
     * @param scrcuOfflineOrders 线下订单
     * @return 结果
     */
    @Override
    public int insertScrcuOfflineOrders(ScrcuOfflineOrders scrcuOfflineOrders)
    {
        scrcuOfflineOrders.setCreateTime(DateUtils.getNowDate());
        return scrcuOfflineOrdersMapper.insertScrcuOfflineOrders(scrcuOfflineOrders);
    }

    /**
     * 修改线下订单
     * 
     * @param scrcuOfflineOrders 线下订单
     * @return 结果
     */
    @Override
    public int updateScrcuOfflineOrders(ScrcuOfflineOrders scrcuOfflineOrders)
    {
        scrcuOfflineOrders.setUpdateTime(DateUtils.getNowDate());
        return scrcuOfflineOrdersMapper.updateScrcuOfflineOrders(scrcuOfflineOrders);
    }

    /**
     * 删除线下订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOfflineOrdersByIds(String ids)
    {
        return scrcuOfflineOrdersMapper.deleteScrcuOfflineOrdersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除线下订单信息
     * 
     * @param id 线下订单ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOfflineOrdersById(Long id)
    {
        return scrcuOfflineOrdersMapper.deleteScrcuOfflineOrdersById(id);
    }
}

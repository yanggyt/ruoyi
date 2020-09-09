package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.ScrcuOfflineRefundOrdersMapper;
import com.ruoyi.bend.domain.ScrcuOfflineRefundOrders;
import com.ruoyi.bend.service.IScrcuOfflineRefundOrdersService;
import com.ruoyi.common.core.text.Convert;

/**
 * 扫码退款Service业务层处理
 * 
 * @author bend
 * @date 2020-09-01
 */
@Service
public class ScrcuOfflineRefundOrdersServiceImpl implements IScrcuOfflineRefundOrdersService 
{
    @Autowired
    private ScrcuOfflineRefundOrdersMapper scrcuOfflineRefundOrdersMapper;

    /**
     * 查询扫码退款
     * 
     * @param id 扫码退款ID
     * @return 扫码退款
     */
    @Override
    public ScrcuOfflineRefundOrders selectScrcuOfflineRefundOrdersById(Long id)
    {
        return scrcuOfflineRefundOrdersMapper.selectScrcuOfflineRefundOrdersById(id);
    }

    /**
     * 查询扫码退款列表
     * 
     * @param scrcuOfflineRefundOrders 扫码退款
     * @return 扫码退款
     */
    @Override
    public List<ScrcuOfflineRefundOrders> selectScrcuOfflineRefundOrdersList(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders)
    {
        return scrcuOfflineRefundOrdersMapper.selectScrcuOfflineRefundOrdersList(scrcuOfflineRefundOrders);
    }

    /**
     * 新增扫码退款
     * 
     * @param scrcuOfflineRefundOrders 扫码退款
     * @return 结果
     */
    @Override
    public int insertScrcuOfflineRefundOrders(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders)
    {
        scrcuOfflineRefundOrders.setCreateTime(DateUtils.getNowDate());
        return scrcuOfflineRefundOrdersMapper.insertScrcuOfflineRefundOrders(scrcuOfflineRefundOrders);
    }

    /**
     * 修改扫码退款
     * 
     * @param scrcuOfflineRefundOrders 扫码退款
     * @return 结果
     */
    @Override
    public int updateScrcuOfflineRefundOrders(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders)
    {
        return scrcuOfflineRefundOrdersMapper.updateScrcuOfflineRefundOrders(scrcuOfflineRefundOrders);
    }

    /**
     * 删除扫码退款对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOfflineRefundOrdersByIds(String ids)
    {
        return scrcuOfflineRefundOrdersMapper.deleteScrcuOfflineRefundOrdersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除扫码退款信息
     * 
     * @param id 扫码退款ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOfflineRefundOrdersById(Long id)
    {
        return scrcuOfflineRefundOrdersMapper.deleteScrcuOfflineRefundOrdersById(id);
    }
}

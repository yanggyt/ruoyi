package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.bend.domain.ScrcuOnlineOrderDetails;
import com.ruoyi.bend.mapper.ScrcuOnlineOrdersMapper;
import com.ruoyi.bend.domain.ScrcuOnlineOrders;
import com.ruoyi.bend.service.IScrcuOnlineOrdersService;
import com.ruoyi.common.core.text.Convert;

/**
 * 收单列表Service业务层处理
 * 
 * @author bend
 * @date 2020-09-01
 */
@Service
public class ScrcuOnlineOrdersServiceImpl implements IScrcuOnlineOrdersService 
{
    @Autowired
    private ScrcuOnlineOrdersMapper scrcuOnlineOrdersMapper;

    /**
     * 查询收单列表
     * 
     * @param id 收单列表ID
     * @return 收单列表
     */
    @Override
    public ScrcuOnlineOrders selectScrcuOnlineOrdersById(Long id)
    {
        return scrcuOnlineOrdersMapper.selectScrcuOnlineOrdersById(id);
    }

    /**
     * 查询收单列表列表
     * 
     * @param scrcuOnlineOrders 收单列表
     * @return 收单列表
     */
    @Override
    public List<ScrcuOnlineOrders> selectScrcuOnlineOrdersList(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        return scrcuOnlineOrdersMapper.selectScrcuOnlineOrdersList(scrcuOnlineOrders);
    }

    /**
     * 新增收单列表
     * 
     * @param scrcuOnlineOrders 收单列表
     * @return 结果
     */
    @Transactional
    @Override
    public int insertScrcuOnlineOrders(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        scrcuOnlineOrders.setCreateTime(DateUtils.getNowDate());
        int rows = scrcuOnlineOrdersMapper.insertScrcuOnlineOrders(scrcuOnlineOrders);
        insertScrcuOnlineOrderDetails(scrcuOnlineOrders);
        return rows;
    }

    /**
     * 修改收单列表
     * 
     * @param scrcuOnlineOrders 收单列表
     * @return 结果
     */
    @Transactional
    @Override
    public int updateScrcuOnlineOrders(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        scrcuOnlineOrders.setUpdateTime(DateUtils.getNowDate());
        scrcuOnlineOrdersMapper.deleteScrcuOnlineOrderDetailsByOrderNumber(scrcuOnlineOrders.getId());
        insertScrcuOnlineOrderDetails(scrcuOnlineOrders);
        return scrcuOnlineOrdersMapper.updateScrcuOnlineOrders(scrcuOnlineOrders);
    }

    /**
     * 删除收单列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteScrcuOnlineOrdersByIds(String ids)
    {
        scrcuOnlineOrdersMapper.deleteScrcuOnlineOrderDetailsByOrderNumbers(Convert.toStrArray(ids));
        return scrcuOnlineOrdersMapper.deleteScrcuOnlineOrdersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收单列表信息
     * 
     * @param id 收单列表ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOnlineOrdersById(Long id)
    {
        scrcuOnlineOrdersMapper.deleteScrcuOnlineOrderDetailsByOrderNumber(id);
        return scrcuOnlineOrdersMapper.deleteScrcuOnlineOrdersById(id);
    }

    /**
     * 新增收单详情信息
     * 
     * @param scrcuOnlineOrders 收单列表对象
     */
    public void insertScrcuOnlineOrderDetails(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        List<ScrcuOnlineOrderDetails> scrcuOnlineOrderDetailsList = scrcuOnlineOrders.getScrcuOnlineOrderDetailsList();
        Long id = scrcuOnlineOrders.getId();
        if (StringUtils.isNotNull(scrcuOnlineOrderDetailsList))
        {
            List<ScrcuOnlineOrderDetails> list = new ArrayList<ScrcuOnlineOrderDetails>();
            for (ScrcuOnlineOrderDetails scrcuOnlineOrderDetails : scrcuOnlineOrderDetailsList)
            {
                scrcuOnlineOrderDetails.setId(id);
                list.add(scrcuOnlineOrderDetails);
            }
            if (list.size() > 0)
            {
                scrcuOnlineOrdersMapper.batchScrcuOnlineOrderDetails(list);
            }
        }
    }

    /**
     * 查询在线收单
     *
     * @param scrcuOnlineOrders 在线收单ID
     * @return 在线收单
     */
    @Override
    public ScrcuOnlineOrders selectScrcuOnlineOrders(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        return scrcuOnlineOrdersMapper.selectOne(scrcuOnlineOrders);
    }
}

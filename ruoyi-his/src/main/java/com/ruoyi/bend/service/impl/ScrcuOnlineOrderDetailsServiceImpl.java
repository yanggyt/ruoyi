package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.ScrcuOnlineOrderDetailsMapper;
import com.ruoyi.bend.domain.ScrcuOnlineOrderDetails;
import com.ruoyi.bend.service.IScrcuOnlineOrderDetailsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 收单详情Service业务层处理
 * 
 * @author bend
 * @date 2020-09-01
 */
@Service
public class ScrcuOnlineOrderDetailsServiceImpl implements IScrcuOnlineOrderDetailsService 
{
    @Autowired
    private ScrcuOnlineOrderDetailsMapper scrcuOnlineOrderDetailsMapper;

    /**
     * 查询收单详情
     * 
     * @param id 收单详情ID
     * @return 收单详情
     */
    @Override
    public ScrcuOnlineOrderDetails selectScrcuOnlineOrderDetailsById(Long id)
    {
        return scrcuOnlineOrderDetailsMapper.selectScrcuOnlineOrderDetailsById(id);
    }

    /**
     * 查询收单详情列表
     * 
     * @param scrcuOnlineOrderDetails 收单详情
     * @return 收单详情
     */
    @Override
    public List<ScrcuOnlineOrderDetails> selectScrcuOnlineOrderDetailsList(ScrcuOnlineOrderDetails scrcuOnlineOrderDetails)
    {
        return scrcuOnlineOrderDetailsMapper.selectScrcuOnlineOrderDetailsList(scrcuOnlineOrderDetails);
    }

    /**
     * 新增收单详情
     * 
     * @param scrcuOnlineOrderDetails 收单详情
     * @return 结果
     */
    @Override
    public int insertScrcuOnlineOrderDetails(ScrcuOnlineOrderDetails scrcuOnlineOrderDetails)
    {
        scrcuOnlineOrderDetails.setCreateTime(DateUtils.getNowDate());
        return scrcuOnlineOrderDetailsMapper.insertScrcuOnlineOrderDetails(scrcuOnlineOrderDetails);
    }

    /**
     * 修改收单详情
     * 
     * @param scrcuOnlineOrderDetails 收单详情
     * @return 结果
     */
    @Override
    public int updateScrcuOnlineOrderDetails(ScrcuOnlineOrderDetails scrcuOnlineOrderDetails)
    {
        return scrcuOnlineOrderDetailsMapper.updateScrcuOnlineOrderDetails(scrcuOnlineOrderDetails);
    }

    /**
     * 删除收单详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOnlineOrderDetailsByIds(String ids)
    {
        return scrcuOnlineOrderDetailsMapper.deleteScrcuOnlineOrderDetailsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收单详情信息
     * 
     * @param id 收单详情ID
     * @return 结果
     */
    @Override
    public int deleteScrcuOnlineOrderDetailsById(Long id)
    {
        return scrcuOnlineOrderDetailsMapper.deleteScrcuOnlineOrderDetailsById(id);
    }
}

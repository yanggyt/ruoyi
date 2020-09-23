package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizCashInfoMapper;
import com.ruoyi.business.domain.BizCashInfo;
import com.ruoyi.business.service.IBizCashInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 兑现申请记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-23
 */
@Service
public class BizCashInfoServiceImpl implements IBizCashInfoService 
{
    @Autowired
    private BizCashInfoMapper bizCashInfoMapper;

    /**
     * 查询兑现申请记录
     * 
     * @param id 兑现申请记录ID
     * @return 兑现申请记录
     */
    @Override
    public BizCashInfo selectBizCashInfoById(Long id)
    {
        return bizCashInfoMapper.selectBizCashInfoById(id);
    }

    /**
     * 查询兑现申请记录列表
     * 
     * @param bizCashInfo 兑现申请记录
     * @return 兑现申请记录
     */
    @Override
    public List<BizCashInfo> selectBizCashInfoList(BizCashInfo bizCashInfo)
    {
        return bizCashInfoMapper.selectBizCashInfoList(bizCashInfo);
    }

    /**
     * 新增兑现申请记录
     * 
     * @param bizCashInfo 兑现申请记录
     * @return 结果
     */
    @Override
    public int insertBizCashInfo(BizCashInfo bizCashInfo)
    {
        bizCashInfo.setCreateTime(DateUtils.getNowDate());
        return bizCashInfoMapper.insertBizCashInfo(bizCashInfo);
    }

    /**
     * 修改兑现申请记录
     * 
     * @param bizCashInfo 兑现申请记录
     * @return 结果
     */
    @Override
    public int updateBizCashInfo(BizCashInfo bizCashInfo)
    {
        bizCashInfo.setUpdateTime(DateUtils.getNowDate());
        return bizCashInfoMapper.updateBizCashInfo(bizCashInfo);
    }

    /**
     * 删除兑现申请记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizCashInfoByIds(String ids)
    {
        return bizCashInfoMapper.deleteBizCashInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除兑现申请记录信息
     * 
     * @param id 兑现申请记录ID
     * @return 结果
     */
    @Override
    public int deleteBizCashInfoById(Long id)
    {
        return bizCashInfoMapper.deleteBizCashInfoById(id);
    }
}

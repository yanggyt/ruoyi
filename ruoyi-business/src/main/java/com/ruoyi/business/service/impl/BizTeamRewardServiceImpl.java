package com.ruoyi.business.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizTeamRewardMapper;
import com.ruoyi.business.domain.BizTeamReward;
import com.ruoyi.business.service.IBizTeamRewardService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 团队奖励明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-22
 */
@Service
public class BizTeamRewardServiceImpl implements IBizTeamRewardService 
{
    @Resource
    private BizTeamRewardMapper bizTeamRewardMapper;

    /**
     * 查询团队奖励明细
     * 
     * @param id 团队奖励明细ID
     * @return 团队奖励明细
     */
    @Override
    public BizTeamReward selectBizTeamRewardById(Long id)
    {
        return bizTeamRewardMapper.selectBizTeamRewardById(id);
    }

    /**
     * 查询团队奖励明细列表
     * 
     * @param bizTeamReward 团队奖励明细
     * @return 团队奖励明细
     */
    @Override
    public List<BizTeamReward> selectBizTeamRewardList(BizTeamReward bizTeamReward)
    {
        return bizTeamRewardMapper.selectBizTeamRewardList(bizTeamReward);
    }

    /**
     * 新增团队奖励明细
     * 
     * @param bizTeamReward 团队奖励明细
     * @return 结果
     */
    @Override
    public int insertBizTeamReward(BizTeamReward bizTeamReward)
    {
        bizTeamReward.setCreateTime(DateUtils.getNowDate());
        return bizTeamRewardMapper.insertBizTeamReward(bizTeamReward);
    }

    /**
     * 插入团队明细
     *
     * @param
     * @return
     */
    @Override
    public void addTeamReward(Long memberID, Long rewardMemberId, Long rewardProductCount, Long rewardAmount, Long productId, int rewardType, String rewardDate)
    {
        BizTeamReward bizTeamReward = new BizTeamReward();
        bizTeamReward.setMemberId(memberID);
        bizTeamReward.setRewardMemberId(rewardMemberId);
        bizTeamReward.setRewardProductCount(rewardProductCount);
        bizTeamReward.setRewardAmount(rewardAmount);
        bizTeamReward.setProductId(productId);
        bizTeamReward.setRewardType(rewardType);
        bizTeamReward.setRewardDate(rewardDate);
        bizTeamReward.setCreateTime(new Date());
        insertBizTeamReward(bizTeamReward);
    }

    /**
     * 修改团队奖励明细
     * 
     * @param bizTeamReward 团队奖励明细
     * @return 结果
     */
    @Override
    public int updateBizTeamReward(BizTeamReward bizTeamReward)
    {
        bizTeamReward.setUpdateTime(DateUtils.getNowDate());
        return bizTeamRewardMapper.updateBizTeamReward(bizTeamReward);
    }

    /**
     * 删除团队奖励明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizTeamRewardByIds(String ids)
    {
        return bizTeamRewardMapper.deleteBizTeamRewardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除团队奖励明细信息
     * 
     * @param id 团队奖励明细ID
     * @return 结果
     */
    @Override
    public int deleteBizTeamRewardById(Long id)
    {
        return bizTeamRewardMapper.deleteBizTeamRewardById(id);
    }
}

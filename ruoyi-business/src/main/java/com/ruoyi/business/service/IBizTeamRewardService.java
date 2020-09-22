package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizTeamReward;

/**
 * 团队奖励明细Service接口
 * 
 * @author ruoyi
 * @date 2020-09-22
 */
public interface IBizTeamRewardService 
{
    /**
     * 查询团队奖励明细
     * 
     * @param id 团队奖励明细ID
     * @return 团队奖励明细
     */
    public BizTeamReward selectBizTeamRewardById(Long id);

    /**
     * 查询团队奖励明细列表
     * 
     * @param bizTeamReward 团队奖励明细
     * @return 团队奖励明细集合
     */
    public List<BizTeamReward> selectBizTeamRewardList(BizTeamReward bizTeamReward);

    /**
     * 新增团队奖励明细
     * 
     * @param bizTeamReward 团队奖励明细
     * @return 结果
     */
    public int insertBizTeamReward(BizTeamReward bizTeamReward);

    /**
     * 插入团队明细
     *
     * @param
     * @return
     */
    public void addTeamReward(Long memberID, Long rewardMemberId, Long rewardProductCount, Long rewardAmount, Long productId, int rewardType, String rewardDate);

    /**
     * 修改团队奖励明细
     * 
     * @param bizTeamReward 团队奖励明细
     * @return 结果
     */
    public int updateBizTeamReward(BizTeamReward bizTeamReward);

    /**
     * 批量删除团队奖励明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizTeamRewardByIds(String ids);

    /**
     * 删除团队奖励明细信息
     * 
     * @param id 团队奖励明细ID
     * @return 结果
     */
    public int deleteBizTeamRewardById(Long id);
}

package com.ruoyi.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 团队奖励明细对象 biz_team_reward
 * 
 * @author ruoyi
 * @date 2020-09-22
 */
public class BizTeamReward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //团队奖励明细类型
    public static final int TEAM_REWARD_TYPE_RECOMM = 0;
    public static final int TEAM_REWARD_TYPE_SECOND = 1;
    public static final int TEAM_REWARD_TYPE_TEAM = 2;

    /** 团队奖励ID */
    private Long id;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberId;

    /** 团队用户ID */
    @Excel(name = "团队用户ID")
    private Long rewardMemberId;

    /** 用户购买产品数量 */
    @Excel(name = "用户购买产品数量")
    private Long rewardProductCount;

    /** 奖励的福豆数量 */
    @Excel(name = "奖励的福豆数量")
    private Long rewardAmount;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;

    /** 奖励类型：0:一级直推；1:二级推荐；2:平级； */
    @Excel(name = "奖励类型：0:一级直推；1:二级推荐；2:团队；")
    private Integer rewardType;

    /** 奖励日期(xxxx-xx-xx) */
    @Excel(name = "奖励日期(xxxx-xx-xx)")
    private String rewardDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setRewardMemberId(Long rewardMemberId) 
    {
        this.rewardMemberId = rewardMemberId;
    }

    public Long getRewardMemberId() 
    {
        return rewardMemberId;
    }
    public void setRewardProductCount(Long rewardProductCount) 
    {
        this.rewardProductCount = rewardProductCount;
    }

    public Long getRewardProductCount() 
    {
        return rewardProductCount;
    }
    public void setRewardAmount(Long rewardAmount)
    {
        this.rewardAmount = rewardAmount;
    }

    public Long getRewardAmount()
    {
        return rewardAmount;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setRewardType(Integer rewardType) 
    {
        this.rewardType = rewardType;
    }

    public Integer getRewardType() 
    {
        return rewardType;
    }
    public void setRewardDate(String rewardDate) 
    {
        this.rewardDate = rewardDate;
    }

    public String getRewardDate() 
    {
        return rewardDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("rewardMemberId", getRewardMemberId())
            .append("rewardProductCount", getRewardProductCount())
            .append("rewardAmount", getRewardAmount())
            .append("productId", getProductId())
            .append("rewardType", getRewardType())
            .append("rewardDate", getRewardDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

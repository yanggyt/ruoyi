package com.ruoyi.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员账户对象 biz_account
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public class BizAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //用户豆账户(0-福豆余额，1-个人福豆，2-团队福豆,3-专项福豆, 4-福豆田)
    public static final int DOU_BALANCE = 0;
    public static final int DOU_PERSON = 1;
    public static final int DOU_TEAM = 2;
    public static final int DOU_SPECIAL = 3;
    public static final int DOU_FIELD = 4;

    //二级直推拥有有效下级用户数量条件
    public static final int SECOND_AWARD_CHILD_LIMIT = 3;

    /** 会员账户ID */
    private Long id;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberId;

    /** 账户类型：0-福豆余额，1-个人福豆，2-团队福豆，3-专项福豆，4-福豆田 */
    @Excel(name = "账户类型：0-福豆余额，1-个人福豆，2-团队福豆，3-专项福豆，4-福豆田")
    private Integer accountType;

    /** 账户金额 */
    @Excel(name = "账户金额")
    private BigDecimal amount;

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
    public void setAccountType(Integer accountType) 
    {
        this.accountType = accountType;
    }

    public Integer getAccountType() 
    {
        return accountType;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("accountType", getAccountType())
            .append("amount", getAmount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

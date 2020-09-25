package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 兑现申请记录对象 biz_cash_info
 * 
 * @author ruoyi
 * @date 2020-09-23
 */
public class BizCashInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //提现申请状态
    public static final int CASH_INFO_STATUS_INITIAL = 0;
    public static final int CASH_INFO_STATUS_AGREE = 1;
    public static final int CASH_INFO_STATUS_DENY = 2;

    /** 兑现申请ID */
    private Long id;

    /** 会员ID */
    private Long memberId;

    /** 会员姓名 */
    @Excel(name = "会员姓名")
    private String memberName;

    /** 会员账户ID */
    private Long accountId;

    /** 账户类型 */
    @Excel(name = "账户类型")
    private Integer accountType;

    /** 开户银行 */
    @Excel(name = "开户银行")
    private Integer bankId;

    /** 开户人 */
    @Excel(name = "开户人")
    private String bankUser;

    /** 开户地 */
    @Excel(name = "开户地")
    private String bankCity;

    /** 卡号 */
    @Excel(name = "卡号")
    private String bankCard;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private Integer drawMoney;

    /** 0-默认 1-通过 2-驳回 */
    @Excel(name = "0-默认 1-通过 2-驳回")
    private Integer status;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setAccountType(Integer accountType) 
    {
        this.accountType = accountType;
    }

    public Integer getAccountType() 
    {
        return accountType;
    }
    public void setBankId(Integer bankId) 
    {
        this.bankId = bankId;
    }

    public Integer getBankId() 
    {
        return bankId;
    }
    public void setBankUser(String bankUser) 
    {
        this.bankUser = bankUser;
    }

    public String getBankUser() 
    {
        return bankUser;
    }
    public void setBankCity(String bankCity) 
    {
        this.bankCity = bankCity;
    }

    public String getBankCity() 
    {
        return bankCity;
    }
    public void setBankCard(String bankCard) 
    {
        this.bankCard = bankCard;
    }

    public String getBankCard() 
    {
        return bankCard;
    }
    public void setDrawMoney(Integer drawMoney) 
    {
        this.drawMoney = drawMoney;
    }

    public Integer getDrawMoney() 
    {
        return drawMoney;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("accountId", getAccountId())
            .append("accountType", getAccountType())
            .append("bankId", getBankId())
            .append("bankUser", getBankUser())
            .append("bankCity", getBankCity())
            .append("bankCard", getBankCard())
            .append("drawMoney", getDrawMoney())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

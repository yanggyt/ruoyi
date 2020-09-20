package com.ruoyi.business.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
/**
 * 会员账户明细对象 biz_account_detail
 *
 * @author ruoyi
 * @date 2020-09-17
 */
public class BizAccountDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //福豆改变类型
    public static final int DOU_CHANGE_TYPE_ADD = 1;
    public static final int DOU_CHANGE_TYPE_REDUSE = -1;
    //福豆使用类型
    public static final int DOU_DETAIL_TYPE_CHARGE = 1;
    public static final int DOU_DETAIL_TYPE_DRAW = 2;
    public static final int DOU_DETAIL_TYPE_EXCHANGE = 3;
    public static final int DOU_DETAIL_TYPE_RESET = 4;
    public static final int DOU_DETAIL_TYPE_ORDER = 5;
    //福豆使用备注
    public static final String DOU_DESC_RECOMM = "直推奖励";
    public static final String DOU_DESC_SECOND = "二级推荐奖励";
    public static final String DOU_DESC_TEAM = "团队奖励";
    public static final String DOU_DESC_SPECIAL1 = "专项账户充值";
    public static final String DOU_DESC_SPECIAL2 = "专项划拨";
    public static final String DOU_DESC_ORDER = "订单消费";

    /** 会员账户明细ID */
    private Long id;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberId;

    /** 会员账户ID */
    @Excel(name = "会员账户ID")
    private Long accountId;

    /** 账户类型：0-福豆余额，1-个人福豆，2-团队福豆，3-专项福豆，4-福豆田 */
    @Excel(name = "账户类型：0-福豆余额，1-个人福豆，2-团队福豆，3-专项福豆，4-福豆田")
    private Integer accountType;

    /** 业务订单编号: 三方支付/兑现申请/团队明细 */
    @Excel(name = "业务订单编号: 三方支付/兑现申请/团队明细")
    private String businessNo;

    /** 变更类型；1:收入(加)；-1:支出(减) */
    @Excel(name = "变更类型；1:收入(加)；-1:支出(减)")
    private Integer changeType;

    /** 变动详情.1：充值；2:提现；3:转账；4:冲正；5:支付 */
    @Excel(name = "变动详情.1：充值；2:提现；3:转账；4:冲正；5:支付")
    private Integer typeDetail;

    /** 账户变更金额 */
    @Excel(name = "账户变更金额")
    private Long amount;

    /** 账户变更前金额 */
    @Excel(name = "账户变更前金额")
    private Long beforeAmount;

    /** 账户变更后金额 */
    @Excel(name = "账户变更后金额")
    private Long afterAmount;

    /** 交易备注：充值【一级推荐奖励】，充值【二级推荐奖励】，充值【团队奖励】，充值【专项划拨】，转账【专项划拨】 */
    @Excel(name = "交易备注：充值【一级推荐奖励】，充值【二级推荐奖励】，充值【团队奖励】，充值【专项划拨】，转账【专项划拨】")
    private String changeDesc;

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
    public void setBusinessNo(String businessNo)
    {
        this.businessNo = businessNo;
    }

    public String getBusinessNo()
    {
        return businessNo;
    }
    public void setChangeType(Integer changeType)
    {
        this.changeType = changeType;
    }

    public Integer getChangeType()
    {
        return changeType;
    }
    public void setTypeDetail(Integer typeDetail)
    {
        this.typeDetail = typeDetail;
    }

    public Integer getTypeDetail()
    {
        return typeDetail;
    }
    public void setBeforeAmount(Long beforeAmount)
    {
        this.beforeAmount = beforeAmount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getBeforeAmount()
    {
        return beforeAmount;
    }
    public void setAfterAmount(Long afterAmount)
    {
        this.afterAmount = afterAmount;
    }

    public Long getAfterAmount()
    {
        return afterAmount;
    }
    public void setChangeDesc(String changeDesc)
    {
        this.changeDesc = changeDesc;
    }

    public String getChangeDesc()
    {
        return changeDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("memberId", getMemberId())
                .append("accountId", getAccountId())
                .append("accountType", getAccountType())
                .append("businessNo", getBusinessNo())
                .append("changeType", getChangeType())
                .append("typeDetail", getTypeDetail())
                .append("beforeAmount", getBeforeAmount())
                .append("afterAmount", getAfterAmount())
                .append("changeDesc", getChangeDesc())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
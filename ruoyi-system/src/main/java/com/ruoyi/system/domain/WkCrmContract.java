package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同对象 wk_crm_contract
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同ID */
    private Integer contractId;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String num;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String name;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private Integer customerId;

    /** 合同金额 */
    @Excel(name = "合同金额")
    private BigDecimal money;

    /** 下单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 客户签约人（联系人id） */
    @Excel(name = "客户签约人", readConverterExp = "联=系人id")
    private Long contactsId;

    /** 公司签约人 */
    @Excel(name = "公司签约人")
    private String companyUserId;

    /** 合同类型 */
    @Excel(name = "合同类型")
    private String types;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer checkStatus;

    /** 已收款金额 */
    @Excel(name = "已收款金额")
    private Double receivedMoney;

    /** 未收款金额 */
    @Excel(name = "未收款金额")
    private Double unreceivedMoney;

    /** 最后跟进人 */
    @Excel(name = "最后跟进人")
    private Integer oldContractId;

    /** 最后跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastTime;

    /** 负责人 */
    @Excel(name = "负责人")
    private Long ownerUserId;

    /** 创建人 */
    @Excel(name = "创建人")
    private Integer createUserId;

    /** 审核记录 */
    private Integer examineRecordId;

    /** 产品总金额 */
    private Double totalPrice;

    /** 付款方式 */
    private String paymentType;

    public void setContractId(Integer contractId) 
    {
        this.contractId = contractId;
    }

    public Integer getContractId() 
    {
        return contractId;
    }
    public void setNum(String num) 
    {
        this.num = num;
    }

    public String getNum() 
    {
        return num;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCustomerId(Integer customerId) 
    {
        this.customerId = customerId;
    }

    public Integer getCustomerId() 
    {
        return customerId;
    }
    public void setMoney(BigDecimal money) 
    {
        this.money = money;
    }

    public BigDecimal getMoney() 
    {
        return money;
    }
    public void setOrderDate(Date orderDate) 
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setContactsId(Long contactsId) 
    {
        this.contactsId = contactsId;
    }

    public Long getContactsId() 
    {
        return contactsId;
    }
    public void setCompanyUserId(String companyUserId) 
    {
        this.companyUserId = companyUserId;
    }

    public String getCompanyUserId() 
    {
        return companyUserId;
    }
    public void setTypes(String types) 
    {
        this.types = types;
    }

    public String getTypes() 
    {
        return types;
    }
    public void setCheckStatus(Integer checkStatus) 
    {
        this.checkStatus = checkStatus;
    }

    public Integer getCheckStatus() 
    {
        return checkStatus;
    }
    public void setReceivedMoney(Double receivedMoney) 
    {
        this.receivedMoney = receivedMoney;
    }

    public Double getReceivedMoney() 
    {
        return receivedMoney;
    }
    public void setUnreceivedMoney(Double unreceivedMoney) 
    {
        this.unreceivedMoney = unreceivedMoney;
    }

    public Double getUnreceivedMoney() 
    {
        return unreceivedMoney;
    }
    public void setOldContractId(Integer oldContractId) 
    {
        this.oldContractId = oldContractId;
    }

    public Integer getOldContractId() 
    {
        return oldContractId;
    }
    public void setLastTime(Date lastTime) 
    {
        this.lastTime = lastTime;
    }

    public Date getLastTime() 
    {
        return lastTime;
    }
    public void setOwnerUserId(Long ownerUserId) 
    {
        this.ownerUserId = ownerUserId;
    }

    public Long getOwnerUserId() 
    {
        return ownerUserId;
    }
    public void setCreateUserId(Integer createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Integer getCreateUserId() 
    {
        return createUserId;
    }
    public void setExamineRecordId(Integer examineRecordId) 
    {
        this.examineRecordId = examineRecordId;
    }

    public Integer getExamineRecordId() 
    {
        return examineRecordId;
    }
    public void setTotalPrice(Double totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() 
    {
        return totalPrice;
    }
    public void setPaymentType(String paymentType) 
    {
        this.paymentType = paymentType;
    }

    public String getPaymentType() 
    {
        return paymentType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractId", getContractId())
            .append("num", getNum())
            .append("name", getName())
            .append("customerId", getCustomerId())
            .append("money", getMoney())
            .append("orderDate", getOrderDate())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("contactsId", getContactsId())
            .append("companyUserId", getCompanyUserId())
            .append("remark", getRemark())
            .append("types", getTypes())
            .append("checkStatus", getCheckStatus())
            .append("receivedMoney", getReceivedMoney())
            .append("unreceivedMoney", getUnreceivedMoney())
            .append("oldContractId", getOldContractId())
            .append("lastTime", getLastTime())
            .append("ownerUserId", getOwnerUserId())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .append("createUserId", getCreateUserId())
            .append("examineRecordId", getExamineRecordId())
            .append("totalPrice", getTotalPrice())
            .append("paymentType", getPaymentType())
            .toString();
    }
}

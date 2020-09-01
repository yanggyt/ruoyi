package com.ruoyi.his.domain;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 挂号记录对象 his_registration_record
 * 
 * @author bend
 * @date 2020-06-28
 */
public class HisRegistrationRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户ID */
    private Long memberId;

    /** 就诊人 */
    private Long patientId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String patientName;

    /** 病人性别（0未知 1男 2女 9未说明） */
    @Excel(name = "病人性别", readConverterExp = "0=未知,1=男,2=女,9=未说明")
    private Integer patientSex;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCardNo;

    /** 医院ID */
    private String orgCode;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private String orgName;

    /** 科室ID */
    private String deptId;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String deptName;

    /** 医生ID */
    private String doctorId;

    /** 医生名称 */
    @Excel(name = "医生名称")
    private String doctorName;

    /** 厂商标识 */
    private String vendorId;

    /** 挂号费 */
    @Excel(name = "挂号费")
    private BigDecimal fee;

    /** 流水号 */
    @Excel(name = "流水号")
    private String paySerialNo;

    /** 缴费方式列表[{PaymentID:支付方式ID,OrgAccID:账户ID,Fee:金额}] */
    private String paymentListStr;

    /** 虚拟收费员ID */
    private String userId;

    /** 挂号ID */
    @Excel(name = "挂号ID")
    private String registeredId;

    /** 挂号CODE */
    private String registeredCode;

    /** 挂号日期 */
    @Excel(name = "挂号日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registeredDate;

    /** 就诊日期 */
    @Excel(name = "就诊日期")
    private String visitDate;

    /** 就诊时段（1上午 2下午） */
    @Excel(name = "就诊时段", readConverterExp = "1=上午,2=下午")
    private String visitTime;

    /** 挂号类型（0预约挂号 1当日挂号 2分诊挂号） */
    @Excel(name = "挂号类型", readConverterExp = "0=预约挂号,1=当日挂号,2=分诊挂号")
    private Integer registeredType;

    /** 门诊ID */
    @Excel(name = "门诊ID")
    private String businessId;

    /** 门诊号 */
    @Excel(name = "门诊号")
    private String outpatientNumber;

    /** 就诊序号 */
    @Excel(name = "就诊序号")
    private String seq;

    /** 是否可退（0否 1是） */
    @Excel(name = "是否可退", readConverterExp = "0=否,1=是")
    private Integer refundAble;

    /** 费用类型ID */
    private String feeTypeId;

    /** 挂号模板ID */
    private String templateId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderCode;

    /** 挂号来源（0线下 1线上 2自助分诊终端） */
    @Excel(name = "挂号来源", readConverterExp = "0=线下,1=线上,2=自助分诊终端")
    private Integer registeredSource;

    /** 状态（0新建 1待接诊 2已就诊 3已退费） */
    @Excel(name = "状态", readConverterExp = "0=新建,1=待接诊,2=已就诊,3=已退费")
    private Integer registeredStatus;

    /** 取消时间 */
    @Excel(name = "取消时间")
    private String cancelTime;

    /** 删除标记（0否 1是） */
    private Integer deleted;

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
    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setPatientSex(Integer patientSex) 
    {
        this.patientSex = patientSex;
    }

    public Integer getPatientSex() 
    {
        return patientSex;
    }
    public void setIdCardNo(String idCardNo) 
    {
        this.idCardNo = idCardNo;
    }

    public String getIdCardNo() 
    {
        return idCardNo;
    }
    public void setOrgCode(String orgCode) 
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode() 
    {
        return orgCode;
    }
    public void setOrgName(String orgName) 
    {
        this.orgName = orgName;
    }

    public String getOrgName() 
    {
        return orgName;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setDoctorId(String doctorId) 
    {
        this.doctorId = doctorId;
    }

    public String getDoctorId() 
    {
        return doctorId;
    }
    public void setDoctorName(String doctorName) 
    {
        this.doctorName = doctorName;
    }

    public String getDoctorName() 
    {
        return doctorName;
    }
    public void setVendorId(String vendorId) 
    {
        this.vendorId = vendorId;
    }

    public String getVendorId() 
    {
        return vendorId;
    }
    public void setFee(BigDecimal fee) 
    {
        this.fee = fee;
    }

    public BigDecimal getFee() 
    {
        return fee;
    }
    public void setPaySerialNo(String paySerialNo) 
    {
        this.paySerialNo = paySerialNo;
    }

    public String getPaySerialNo() 
    {
        return paySerialNo;
    }
    public void setPaymentListStr(String paymentListStr) 
    {
        this.paymentListStr = paymentListStr;
    }

    public String getPaymentListStr() 
    {
        return paymentListStr;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setRegisteredId(String registeredId) 
    {
        this.registeredId = registeredId;
    }

    public String getRegisteredId() 
    {
        return registeredId;
    }
    public void setRegisteredCode(String registeredCode) 
    {
        this.registeredCode = registeredCode;
    }

    public String getRegisteredCode() 
    {
        return registeredCode;
    }
    public void setRegisteredDate(Date registeredDate) 
    {
        this.registeredDate = registeredDate;
    }

    public Date getRegisteredDate() 
    {
        return registeredDate;
    }
    public void setVisitDate(String visitDate) 
    {
        this.visitDate = visitDate;
    }

    public String getVisitDate() 
    {
        return visitDate;
    }
    public void setVisitTime(String visitTime) 
    {
        this.visitTime = visitTime;
    }

    public String getVisitTime() 
    {
        return visitTime;
    }
    public void setRegisteredType(Integer registeredType) 
    {
        this.registeredType = registeredType;
    }

    public Integer getRegisteredType() 
    {
        return registeredType;
    }
    public void setBusinessId(String businessId) 
    {
        this.businessId = businessId;
    }

    public String getBusinessId() 
    {
        return businessId;
    }
    public void setOutpatientNumber(String outpatientNumber) 
    {
        this.outpatientNumber = outpatientNumber;
    }

    public String getOutpatientNumber() 
    {
        return outpatientNumber;
    }
    public void setSeq(String seq) 
    {
        this.seq = seq;
    }

    public String getSeq() 
    {
        return seq;
    }
    public void setRefundAble(Integer refundAble) 
    {
        this.refundAble = refundAble;
    }

    public Integer getRefundAble() 
    {
        return refundAble;
    }
    public void setFeeTypeId(String feeTypeId) 
    {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeTypeId() 
    {
        return feeTypeId;
    }
    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
    }
    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    public void setRegisteredSource(Integer registeredSource) 
    {
        this.registeredSource = registeredSource;
    }

    public Integer getRegisteredSource() 
    {
        return registeredSource;
    }
    public void setRegisteredStatus(Integer registeredStatus) 
    {
        this.registeredStatus = registeredStatus;
    }

    public Integer getRegisteredStatus() 
    {
        return registeredStatus;
    }
    public void setCancelTime(String cancelTime) 
    {
        this.cancelTime = cancelTime;
    }

    public String getCancelTime() 
    {
        return cancelTime;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("patientId", getPatientId())
            .append("patientName", getPatientName())
            .append("patientSex", getPatientSex())
            .append("idCardNo", getIdCardNo())
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("doctorId", getDoctorId())
            .append("doctorName", getDoctorName())
            .append("vendorId", getVendorId())
            .append("fee", getFee())
            .append("paySerialNo", getPaySerialNo())
            .append("paymentListStr", getPaymentListStr())
            .append("userId", getUserId())
            .append("registeredId", getRegisteredId())
            .append("registeredCode", getRegisteredCode())
            .append("registeredDate", getRegisteredDate())
            .append("visitDate", getVisitDate())
            .append("visitTime", getVisitTime())
            .append("registeredType", getRegisteredType())
            .append("businessId", getBusinessId())
            .append("outpatientNumber", getOutpatientNumber())
            .append("seq", getSeq())
            .append("refundAble", getRefundAble())
            .append("feeTypeId", getFeeTypeId())
            .append("templateId", getTemplateId())
            .append("orderCode", getOrderCode())
            .append("registeredSource", getRegisteredSource())
            .append("registeredStatus", getRegisteredStatus())
            .append("cancelTime", getCancelTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}

package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公海对象 wk_crm_customer_pool
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmCustomerPool extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公海id */
    private Long poolId;

    /** 公海名称 */
    @Excel(name = "公海名称")
    private String poolName;

    /** 管理员 “,”分割 */
    @Excel(name = "管理员 “,”分割")
    private String adminUserId;

    /** 公海规则员工成员 “,”分割 */
    @Excel(name = "公海规则员工成员 “,”分割")
    private String memberUserId;

    /** 公海规则部门成员 “,”分割 */
    @Excel(name = "公海规则部门成员 “,”分割")
    private String memberDeptId;

    /** 状态 0 停用 1启用 */
    @Excel(name = "状态 0 停用 1启用")
    private Integer status;

    /** 前负责人领取规则 0不限制 1限制 */
    @Excel(name = "前负责人领取规则 0不限制 1限制")
    private Integer preOwnerSetting;

    /** 前负责人领取规则限制天数 */
    @Excel(name = "前负责人领取规则限制天数")
    private Integer preOwnerSettingDay;

    /** 是否限制领取频率 0不限制 1限制 */
    @Excel(name = "是否限制领取频率 0不限制 1限制")
    private Integer receiveSetting;

    /** 领取频率规则 */
    @Excel(name = "领取频率规则")
    private Integer receiveNum;

    /** 是否设置提前提醒 0不开启 1开启 */
    @Excel(name = "是否设置提前提醒 0不开启 1开启")
    private Integer remindSetting;

    /** 提醒规则天数 */
    @Excel(name = "提醒规则天数")
    private Long remindDay;

    /** 收回规则 0不自动收回 1自动收回 */
    @Excel(name = "收回规则 0不自动收回 1自动收回")
    private Integer putInRule;

    /**  */
    @Excel(name = "")
    private Long createUserId;

    public void setPoolId(Long poolId) 
    {
        this.poolId = poolId;
    }

    public Long getPoolId() 
    {
        return poolId;
    }
    public void setPoolName(String poolName) 
    {
        this.poolName = poolName;
    }

    public String getPoolName() 
    {
        return poolName;
    }
    public void setAdminUserId(String adminUserId) 
    {
        this.adminUserId = adminUserId;
    }

    public String getAdminUserId() 
    {
        return adminUserId;
    }
    public void setMemberUserId(String memberUserId) 
    {
        this.memberUserId = memberUserId;
    }

    public String getMemberUserId() 
    {
        return memberUserId;
    }
    public void setMemberDeptId(String memberDeptId) 
    {
        this.memberDeptId = memberDeptId;
    }

    public String getMemberDeptId() 
    {
        return memberDeptId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setPreOwnerSetting(Integer preOwnerSetting) 
    {
        this.preOwnerSetting = preOwnerSetting;
    }

    public Integer getPreOwnerSetting() 
    {
        return preOwnerSetting;
    }
    public void setPreOwnerSettingDay(Integer preOwnerSettingDay) 
    {
        this.preOwnerSettingDay = preOwnerSettingDay;
    }

    public Integer getPreOwnerSettingDay() 
    {
        return preOwnerSettingDay;
    }
    public void setReceiveSetting(Integer receiveSetting) 
    {
        this.receiveSetting = receiveSetting;
    }

    public Integer getReceiveSetting() 
    {
        return receiveSetting;
    }
    public void setReceiveNum(Integer receiveNum) 
    {
        this.receiveNum = receiveNum;
    }

    public Integer getReceiveNum() 
    {
        return receiveNum;
    }
    public void setRemindSetting(Integer remindSetting) 
    {
        this.remindSetting = remindSetting;
    }

    public Integer getRemindSetting() 
    {
        return remindSetting;
    }
    public void setRemindDay(Long remindDay) 
    {
        this.remindDay = remindDay;
    }

    public Long getRemindDay() 
    {
        return remindDay;
    }
    public void setPutInRule(Integer putInRule) 
    {
        this.putInRule = putInRule;
    }

    public Integer getPutInRule() 
    {
        return putInRule;
    }
    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() 
    {
        return createUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("poolId", getPoolId())
            .append("poolName", getPoolName())
            .append("adminUserId", getAdminUserId())
            .append("memberUserId", getMemberUserId())
            .append("memberDeptId", getMemberDeptId())
            .append("status", getStatus())
            .append("preOwnerSetting", getPreOwnerSetting())
            .append("preOwnerSettingDay", getPreOwnerSettingDay())
            .append("receiveSetting", getReceiveSetting())
            .append("receiveNum", getReceiveNum())
            .append("remindSetting", getRemindSetting())
            .append("remindDay", getRemindDay())
            .append("putInRule", getPutInRule())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}

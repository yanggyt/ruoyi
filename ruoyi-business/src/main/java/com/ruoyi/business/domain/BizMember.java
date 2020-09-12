package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员对象 biz_member
 * 
 * @author ruoyi
 * @date 2020-09-11
 */
public class BizMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员ID */
    private Long id;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String memberName;

    /** 用户密码 */
    private String password;

    /** 推荐人ID */
    @Excel(name = "推荐人ID")
    private Long recommendId;

    /** 推荐人手机 */
    private String recommendMobile;

    /** 推荐人姓名 */
    private String recommendName;

    /** 会员类型 */
    @Excel(name = "会员类型")
    private Integer memberType;

    /** 是否删除：0-否，1-是 */
    private Integer isDelete;

    /** 是否禁用：0-否，1-是 */
    @Excel(name = "是否禁用：0-否，1-是")
    private Integer isEnable;

    /** 福豆相关数据 */
    @Excel(name = "福豆余额")
    private Long douBalance;

    @Excel(name = "福豆余额")
    private Long douPerson;

    @Excel(name = "团队福豆")
    private Long douTeam;

    @Excel(name = "福豆田")
    private Long douField;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setMemberName(String memberName) 
    {
        this.memberName = memberName;
    }

    public String getMemberName() 
    {
        return memberName;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setRecommendId(Long recommendId) 
    {
        this.recommendId = recommendId;
    }

    public Long getRecommendId() 
    {
        return recommendId;
    }

    public void setRecommendMobile(String recommendMobile) 
    {
        this.recommendMobile = recommendMobile;
    }

    public String getRecommendMobile() 
    {
        return recommendMobile;
    }
    public void setRecommendName(String recommendName) 
    {
        this.recommendName = recommendName;
    }

    public String getRecommendName() 
    {
        return recommendName;
    }
    public void setMemberType(Integer memberType) 
    {
        this.memberType = memberType;
    }

    public Integer getMemberType() 
    {
        return memberType;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }
    public void setIsEnable(Integer isEnable) 
    {
        this.isEnable = isEnable;
    }

    public Integer getIsEnable() 
    {
        return isEnable;
    }

    public Long getDouBalance() {
        return douBalance;
    }

    public void setDouBalance(Long douBalance) {
        this.douBalance = douBalance;
    }

    public Long getDouPerson() {
        return douPerson;
    }

    public void setDouPerson(Long douPerson) {
        this.douPerson = douPerson;
    }

    public Long getDouTeam() {
        return douTeam;
    }

    public void setDouTeam(Long douTeam) {
        this.douTeam = douTeam;
    }

    public Long getDouField() {
        return douField;
    }

    public void setDouField(Long douField) {
        this.douField = douField;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mobile", getMobile())
            .append("memberName", getMemberName())
            .append("password", getPassword())
            .append("recommendId", getRecommendId())
            .append("recommendMobile", getRecommendMobile())
            .append("recommendName", getRecommendName())
            .append("memberType", getMemberType())
            .append("isDelete", getIsDelete())
            .append("isEnable", getIsEnable())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

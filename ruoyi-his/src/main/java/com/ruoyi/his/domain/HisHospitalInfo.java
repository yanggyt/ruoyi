package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 医疗机构对象 his_hospital_info
 * 
 * @author bend
 * @date 2020-06-28
 */
public class HisHospitalInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 机构编码 */
    private String orgCode;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String orgAddress;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 邮政编码 */
    private String postalCode;

    /** 机构图标 */
    @Excel(name = "机构图标")
    private String logo;

    /** 机构简介 */
    @Excel(name = "机构简介")
    private String introduction;

    /** 医院类别（1医院 2乡镇卫生院 3社区卫生服务中心 4诊所） */
    @Excel(name = "医院类别", readConverterExp = "1=医院,2=乡镇卫生院,3=社区卫生服务中心,4=诊所")
    private Integer orgType;

    /** 医院等级（1三甲医院 2三乙医院 3三丙医院 4二甲医院 5二乙医院 6二丙医院 7一甲医院 8一乙医院 9一丙医院 10社区卫生服务中心 11社区卫生服务站 ） */
    @Excel(name = "医院等级", readConverterExp = "1=三甲医院,2=三乙医院,3=三丙医院,4=二甲医院,5=二乙医院,6=二丙医院,7=一甲医院,8=一乙医院,9=一丙医院,1=0社区卫生服务中心,1=1社区卫生服务站")
    private Integer orgLevel;

    /** 优秀科室 */
    private String excellentDeptIds;

    /** 优秀医生 */
    private String excellentDoctorIds;

    /** 粉丝量 */
    private Long fans;

    /** 星级评分 */
    private BigDecimal starRating;

    /** 营业执照 */
    private String businessLicense;

    /** 排序号 */
    @Excel(name = "排序号")
    private Long sortNo;

    /** 经度 */
    private BigDecimal longitude;

    /** 纬度 */
    private BigDecimal latitude;

    /** 区划ID */
    private String regionId;

    /** 区划代码 */
    private String regionCode;

    /**机构虚拟操作员ID*/
    private String vmUserId;

    /**虚拟操作员*/
    private String vmUserName;

    /** 显示状态（0否 1是） */
    @Excel(name = "显示状态", readConverterExp = "0=否,1=是")
    private Integer isShow;

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
    public void setOrgAddress(String orgAddress) 
    {
        this.orgAddress = orgAddress;
    }

    public String getOrgAddress() 
    {
        return orgAddress;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }
    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

    public String getPostalCode() 
    {
        return postalCode;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setOrgType(Integer orgType) 
    {
        this.orgType = orgType;
    }

    public Integer getOrgType() 
    {
        return orgType;
    }
    public void setOrgLevel(Integer orgLevel) 
    {
        this.orgLevel = orgLevel;
    }

    public Integer getOrgLevel() 
    {
        return orgLevel;
    }
    public void setExcellentDeptIds(String excellentDeptIds) 
    {
        this.excellentDeptIds = excellentDeptIds;
    }

    public String getExcellentDeptIds() 
    {
        return excellentDeptIds;
    }
    public void setExcellentDoctorIds(String excellentDoctorIds) 
    {
        this.excellentDoctorIds = excellentDoctorIds;
    }

    public String getExcellentDoctorIds() 
    {
        return excellentDoctorIds;
    }
    public void setFans(Long fans) 
    {
        this.fans = fans;
    }

    public Long getFans() 
    {
        return fans;
    }
    public void setStarRating(BigDecimal starRating) 
    {
        this.starRating = starRating;
    }

    public BigDecimal getStarRating() 
    {
        return starRating;
    }
    public void setBusinessLicense(String businessLicense) 
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense() 
    {
        return businessLicense;
    }
    public void setSortNo(Long sortNo) 
    {
        this.sortNo = sortNo;
    }

    public Long getSortNo() 
    {
        return sortNo;
    }
    public void setLongitude(BigDecimal longitude) 
    {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(BigDecimal latitude) 
    {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() 
    {
        return latitude;
    }
    public void setRegionId(String regionId) 
    {
        this.regionId = regionId;
    }

    public String getRegionId() 
    {
        return regionId;
    }
    public void setRegionCode(String regionCode) 
    {
        this.regionCode = regionCode;
    }

    public String getRegionCode() 
    {
        return regionCode;
    }

    public String getVmUserId() {
        return vmUserId;
    }

    public void setVmUserId(String vmUserId) {
        this.vmUserId = vmUserId;
    }

    public String getVmUserName() {
        return vmUserName;
    }

    public void setVmUserName(String vmUserName) {
        this.vmUserName = vmUserName;
    }

    public void setIsShow(Integer isShow)
    {
        this.isShow = isShow;
    }

    public Integer getIsShow() 
    {
        return isShow;
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
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("orgAddress", getOrgAddress())
            .append("contactPhone", getContactPhone())
            .append("contactPerson", getContactPerson())
            .append("postalCode", getPostalCode())
            .append("logo", getLogo())
            .append("introduction", getIntroduction())
            .append("orgType", getOrgType())
            .append("orgLevel", getOrgLevel())
            .append("excellentDeptIds", getExcellentDeptIds())
            .append("excellentDoctorIds", getExcellentDoctorIds())
            .append("fans", getFans())
            .append("starRating", getStarRating())
            .append("businessLicense", getBusinessLicense())
            .append("sortNo", getSortNo())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("regionId", getRegionId())
            .append("regionCode", getRegionCode())
            .append("vmUserId", getVmUserId())
            .append("vmUserName", getVmUserName())
            .append("isShow", getIsShow())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}

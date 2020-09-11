package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员收货地址对象 biz_member_address
 * 
 * @author ruoyi
 * @date 2020-09-11
 */
public class BizMemberAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员地址ID */
    private Long id;

    /** 会员ID */
    @Excel(name = "会员ID")
    private Long memberID;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String memberName;

    /** 收货人地址 */
    @Excel(name = "收货人地址")
    private String address;

    /** 省编码 */
    @Excel(name = "省编码")
    private String provinceCode;

    /** 省名称 */
    @Excel(name = "省名称")
    private String provinceName;

    /** 市编码 */
    @Excel(name = "市编码")
    private String cityCode;

    /** 市名称 */
    @Excel(name = "市名称")
    private String cityName;

    /** 区编码 */
    @Excel(name = "区编码")
    private String areaCode;

    /** 区名称 */
    @Excel(name = "区名称")
    private String areaName;

    /** 是否删除：0-否，1-是 */
    @Excel(name = "是否删除：0-否，1-是")
    private Integer isDelete;

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

    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
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
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setProvinceCode(String provinceCode) 
    {
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode() 
    {
        return provinceCode;
    }
    public void setProvinceName(String provinceName) 
    {
        this.provinceName = provinceName;
    }

    public String getProvinceName() 
    {
        return provinceName;
    }
    public void setCityCode(String cityCode) 
    {
        this.cityCode = cityCode;
    }

    public String getCityCode() 
    {
        return cityCode;
    }
    public void setCityName(String cityName) 
    {
        this.cityName = cityName;
    }

    public String getCityName() 
    {
        return cityName;
    }
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode() 
    {
        return areaCode;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mobile", getMobile())
            .append("memberName", getMemberName())
            .append("address", getAddress())
            .append("provinceCode", getProvinceCode())
            .append("provinceName", getProvinceName())
            .append("cityCode", getCityCode())
            .append("cityName", getCityName())
            .append("areaCode", getAreaCode())
            .append("areaName", getAreaName())
            .append("isDelete", getIsDelete())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

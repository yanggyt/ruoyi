package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家信息对象 business_firm
 * 
 * @author ruoyi
 * @date 2020-05-19
 */
public class BusinessFirm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 商家编号 */
    @Excel(name = "商家编号")
    private String businessCode;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String loginName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 固定电话 */
    @Excel(name = "固定电话")
    private String telephoneNo;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNo;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 头像路径 */
    @Excel(name = "头像路径")
    private String avatar;

    /** 身份证证件地址 */
    @Excel(name = "身份证证件地址")
    private String idCard;

    /** 房产、合同等证件地址,json串 */
    @Excel(name = "房产、合同等证件地址,json串")
    private String credentials;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 盐加密 */
    @Excel(name = "盐加密")
    private String salt;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 质保金金额 */
    @Excel(name = "质保金金额")
    private Long guaranteeMoney;

    /** 已退质保金金额 */
    @Excel(name = "已退质保金金额")
    private Long refundGuaranteeMoney;

    /** 商家类型，0个人 1商家 */
    @Excel(name = "商家类型，0个人 1商家")
    private String bussinessType;

    /** 省id */
    @Excel(name = "省id")
    private String provinceCode;

    /** 省名称 */
    @Excel(name = "省名称")
    private String provinceName;

    /** 市ID */
    @Excel(name = "市ID")
    private String cityCode;

    /** 市名称 */
    @Excel(name = "市名称")
    private String ciityName;

    /** 县ID */
    @Excel(name = "县ID")
    private String countyCode;

    /** 县名称 */
    @Excel(name = "县名称")
    private String countyName;

    /** 乡/镇ID */
    @Excel(name = "乡/镇ID")
    private String townCode;

    /** 乡/镇名称 */
    @Excel(name = "乡/镇名称")
    private String townName;

    /** 详细地址信息 */
    @Excel(name = "详细地址信息")
    private String adress;

    /** 坐标地址 */
    @Excel(name = "坐标地址")
    private String coordinatePoint;

    /** 开户行名称 */
    @Excel(name = "开户行名称")
    private String bankName;

    /** 开户行卡号 */
    @Excel(name = "开户行卡号")
    private String bankCardNo;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登陆ip */
    @Excel(name = "最后登陆ip")
    private String loginIp;

    /** 最后登陆时间 */
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginDate;

    /** 版本号 */
    @Excel(name = "版本号")
    private Integer version;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBusinessCode(String businessCode) 
    {
        this.businessCode = businessCode;
    }

    public String getBusinessCode() 
    {
        return businessCode;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setTelephoneNo(String telephoneNo) 
    {
        this.telephoneNo = telephoneNo;
    }

    public String getTelephoneNo() 
    {
        return telephoneNo;
    }
    public void setPhoneNo(String phoneNo) 
    {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() 
    {
        return phoneNo;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setCredentials(String credentials) 
    {
        this.credentials = credentials;
    }

    public String getCredentials() 
    {
        return credentials;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setGuaranteeMoney(Long guaranteeMoney) 
    {
        this.guaranteeMoney = guaranteeMoney;
    }

    public Long getGuaranteeMoney() 
    {
        return guaranteeMoney;
    }
    public void setRefundGuaranteeMoney(Long refundGuaranteeMoney) 
    {
        this.refundGuaranteeMoney = refundGuaranteeMoney;
    }

    public Long getRefundGuaranteeMoney() 
    {
        return refundGuaranteeMoney;
    }
    public void setBussinessType(String bussinessType) 
    {
        this.bussinessType = bussinessType;
    }

    public String getBussinessType() 
    {
        return bussinessType;
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
    public void setCiityName(String ciityName) 
    {
        this.ciityName = ciityName;
    }

    public String getCiityName() 
    {
        return ciityName;
    }
    public void setCountyCode(String countyCode) 
    {
        this.countyCode = countyCode;
    }

    public String getCountyCode() 
    {
        return countyCode;
    }
    public void setCountyName(String countyName) 
    {
        this.countyName = countyName;
    }

    public String getCountyName() 
    {
        return countyName;
    }
    public void setTownCode(String townCode) 
    {
        this.townCode = townCode;
    }

    public String getTownCode() 
    {
        return townCode;
    }
    public void setTownName(String townName) 
    {
        this.townName = townName;
    }

    public String getTownName() 
    {
        return townName;
    }
    public void setAdress(String adress) 
    {
        this.adress = adress;
    }

    public String getAdress() 
    {
        return adress;
    }
    public void setCoordinatePoint(String coordinatePoint) 
    {
        this.coordinatePoint = coordinatePoint;
    }

    public String getCoordinatePoint() 
    {
        return coordinatePoint;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankCardNo(String bankCardNo) 
    {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardNo() 
    {
        return bankCardNo;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setLoginIp(String loginIp) 
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp() 
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate) 
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() 
    {
        return loginDate;
    }
    public void setVersion(Integer version) 
    {
        this.version = version;
    }

    public Integer getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("businessCode", getBusinessCode())
            .append("loginName", getLoginName())
            .append("userName", getUserName())
            .append("email", getEmail())
            .append("telephoneNo", getTelephoneNo())
            .append("phoneNo", getPhoneNo())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("idCard", getIdCard())
            .append("credentials", getCredentials())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("status", getStatus())
            .append("guaranteeMoney", getGuaranteeMoney())
            .append("refundGuaranteeMoney", getRefundGuaranteeMoney())
            .append("bussinessType", getBussinessType())
            .append("provinceCode", getProvinceCode())
            .append("provinceName", getProvinceName())
            .append("cityCode", getCityCode())
            .append("ciityName", getCiityName())
            .append("countyCode", getCountyCode())
            .append("countyName", getCountyName())
            .append("townCode", getTownCode())
            .append("townName", getTownName())
            .append("adress", getAdress())
            .append("coordinatePoint", getCoordinatePoint())
            .append("bankName", getBankName())
            .append("bankCardNo", getBankCardNo())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("version", getVersion())
            .append("remark", getRemark())
            .toString();
    }
}

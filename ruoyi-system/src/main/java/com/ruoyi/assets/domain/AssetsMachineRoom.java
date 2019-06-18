package com.ruoyi.assets.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.SysDictData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.beans.Transient;

/**
 * 机房表 assets_machine_room
 *
 * @author TP
 * @date 2019-06-17
 */
public class AssetsMachineRoom extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 机房编号
     */
    private Integer machineRoomId;
    /**
     * 机房名称
     */
    private String machineRoomName;
    /**
     * 带宽类型
     */
    private Integer dictCode;
    /**
     * 带宽类型 实体关联
     */
    private SysDictData bandwidthType;
    /**
     * 带宽大小
     */
    private Integer bandwidthSize;
    /**
     * 所在国家
     */
    private String country;
    /**
     * 所在省
     */
    private String province;
    /**
     * 所在市
     */
    private String city;
    /**
     * 所在区
     */
    private String area;
    /**
     * 地址
     */
    private String location;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 邮箱
     */
    private String email;

    public Integer getMachineRoomId() {
        return machineRoomId;
    }

    public void setMachineRoomId(Integer machineRoomId) {
        this.machineRoomId = machineRoomId;
    }

    public String getMachineRoomName() {
        return machineRoomName;
    }

    public void setMachineRoomName(String machineRoomName) {
        this.machineRoomName = machineRoomName;
    }

    @Transient
    public Integer getDictCode() {
        return dictCode;
    }

    public void setDictCode(Integer dictCode) {
        this.dictCode = dictCode;
    }

    public SysDictData getBandwidthType() {
        return bandwidthType;
    }

    public void setBandwidthType(SysDictData bandwidthType) {
        this.bandwidthType = bandwidthType;
    }

    public Integer getBandwidthSize() {
        return bandwidthSize;
    }

    public void setBandwidthSize(Integer bandwidthSize) {
        this.bandwidthSize = bandwidthSize;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("machineRoomId", getMachineRoomId())
                .append("machineRoomName", getMachineRoomName())
                .append("bandwidthType", getBandwidthType())
                .append("bandwidthSize", getBandwidthSize())
                .append("country", getCountry())
                .append("province", getProvince())
                .append("city", getCity())
                .append("area", getArea())
                .append("location", getLocation())
                .append("contactPerson", getContactPerson())
                .append("contactPhone", getContactPhone())
                .append("email", getEmail())
                .toString();
    }
}

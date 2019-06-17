package com.ruoyi.assets.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 机柜类型表 assets_cabinet
 *
 * @author TP
 * @date 2019-06-17
 */
public class AssetsCabinet extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 机柜编号
     */
    private Integer cabinetId;
    /**
     * 机房编号
     */
    private AssetsMachineRoom machineRoom;
    /**
     * 机柜名称
     */
    private String cabinetName;
    /**
     * 机柜所在区域
     */
    private Long cabinetArea;
    /**
     * 机柜可用U数
     */
    private Integer cabinetAvailU;
    /**
     * 机柜可用电力
     */
    private Integer cabinetAvailElec;

    public Integer getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(Integer cabinetId) {
        this.cabinetId = cabinetId;
    }

    public AssetsMachineRoom getMachineRoom() {
        return machineRoom;
    }

    public void setMachineRoom(AssetsMachineRoom machineRoom) {
        this.machineRoom = machineRoom;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public Long getCabinetArea() {
        return cabinetArea;
    }

    public void setCabinetArea(Long cabinetArea) {
        this.cabinetArea = cabinetArea;
    }

    public Integer getCabinetAvailU() {
        return cabinetAvailU;
    }

    public void setCabinetAvailU(Integer cabinetAvailU) {
        this.cabinetAvailU = cabinetAvailU;
    }

    public Integer getCabinetAvailElec() {
        return cabinetAvailElec;
    }

    public void setCabinetAvailElec(Integer cabinetAvailElec) {
        this.cabinetAvailElec = cabinetAvailElec;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("cabinetId", getCabinetId())
                .append("machineRoomId", getMachineRoom())
                .append("cabinetName", getCabinetName())
                .append("cabinetArea", getCabinetArea())
                .append("cabinetAvailU", getCabinetAvailU())
                .append("cabinetAvailElec", getCabinetAvailElec())
                .append("remark", getRemark())
                .toString();
    }
}

package com.wuzhen.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuzhen.common.annotation.Excel;
import com.wuzhen.common.annotation.Excel.ColumnType;
import com.wuzhen.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * 活动信息 active_info
 *
 * @author zhengzheng
 */
public class ActiveInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    @Excel(name = "报名用户编号", cellType = ColumnType.NUMERIC)
    private Long id;


    /**
     * 活动标题
     */
    @Excel(name = "活动标题")
    private String activeTitle;


    /**
     * 活动内容描述
     */
    @Excel(name = "活动内容描述")
    private String activeDesc;




    /**
     * 活动状态
     */
    private String actStatus;


    /**
     * 活动开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动开始日期")
    private String activeStartDate;

    /**
     * 活动结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动结束日期")
    private String activeEndDate;


    /**
     * 报名开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名开始日期")
    private String enrollStartDate;

    public String getEnrollStartDate() {
        return enrollStartDate;
    }

    public void setEnrollStartDate(String enrollStartDate) {
        this.enrollStartDate = enrollStartDate;
    }

    public String getEnrollEndDate() {
        return enrollEndDate;
    }

    public void setEnrollEndDate(String enrollEndDate) {
        this.enrollEndDate = enrollEndDate;
    }

    /**
     * 报名结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名结束日期")
    private String enrollEndDate;


    /**
     * 海报地址
     */
    @Excel(name = "活动类型")
    private String activeType;


    public String getLpFilesName() {
        return lpFilesName;
    }

    public void setLpFilesName(String lpFilesName) {
        this.lpFilesName = lpFilesName;
    }

    /**
     * 活动列表
     */
    private String lpFilesName;

    private String fpFilesName;

    private String lsFilesName;


    public String getLsFilesName() {
        return lsFilesName;
    }

    public void setLsFilesName(String lsFilesName) {
        this.lsFilesName = lsFilesName;
    }


    public String getListLpNames() {
        return listLpNames;
    }

    public void setListLpNames(String listLpNames) {
        this.listLpNames = listLpNames;
    }

    private String listLpNames ;

    public String getListFpNames() {
        return listFpNames;
    }

    public void setListFpNames(String listFpNames) {
        this.listFpNames = listFpNames;
    }

    private String listFpNames ;


    public String getFpFilesName() {
        return fpFilesName;
    }

    public void setFpFilesName(String fpFilesName) {
        this.fpFilesName = fpFilesName;
    }

    private String listLsNames ;

    public String getListLsNames() {
        return listLsNames;
    }

    public void setListLsNames(String listLsNames) {
        this.listLsNames = listLsNames;
    }



    /**
     * 活动图片地址
     */
    @Excel(name = "活动图片地址")
    private String activePicUrl;


    /**
     * 活动图片
     */
    private MultipartFile activeFirstPic;


    /**
     * 活动图片地址
     */
    @Excel(name = "活动首页图片地址")
    private String activeFirstPicUrl;



    /**
     * 活动图片地址
     */
    @Excel(name = "活动地址")
    private String address;


    /**
     * 活动报名类型
     */
    @Excel(name = "活动报名类型")
    private String isEnroll;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 活动图片地址
     */
    private String isFristPage;


    public MultipartFile getActiveFirstPic() {
        return activeFirstPic;
    }

    public void setActiveFirstPic(MultipartFile activeFirstPic) {
        this.activeFirstPic = activeFirstPic;
    }

    public String getActiveFirstPicUrl() {
        return activeFirstPicUrl;
    }

    public void setActiveFirstPicUrl(String activeFirstPicUrl) {
        this.activeFirstPicUrl = activeFirstPicUrl;
    }

    public String getIsFristPage() {
        return isFristPage;
    }

    public void setIsFristPage(String isFristPage) {
        this.isFristPage = isFristPage;
    }

    /**
     * 推荐人编号
     */
    @Excel(name = "活动状态")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActiveTitle() {
        return activeTitle;
    }

    public void setActiveTitle(String activeTitle) {
        this.activeTitle = activeTitle;
    }

    public String getActiveDesc() {
        return activeDesc;
    }

    public void setActiveDesc(String activeDesc) {
        this.activeDesc = activeDesc;
    }

    public String getActiveStartDate() {
        return activeStartDate;
    }

    public void setActiveStartDate(String activeStartDate) {
        this.activeStartDate = activeStartDate;
    }

    public String getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(String activeEndDate) {
        this.activeEndDate = activeEndDate;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }


    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

//    public MultipartFile[] getActivePic() {
//        return activePic;
//    }
//
//    public void setActivePic(MultipartFile[] activePic) {
//        this.activePic = activePic;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getActivePicUrl() {
        return activePicUrl;
    }

    public void setActivePicUrl(String activePicUrl) {
        this.activePicUrl = activePicUrl;
    }



    public String getIsEnroll() {
        return isEnroll;
    }

    public void setIsEnroll(String isEnroll) {
        this.isEnroll = isEnroll;
    }

    @Override
    public String toString() {
        return "ActiveInfo{" +
                "id=" + id +
                ", activeTitle='" + activeTitle + '\'' +
                ", activeDesc='" + activeDesc + '\'' +
                ", activeStartDate='" + activeStartDate + '\'' +
                ", activeEndDate='" + activeEndDate + '\'' +
                ", enrollStartDate='" + enrollStartDate + '\'' +
                ", enrollEndDate='" + enrollEndDate + '\'' +
                ", activeType='" + activeType + '\'' +
                ", lpFilesName='" + lpFilesName + '\'' +
                ", fpFilesName='" + fpFilesName + '\'' +
                ", lsFilesName='" + lsFilesName + '\'' +
                ", listLpNames='" + listLpNames + '\'' +
                ", listFpNames='" + listFpNames + '\'' +
                ", listLsNames='" + listLsNames + '\'' +
                ", activePicUrl='" + activePicUrl + '\'' +
                ", activeFirstPic=" + activeFirstPic +
                ", activeFirstPicUrl='" + activeFirstPicUrl + '\'' +
                ", address='" + address + '\'' +
                ", isEnroll='" + isEnroll + '\'' +
                ", isFristPage='" + isFristPage + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}

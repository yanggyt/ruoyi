package com.wuzhen.system.domain;

import com.wuzhen.common.annotation.Excel;
import com.wuzhen.common.annotation.Excel.ColumnType;
import com.wuzhen.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 报名表 Enroll_user
 *
 * @author zhengzheng
 */
public class EnrollUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //    /**
//     * 报名用户编号
//     */
    @Excel(name = "报名用户编号", cellType = ColumnType.NUMERIC)
    private Long id;


    /**
     * 昵称
     */
    @Excel(name = "昵称")
    private String nickName;


    /**
     * 岗位名称
     */
    @Excel(name = "职务")
    private String postName;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /**
     * 海报地址
     */
    @Excel(name = "海报地址")
    private String playbillAddress;

    /**
     * 推荐人编号
     */
    @Excel(name = "推荐人编号")
    private String recommenderNo;

    @Excel(name = "EPC号")
    private String qrCode;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlaybillAddress() {
        return playbillAddress;
    }

    public void setPlaybillAddress(String playbillAddress) {
        this.playbillAddress = playbillAddress;
    }

    public String getRecommenderNo() {
        return recommenderNo;
    }

    public void setRecommenderNo(String recommenderNo) {
        this.recommenderNo = recommenderNo;
    }


    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("nickName", getNickName())
                .append("postName", getPostName())
                .append("phoneNumber", getPhoneNumber())
                .append("playbillAddress", getPlaybillAddress())
                .append("recommenderNo", getRecommenderNo())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }

}

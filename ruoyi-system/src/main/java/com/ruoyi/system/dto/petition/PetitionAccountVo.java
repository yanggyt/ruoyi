package com.ruoyi.system.dto.petition;

import java.util.Date;

/**
 * 电子签呈查询详情页面吧单子相关要展示的人员放进一个vo前台进行展示(主要用于APP)
 * @author: zsy
 * @create: 2022-01-10 14:26
 * @Version 1.0
 **/
public class PetitionAccountVo {
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;
    /** 核准人员 */
    private String addName;
    /** 工号 */
    private String sid;
    /** 审核结果 */
    private Integer result;
    /** 备注意见 */
    private String remark;
    /** 创建者 */
    private String createBy;
    /** 创建时间 */
    private Date createTime;
    /** 发送到 */
    private String tosend;
    /** 关联id */
    private Long petitionId;
    /** 发送者 */
    private String fromsend;

    private Date auditDate;

    /**
     *  角色
     */
    
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTosend() {
        return tosend;
    }

    public void setTosend(String tosend) {
        this.tosend = tosend;
    }

    public Long getPetitionId() {
        return petitionId;
    }

    public void setPetitionId(Long petitionId) {
        this.petitionId = petitionId;
    }

    public String getFromsend() {
        return fromsend;
    }

    public void setFromsend(String fromsend) {
        this.fromsend = fromsend;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    @Override
    public String toString() {
        return "PetitionAccountVo{" +
                "id=" + id +
                ", addName='" + addName + '\'' +
                ", sid='" + sid + '\'' +
                ", result=" + result +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", tosend='" + tosend + '\'' +
                ", petitionId=" + petitionId +
                ", fromsend='" + fromsend + '\'' +
                ", auditDate=" + auditDate +
                '}';
    }
}

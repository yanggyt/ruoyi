package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * 部门表 sys_dept
 *
 * @author ruoyi
 */
@Entity
@Table(name = "sys_dept")
public class SysDept extends BaseEntity {

    public static final Long ROOT_ID = 1L;
    public static final String STATUS_NORMAL = "0";
    public static final String STATUS_DISABLED = "1";

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    /**
     * 编码，父001 -> 子001001 -> 孙 001001001 格式编码
     */
    private String code;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示顺序
     */
    private String orderNum;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态:0正常,1停用
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag = NOT_DELETED;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @ForeignKey(name = "none")
    private SysDept parent;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @NotBlank(message = "显示顺序不能为空")
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public SysDept getParent() {
        return parent;
    }

    public void setParent(SysDept parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("code", getCode())
                .append("deptName", getDeptName())
                .append("orderNum", getOrderNum())
                .append("leader", getLeader())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }

    @Transient
    public Long getParentId() {
        if(this.parent == null){
            return null;
        }
        return parent.getDeptId();
    }

    public SysDept(){
        super();
    }

    public SysDept(Long deptId){
        this();
        setDeptId(deptId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDept dept = (SysDept) o;
        return Objects.equals(deptId, dept.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId);
    }
}

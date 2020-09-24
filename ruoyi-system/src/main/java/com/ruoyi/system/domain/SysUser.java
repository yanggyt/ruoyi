package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
@Data
@Entity
@Table(name = "sys_user")
@DynamicUpdate
@SelectBeforeUpdate
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /**
     * 登录名称
     */
    @NotBlank(message = "登录账号不能为空")
    @Size(min = 0, max = 30, message = "登录账号长度不能超过30个字符")
    @Excel(name = "登录名称")
    private String loginName;

    /**
     * 用户名称
     */
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    @Excel(name = "手机号码")
    private String phonenumber;

    /**
     * 用户性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登陆IP
     */
    @Excel(name = "最后登陆IP", type = Type.EXPORT)
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;

    /** 用户类型 */
    private String userType;

    /**
     * 部门对象
     */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "deptId")
    @ForeignKey(name = "none")
    private SysDept dept;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
    @ForeignKey(name = "none")
    private Set<SysRole> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_post",
            inverseJoinColumns = @JoinColumn(name = "postId", referencedColumnName = "postId"),
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"))
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<SysPost> posts = new HashSet<>();



    public SysUser() {

    }

    public SysUser(Long userId) {
        this.userId = userId;
    }

    @Transient
    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    @Transient
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public SysDept getDept() {
        if (dept == null) {
            dept = new SysDept();
        }
        return dept;
    }
}

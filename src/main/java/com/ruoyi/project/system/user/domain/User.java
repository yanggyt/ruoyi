package com.ruoyi.project.system.user.domain;

import java.io.Serializable;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 用户对象 sys_user
 * 
 * @author yangzz
 */
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    // 用户ID
    private Long userId;
    // 部门ID
    private Long deptId;
    // 登录名
    private String loginName;
    // 用户名称
    private String userName;
    // 用户邮箱
    private String email;
    // 手机号码
    private String phonenumber;
    // 密码
    private String password;
    // 盐加密
    private String salt;
    // 帐号状态:0正常,1锁定,2黑名单,3禁止
    private String status;
    // 拒绝登录描述
    private String refuseDes;
    // 创建时间
    private String createTime;
    // 部门对象
    private Dept dept;
    // 角色对象
    private Role role;

    public User()
    {
    }

    public User(Long userId, String loginName, String password)
    {
        super();
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getRefuseDes()
    {
        return refuseDes;
    }

    public void setRefuseDes(String refuseDes)
    {
        this.refuseDes = refuseDes;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public Dept getDept()
    {
        return dept;
    }

    public void setDept(Dept dept)
    {
        this.dept = dept;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "{\"userId\":\"" + userId + "\",\"deptId\":\"" + deptId + "\",\"loginName\":\"" + loginName
                + "\",\"userName\":\"" + userName + "\",\"email\":\"" + email + "\",\"phonenumber\":\"" + phonenumber
                + "\",\"password\":\"" + password + "\",\"salt\":\"" + salt + "\",\"status\":\"" + status
                + "\",\"refuseDes\":\"" + refuseDes + "\",\"createTime\":\"" + createTime + "\"}  ";
    }

}

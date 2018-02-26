package com.ruoyi.project.system.user.domain;

import com.ruoyi.project.system.dept.domain.Dept;
import lombok.Data;

/**
 * 用户对象 sys_user
 * 
 * @author yangzz
 */
@Data
public class User
{
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

}

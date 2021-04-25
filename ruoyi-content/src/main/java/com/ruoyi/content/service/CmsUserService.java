package com.ruoyi.content.service;

import com.ruoyi.content.domain.CmsSysUser;
import com.ruoyi.content.message.Message;

import java.util.List;

/**
 * 说明：系统用户
 */
public interface CmsUserService {

    /**
     * 添加新用户
     */
    public Message insertUser(CmsSysUser user);

    /**
     * 删除用户
     */
    public Message delUser(String email);

    /**
     * 修改用户信息
     */
    public Message updateUser(CmsSysUser user);

    /**
     * 查询所有的用户,分页展示
     */
    public List<?> queryUser(int startRow, int rows, String email, String phone, String name, String companyId);

    /**
     * 查询单个用户
     */
    public CmsSysUser queryUserByEmail(String email);

    /**
     * 查询用户数量
     */
    public int countUser(String email, String phone, String name, String companyId);

    /**
     * 修改当前登录人的密码
     */
    public Message updataPassword(String newPass);

    /**
     * 判断原始密码是否正确
     */
    public Message checkOldPass(String oldPass);
}

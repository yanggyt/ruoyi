package com.ruoyi.project.system.user.service;

import java.util.List;
import com.ruoyi.common.utils.PageUtilEntity;
import com.ruoyi.project.system.user.domain.User;

/**
 * 用户 业务层
 * 
 * @author yangzz
 */
public interface IUserService
{

    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * @return 用户信息集合信息
     */
    public List<User> pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public User findByUserName(String userName);
}

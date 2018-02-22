package com.ruoyi.project.system.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.PageUtilEntity;
import com.ruoyi.project.system.user.dao.IUserDao;
import com.ruoyi.project.system.user.domain.User;

/**
 * 用户 业务层处理
 * 
 * @author y
 */
@Service("userService")
public class UserService implements IUserService
{

    @Autowired
    private IUserDao userDao;

    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * 
     * @return 用户信息集合信息
     */
    public List<User> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return userDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public User findByUserName(String userName)
    {
        return userDao.findByUserName(userName);
    }

}

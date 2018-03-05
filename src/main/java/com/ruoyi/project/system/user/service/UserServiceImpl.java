package com.ruoyi.project.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.user.dao.IUserDao;
import com.ruoyi.project.system.user.domain.User;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service("userService")
public class UserServiceImpl implements IUserService
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
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return userDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByName(String userName)
    {
        return userDao.selectUserByName(userName);
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public User selectUserById(Long userId)
    {
        return userDao.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId)
    {
        return userDao.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteUser(Long[] ids)
    {
        return userDao.batchDeleteUser(ids);
    }

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int saveUser(User user)
    {
        // 删除用户与角色关联
        // 新增用户与角色管理
        return userDao.updateUser(user);
    }

}

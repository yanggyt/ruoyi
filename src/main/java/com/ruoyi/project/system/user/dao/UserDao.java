package com.ruoyi.project.system.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruoyi.common.utils.PageUtilEntity;
import com.ruoyi.framework.core.dao.DynamicObjectBaseDao;
import com.ruoyi.project.system.user.domain.User;

/**
 * 用户 数据层处理
 * 
 * @author y
 */
@Repository("userDao")
public class UserDao extends DynamicObjectBaseDao implements IUserDao
{

    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * @return 用户对象信息
     */
    @SuppressWarnings("unchecked")
    public List<User> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<User> userPageInfo = null;
        try
        {
            userPageInfo = (List<User>) this.findForList("SystemUserMapper.queryUserListByCond", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;

    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public User findByUserName(String username)
    {
        return (User) this.findForObject("SystemUserMapper.findByUserName", username);
    }

}

package com.ruoyi.project.system.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruoyi.framework.core.dao.DynamicObjectBaseDao;
import com.ruoyi.framework.page.PageUtilEntity;
import com.ruoyi.project.system.user.domain.User;

/**
 * 用户 数据层处理
 * 
 * @author yangzz
 */
@Repository("userDao")
public class UserDaoImpl extends DynamicObjectBaseDao implements IUserDao
{

    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * @return 用户对象信息
     */
    @Override
    public List<User> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<User> userPageInfo = null;
        try
        {
            userPageInfo = this.findForList("SystemUserMapper.queryUserListByCond", pageUtilEntity);
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
    @Override
    public User selectByUserName(String username)
    {
        return this.findForObject("SystemUserMapper.selectByUserName", username);
    }

}

package com.ruoyi.project.system.user.dao;

import org.springframework.stereotype.Repository;
import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.user.domain.User;

/**
 * 用户 数据层处理
 * 
 * @author ruoyi
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
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemUserMapper.queryUserListByCond", pageUtilEntity);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByName(String username)
    {
        return this.findForObject("SystemUserMapper.selectUserByName", username);
    }

}

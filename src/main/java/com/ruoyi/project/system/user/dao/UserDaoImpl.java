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
        return this.findForList("SystemUserMapper.pageInfoQuery", pageUtilEntity);
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

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId)
    {
        return this.findForObject("SystemUserMapper.selectUserById", userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        return this.delete("SystemUserMapper.deleteUserById", userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteUser(Long[] ids)
    {
        return this.delete("SystemUserMapper.batchDeleteUser", ids);
    }

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(User user)
    {
        return this.save("SystemUserMapper.updateUser", user);
    }

}

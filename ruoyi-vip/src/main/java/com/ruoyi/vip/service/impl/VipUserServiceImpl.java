package com.ruoyi.vip.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.vip.domain.VipUser;
import com.ruoyi.vip.mapper.VipUserMapper;
import com.ruoyi.vip.service.IVipUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
public class VipUserServiceImpl extends AbstractBaseServiceImpl<VipUserMapper, VipUser> implements IVipUserService {
    @Autowired
    private VipUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * 根据条件分页查询用户对象
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(tableAlias = "u")
    public List<VipUser> selectUserList(VipUser user) {
        startPage();
        return userMapper.selectUserList( user );
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public VipUser selectUserByLoginName(String userName) {
        return userMapper.selectUserByLoginName( userName );
    }

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    @Override
    public VipUser selectUserByPhoneNumber(String phoneNumber) {
        return userMapper.selectUserByPhoneNumber( phoneNumber );
    }

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public VipUser selectUserByEmail(String email) {
        return userMapper.selectUserByEmail( email );
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public VipUser selectUserById(Long userId) {
        return userMapper.selectUserById( userId );
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId) {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId( userId );
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId( userId );
        return userMapper.deleteUserById( userId );
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws Exception {
        Long[] userIds = Convert.toLongArray( ids );
        return userMapper.deleteUserByIds( userIds );
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(VipUser user) {
        // 新增用户信息
        int rows = userMapper.insertUser( user );
        return rows;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(VipUser user) {
        Long userId = user.getId();
        return userMapper.updateUser( user );
    }

    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(VipUser user) {
        return userMapper.updateUser( user );
    }

    /**
     * 修改用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(VipUser user) {
        return updateUserInfo( user );
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName) {
        int count = userMapper.checkLoginNameUnique( loginName );
        if (count > 0) {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(VipUser user) {
        Long userId = StringUtils.isNull( user.getId() ) ? -1L : user.getId();
        VipUser info = userMapper.checkPhoneUnique( user.getPhonenumber() );
        if (StringUtils.isNotNull( info ) && info.getId().longValue() != userId.longValue()) {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(VipUser user) {
        Long userId = StringUtils.isNull( user.getId() ) ? -1L : user.getId();
        VipUser info = userMapper.checkEmailUnique( user.getEmail() );
        if (StringUtils.isNotNull( info ) && info.getId().longValue() != userId.longValue()) {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }


}

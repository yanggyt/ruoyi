package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.repository.SysUserRepository;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private ISysConfigService configService;

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @param pageRequest
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public Page<SysUser> selectUserList(SysUser user, Pageable pageRequest) {
        return sysUserRepository.findAll(getSpecification(user), pageRequest);
    }

    private Specification<SysUser> getSpecification(SysUser sysUser){
        return new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotEmpty(sysUser.getDelFlag())){
                    predicates.add(criteriaBuilder.equal(root.get("delFlag").as(String.class), sysUser.getDelFlag()));
                }
                if(StringUtils.isNotEmpty(sysUser.getLoginName())){
                    predicates.add(criteriaBuilder.like(root.get("loginName").as(String.class), "%" + sysUser.getLoginName() + "%"));
                }
                if(StringUtils.isNotEmpty(sysUser.getStatus())){
                    predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), sysUser.getStatus()));
                }
                if(StringUtils.isNotEmpty(sysUser.getPhonenumber())){
                    predicates.add(criteriaBuilder.like(root.get("phonenumber").as(String.class), "%" + sysUser.getPhonenumber() + "%"));
                }
                if(sysUser.getStartTime() != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), sysUser.getStartTime()));
                }
                if(sysUser.getEndTime() != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime").as(Date.class), sysUser.getEndTime()));
                }
                if(sysUser.getDept() != null && sysUser.getDept().getDeptId() != null){
                    predicates.add(criteriaBuilder.equal(root.get("dept").get("deptId").as(Long.class), sysUser.getDept().getDeptId()));
                }
                if(sysUser.getDept() != null && StringUtils.isNotEmpty(sysUser.getDept().getCode())){
                    predicates.add(criteriaBuilder.equal(root.get("dept").get("code").as(String.class), "%" + sysUser.getDept().getCode() + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param user 用户信息
     * @param pageRequest
     * @return 用户信息集合信息
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public Page<SysUser> selectAllocatedList(SysUser user, Pageable pageRequest) {
        return sysUserRepository.findAll(getSpecification(user), pageRequest);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public Page<SysUser> selectUnallocatedList(SysUser sysUser, Pageable pageable) {
        return sysUserRepository.findAll(new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotEmpty(sysUser.getLoginName())){
                    predicates.add(criteriaBuilder.like(root.get("loginName").as(String.class), "%" + sysUser.getLoginName() + "%"));
                }
                if(StringUtils.isNotEmpty(sysUser.getPhonenumber())){
                    predicates.add(criteriaBuilder.like(root.get("phonenumber").as(String.class), "%" + sysUser.getPhonenumber() + "%"));
                }
                if(sysUser.getRoles() != null && sysUser.getRoles().size() == 1){
                    SysRole role = sysUser.getRoles().iterator().next();
                    Predicate notMember = criteriaBuilder.isNotMember(role, root.get("roles").as(Set.class));
                    predicates.add(notMember);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByLoginName(String userName) {
        return sysUserRepository.findFirstByDelFlagAndLoginName(BaseEntity.NOT_DELETED, userName);
    }

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        return sysUserRepository.findFirstByDelFlagAndAndPhonenumber(BaseEntity.NOT_DELETED, phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByEmail(String email) {
        return sysUserRepository.findFirstByDelFlagAndEmail(BaseEntity.NOT_DELETED, email);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return sysUserRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("无效的数据"));
    }

    @Override
    public SysUser selectUserWithRolesAndPostsById(Long userId) {
        return sysUserRepository.findSysUserByDelFlagAndUserId(BaseEntity.NOT_DELETED, userId);
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteUserById(Long userId) {
        sysUserRepository.deleteById(userId);
        return 1;
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteUserByIds(String ids) throws BusinessException {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds) {
            checkUserAllowed(new SysUser(userId));
            deleteUserById(userId);
        }
        return userIds.length;
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public SysUser insertUser(SysUser user) {
        return sysUserRepository.save(user);
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public SysUser updateUser(SysUser user) {
        return sysUserRepository.save(user);
    }

    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public SysUser updateUserInfo(SysUser user) {
        return sysUserRepository.save(user);
    }

    /**
     * 修改用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public SysUser resetUserPwd(SysUser user) {
        return updateUserInfo(user);
    }

    /**
     * 校验登录名称是否唯一
     *
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName) {
        int count = sysUserRepository.countByLoginName(loginName);
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
    public String checkPhoneUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = sysUserRepository.findFirstByPhonenumber(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
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
    public String checkEmailUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = sysUserRepository.findFirstByEmail(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    public void checkUserAllowed(SysUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
            throw new BusinessException("不允许操作超级管理员用户");
        }
    }

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (SysUser user : userList) {
            try {
                // 验证是否存在这个用户
                SysUser u = sysUserRepository.findFirstByLoginName(user.getLoginName());
                if (StringUtils.isNull(u)) {
                    user.setPassword(Md5Utils.hash(user.getLoginName() + password));
                    user.setCreateBy(operName);
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getLoginName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getLoginName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public void changeStatus(SysUser user) {
        sysUserRepository.changeStatus(user.getStatus(), user.getUserId());
    }
}

package com.ruoyi.system.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.QSysRole;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.repository.SysRoleRepository;
import com.ruoyi.system.repository.SysUserRepository;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.base.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色 业务层处理
 *
 * @author ruoyi
 */
@DataScope(userFieldName = "user")
@Service
public class SysRoleServiceImpl extends BusinessService implements ISysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @param user
     * @return 角色数据集合信息
     */
    @Override
    public Page<SysRole> selectRoleList(SysRole role, Pageable pageable, SysUser user) {
        return sysRoleRepository.findAll(getPredicate(role, user), pageable);
    }

    public Predicate getPredicate(SysRole role, SysUser user){
        QSysRole qSysRole = QSysRole.sysRole;
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(buildEqual(qSysRole.delFlag, BaseEntity.NOT_DELETED));
        if(StringUtils.isNotEmpty(role.getRoleName())){
            predicates.add(buildLike(qSysRole.roleName, role.getRoleName()));
        }
        if(StringUtils.isNotEmpty(role.getStatus())){
            predicates.add(buildEqual(qSysRole.status, role.getStatus()));
        }
        if(StringUtils.isNotEmpty(role.getRoleKey())){
            predicates.add(buildLike(qSysRole.roleKey, role.getRoleKey()));
        }
        if(role.getDataScope() != null){
            predicates.add(buildEqual(qSysRole.dataScope, role.getDataScope()));
        }
        if(role.getStartTime() != null){
            predicates.add(buildGreaterThanOrEqualTo(qSysRole.createTime, role.getStartTime()));
        }
        if(role.getEndTime() != null){
            predicates.add(buildLessThanOrEqualTo(qSysRole.createTime, role.getEndTime()));
        }
//        predicates.add(buildDataPermission(qSysRole.depts, user.getUserId()));
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId) {
        SysUser sysUser = sysUserRepository.findSysUserByDelFlagAndUserId(BaseEntity.NOT_DELETED, userId);
        Set<SysRole> roles = sysUser.getRoles();
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : roles) {
            permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public Set<SysRole> selectRolesByUserId(Long userId) {
        SysUser sysUser = sysUserRepository.findSysUserByDelFlagAndUserId(BaseEntity.NOT_DELETED, userId);
        return sysUser.getRoles();
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRoleAll() {
        return sysRoleRepository.findAll(getPredicate(new SysRole(), new SysUser()), Pageable.unpaged()).getContent();
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public SysRole selectRoleById(Long roleId) {
        return sysRoleRepository.findById(roleId).get();
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Transactional
    @Override
    public boolean deleteRoleById(Long roleId) {
        sysRoleRepository.deleteById(roleId);
        return true;
    }

    /**
     * 批量删除角色信息
     *
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Transactional
    @Override
    public int deleteRoleByIds(String ids) throws BusinessException {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            checkRoleAllowed(new SysRole(roleId));
            SysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
            deleteRoleById(roleId);
        }
        return roleIds.length;
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public SysRole insertRole(SysRole role) {
        return sysRoleRepository.save(role);
    }

    /**
     * 修改保存角色信息
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public SysRole updateRole(SysRole role) {
        return sysRoleRepository.save(role);
    }

    /**
     * 修改数据权限信息
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int authDataScope(SysRole role) {
        SysRole db = sysRoleRepository.findById(role.getRoleId()).get();
        db.setDataScope(role.getDataScope());
        db.setDepts(role.getDepts());
        return 1;
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = sysRoleRepository.findFirstByRoleName(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = sysRoleRepository.findFirstByRoleKey(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    public void checkRoleAllowed(SysRole role) {
        if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin()) {
            throw new BusinessException("不允许操作超级管理员角色");
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId) {
        return sysUserRepository.countByRolesContaining(new SysRole(roleId));
    }

    /**
     * 角色状态修改
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int changeStatus(SysRole role) {
        sysRoleRepository.updateStatus(role.getStatus(), role.getRoleId());
        return 1;
    }

    /**
     * 取消授权用户角色
     *
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAuthUser(SysUserRole userRole) {
        SysUser sysUser = sysUserRepository.findSysUserByDelFlagAndUserId(BaseEntity.NOT_DELETED, userRole.getUserId());
        Set<SysRole> roles = sysUser.getRoles();
        roles.remove(new SysRole(userRole.getRoleId()));
        return 1;
    }

    /**
     * 批量取消授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int deleteAuthUsers(Long roleId, String userIds) {
        SysRole sysRole = sysRoleRepository.findById(roleId).get();
        Long[] users = Convert.toLongArray(userIds);
        for (Long userId : users) {
            SysUser sysUser = sysUserRepository.findSysUserByDelFlagAndUserId(BaseEntity.NOT_DELETED, userId);
            sysUser.getRoles().remove(sysRole);
        }
        return 1;
    }

    /**
     * 批量选择授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    @Transactional
    public int insertAuthUsers(Long roleId, String userIds) {
        SysRole sysRole = sysRoleRepository.findById(roleId).get();
        Long[] users = Convert.toLongArray(userIds);
        for (Long userId : users) {
            SysUser sysUser = sysUserRepository.findSysUserByDelFlagAndUserId(BaseEntity.NOT_DELETED, userId);
            sysUser.getRoles().add(sysRole);
        }
        return 1;
    }
}

package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.repository.SysDeptRepository;
import com.ruoyi.system.repository.SysRoleRepository;
import com.ruoyi.system.repository.SysUserRepository;
import com.ruoyi.system.service.ISysDeptService;
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
import java.util.List;

/**
 * 部门管理 服务实现
 *
 * @author ruoyi
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService {
    @Autowired
    private SysDeptRepository sysDeptRepository;
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    public Page<SysDept> selectDeptList(SysDept dept, Pageable pageable) {
        return sysDeptRepository.findAll(getSpecification(dept), pageable);
    }

    private Specification<SysDept> getSpecification(SysDept sysDept){
        return new Specification<SysDept>() {
            @Override
            public Predicate toPredicate(Root<SysDept> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotEmpty(sysDept.getDelFlag())){
                    predicates.add(criteriaBuilder.equal(root.get("delFlag").as(String.class), sysDept.getDelFlag()));
                }
                if(StringUtils.isNotEmpty(sysDept.getStatus())){
                    predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), sysDept.getStatus()));
                }
                if(StringUtils.isNotEmpty(sysDept.getDeptName())){
                    predicates.add(criteriaBuilder.equal(root.get("deptName").as(String.class), "%" + sysDept.getDeptName() + "%"));
                }
                if(sysDept.getParent() != null && sysDept.getParent().getDeptId() != null){
                    predicates.add(criteriaBuilder.equal(root.get("parent").get("deptId").as(Long.class), sysDept.getParent().getDeptId()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    /**
     * 查询部门管理树
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    @Override
    public List<Ztree> selectDeptTree(SysDept dept) {
        List<SysDept> deptList = sysDeptRepository.findAll(getSpecification(dept));
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }

    /**
     * 根据角色ID查询部门（数据权限）
     *
     * @param role 角色对象
     * @return 部门列表（数据权限）
     */
    @Override
    public List<Ztree> roleDeptTreeData(SysRole role) {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees;
        List<SysDept> deptList = sysDeptRepository.findAll(getSpecification(new SysDept()));
        if (StringUtils.isNotNull(roleId)) {
            SysRole sysRole = sysRoleRepository.findById(roleId).get();
            ztrees = initZtree(deptList, sysRole.getDepts());
        } else {
            ztrees = initZtree(deptList);
        }
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysDept> deptList) {
        return initZtree(deptList, null);
    }

    /**
     * 对象转部门树
     *
     * @param deptList     部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysDept> deptList, List<SysDept> roleDeptList) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (SysDept dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(dept.getDeptId());
                ztree.setpId(dept.getParentId());
                ztree.setName(dept.getDeptName());
                ztree.setTitle(dept.getDeptName());
                if (isCheck) {
                    ztree.setChecked(roleDeptList.contains(new SysDept(dept.getDeptId())));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询下级部门数量
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int countChildren(Long deptId) {
        return sysDeptRepository.countByDelFlagAndParent(BaseEntity.NOT_DELETED, new SysDept(deptId));
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return sysUserRepository.countByDelFlagAndDept(BaseEntity.NOT_DELETED, new SysDept(deptId)) > 0;
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Transactional
    @Override
    public void deleteDeptById(Long deptId) {
        sysDeptRepository.updateDelFlagByDeptId(BaseEntity.DELETED, deptId);
    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Transactional
    @Override
    public SysDept insertDept(SysDept dept) {
        SysDept info = sysDeptRepository.findById(dept.getParentId()).get();
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
            throw new BusinessException("部门停用，不允许新增");
        }
        setDeptCode(dept);
        return sysDeptRepository.save(dept);
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    @Transactional
    public SysDept updateDept(SysDept dept) {
        setDeptCode(dept);
        sysDeptRepository.save(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return dept;
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept) {
        SysDept parent = null;
        while ( (parent = dept.getParent()) != null ){
            sysDeptRepository.updateStatusByDeptId(SysDept.STATUS_NORMAL, dept.getDeptId());
            dept = parent;
        }
    }


    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SysDept selectDeptById(Long deptId) {
        return sysDeptRepository.findById(deptId).orElseThrow(() -> new IllegalArgumentException("无效的数据"));
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(SysDept dept) {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        SysDept info = sysDeptRepository.findFirstByDelFlagAndDeptNameAndParent(BaseEntity.NOT_DELETED, dept.getDeptName(), dept.getParent());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    public void setDeptCode(SysDept dept){
        SysDept parent = dept.getParent();
        String parentCode = parent.getCode();
        SysDept max = sysDeptRepository.findFirstByParentOrderByCodeDesc(parent);
        int next = 001;
        if(max != null){
            String maxCode = max.getCode();
            String sequence = maxCode.substring(maxCode.length() - 3);
            next = Integer.parseInt(sequence) + 1;
        }
        String code = String.format("%s%03d", parentCode, next);
        dept.setCode(code);
    }
}

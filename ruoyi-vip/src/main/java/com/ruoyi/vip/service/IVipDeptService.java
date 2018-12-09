package com.ruoyi.vip.service;

import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.vip.domain.VipDept;

import java.util.List;
import java.util.Map;

/**
 * 部门管理 服务层
 * 
 * @author ruoyi
 */
public interface IVipDeptService extends AbstractBaseService<VipDept>
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<VipDept> selectDeptList(VipDept dept);

    /**
     * 查询部门管理树
     * 
     * @return 所有部门信息
     */
    public List<Map<String, Object>> selectDeptTree();



    /**
     * 查询部门人数
     * 
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectDeptCount(Long parentId);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(VipDept dept);

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(VipDept dept);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    public VipDept selectDeptById(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public String checkDeptNameUnique(VipDept dept);
}

package com.ruoyi.project.system.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ruoyi.project.system.dept.dao.IDeptDao;
import com.ruoyi.project.system.dept.domain.Dept;

/**
 * 部门管理 服务实现
 * 
 * @author ruoyi
 */
@Repository("deptService")
public class DeptServiceImpl implements IDeptService
{
    @Autowired
    private IDeptDao deptDao;

    /**
     * 新增部门管理
     * 
     * @param dept 部门对象
     */
    public int insertDept(Dept dept)
    {
        return deptDao.insertDept(dept);
    }

    /**
     * 查询部门管理集合
     * 
     * @return 所有部门信息
     */
    public List<Dept> selectDeptAll()
    {
        return deptDao.selectDeptAll();
    }

    /**
     * 查询部门人数
     * 
     * @param parentId 部门ID
     * @return 结果
     */
    public int selectDeptCount(Long parentId)
    {
        Dept dept = new Dept();
        dept.setParentId(parentId);
        return deptDao.selectDeptCount(dept);
    }

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId)
    {
        int result = deptDao.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId)
    {
        return deptDao.deleteDeptById(deptId);
    }

    /**
     * 保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int saveDept(Dept dept)
    {
        return deptDao.saveDept(dept);
    }

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    public Dept selectDeptById(Long deptId)
    {
        return deptDao.selectDeptById(deptId);
    }
}

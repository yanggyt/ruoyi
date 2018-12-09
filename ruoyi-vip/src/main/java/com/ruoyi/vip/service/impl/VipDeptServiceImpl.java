package com.ruoyi.vip.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.vip.domain.VipDept;
import com.ruoyi.vip.mapper.VipDeptMapper;
import com.ruoyi.vip.service.IVipDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理 服务实现
 *
 * @author ruoyi
 */
@Service
public class VipDeptServiceImpl extends AbstractBaseServiceImpl<VipDeptMapper, VipDept> implements IVipDeptService {
    @Autowired
    private VipDeptMapper deptMapper;

    /**
     * 查询部门管理数据
     *
     * @return 部门信息集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<VipDept> selectDeptList(VipDept dept) {
        return mapper.selectDeptList( dept );
    }

    /**
     * 查询部门管理树
     *
     * @return 所有部门信息
     */
    @Override
    public List<Map<String, Object>> selectDeptTree() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<VipDept> deptList = selectDeptList( new VipDept() );
        trees = getTrees( deptList, false, null );
        return trees;
    }


    /**
     * 对象转部门树
     *
     * @param deptList     部门列表
     * @param isCheck      是否需要选中
     * @param roleDeptList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<VipDept> deptList, boolean isCheck, List<String> roleDeptList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (VipDept dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals( dept.getDelFlag() )) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put( "id", dept.getDeptId() );
                deptMap.put( "pId", dept.getParentId() );
                deptMap.put( "name", dept.getDeptName() );
                deptMap.put( "title", dept.getDeptName() );
                if (isCheck) {
                    deptMap.put( "checked", roleDeptList.contains( dept.getDeptId() + dept.getDeptName() ) );
                } else {
                    deptMap.put( "checked", false );
                }
                trees.add( deptMap );
            }
        }
        return trees;
    }

    /**
     * 查询部门人数
     *
     * @param parentId 部门ID
     * @return 结果
     */
    @Override
    public int selectDeptCount(Long parentId) {
        VipDept dept = new VipDept();
        dept.setParentId( parentId );
        return deptMapper.selectDeptCount( dept );
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = deptMapper.checkDeptExistUser( deptId );
        return result > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId) {
        return deptMapper.deleteDeptById( deptId );
    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(VipDept dept) {
        VipDept info = deptMapper.selectDeptById( dept.getParentId() );
        dept.setParentIds( info.getParentIds() + "," + dept.getParentId() );
        return deptMapper.insertDept( dept );
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(VipDept dept) {
        VipDept info = deptMapper.selectDeptById( dept.getParentId() );
        if (StringUtils.isNotNull( info )) {
            String ancestors = info.getParentIds() + "," + dept.getParentId();
            dept.setParentIds( ancestors );
            updateDeptChildren( dept.getDeptId(), ancestors );
        }
        return deptMapper.updateDept( dept );
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    部门ID
     * @param ancestors 元素列表
     */
    public void updateDeptChildren(Long deptId, String ancestors) {
        VipDept dept = new VipDept();
        dept.setParentId( deptId );
        List<VipDept> childrens = deptMapper.selectDeptList( dept );
        for (VipDept children : childrens) {
            children.setParentIds( ancestors + "," + dept.getParentId() );
        }
        if (childrens.size() > 0) {
            deptMapper.updateDeptChildren( childrens );
        }
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public VipDept selectDeptById(Long deptId) {
        return deptMapper.selectDeptById( deptId );
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(VipDept dept) {
        Long deptId = StringUtils.isNull( dept.getDeptId() ) ? -1L : dept.getDeptId();
        VipDept info = deptMapper.checkDeptNameUnique( dept.getDeptName(), dept.getParentId() );
        if (StringUtils.isNotNull( info ) && info.getDeptId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }
}

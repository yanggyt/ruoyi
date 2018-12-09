package com.ruoyi.course.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.course.domain.TrainCourseCategory;
import com.ruoyi.course.mapper.TrainCourseCategoryMapper;
import com.ruoyi.course.service.ITrainCourseCategoryService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
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
public class TrainCourseCategoryServiceImpl extends AbstractBaseServiceImpl<TrainCourseCategoryMapper, TrainCourseCategory> implements ITrainCourseCategoryService {
    @Autowired
    private TrainCourseCategoryMapper trainCourseCategoryMapper;
    /**
     * 查询部门管理数据
     *
     * @return 部门信息集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<TrainCourseCategory> selectDeptList(TrainCourseCategory dept) {
        return trainCourseCategoryMapper.selectDeptList( dept );
    }

    /**
     * 查询部门管理树
     *
     * @return 所有部门信息
     */
    @Override
    public List<Map<String, Object>> selectDeptTree() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<TrainCourseCategory> deptList = selectDeptList( new TrainCourseCategory() );
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
    public List<Map<String, Object>> getTrees(List<TrainCourseCategory> deptList, boolean isCheck, List<String> roleDeptList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (TrainCourseCategory dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals( dept.getDelFlag() )) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put( "id", dept.getId() );
                deptMap.put( "pId", dept.getParentId() );
                deptMap.put( "name", dept.getName() );
                deptMap.put( "title", dept.getName() );
                if (isCheck) {
                    deptMap.put( "checked", roleDeptList.contains( dept.getDeptId() + dept.getName() ) );
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
        TrainCourseCategory dept = new TrainCourseCategory();
        dept.setParentId( parentId );
        return trainCourseCategoryMapper.selectDeptCount( dept );
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = trainCourseCategoryMapper.checkDeptExistUser( deptId );
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
        return trainCourseCategoryMapper.deleteDeptById( deptId );
    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(TrainCourseCategory dept) {
        TrainCourseCategory info = trainCourseCategoryMapper.selectDeptById( dept.getParentId() );
        dept.setParentIds( info.getParentIds() + "," + dept.getParentId() );
        return trainCourseCategoryMapper.insertDept( dept );
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(TrainCourseCategory dept) {
        TrainCourseCategory info = trainCourseCategoryMapper.selectDeptById( dept.getParentId() );
        if (StringUtils.isNotNull( info )) {
            String ancestors = info.getParentIds() + "," + dept.getParentId();
            dept.setParentIds( ancestors );
            updateDeptChildren( dept.getDeptId(), ancestors );
        }
        return trainCourseCategoryMapper.updateDept( dept );
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    部门ID
     * @param ancestors 元素列表
     */
    public void updateDeptChildren(Long deptId, String ancestors) {
        TrainCourseCategory dept = new TrainCourseCategory();
        dept.setParentId( deptId );
        List<TrainCourseCategory> childrens = trainCourseCategoryMapper.selectDeptList( dept );
        for (TrainCourseCategory children : childrens) {
            children.setParentIds( ancestors + "," + dept.getParentId() );
        }
        if (childrens.size() > 0) {
            trainCourseCategoryMapper.updateDeptChildren( childrens );
        }
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public TrainCourseCategory selectDeptById(Long deptId) {
        return trainCourseCategoryMapper.selectDeptById( deptId );
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(TrainCourseCategory dept) {
        Long deptId = StringUtils.isNull( dept.getDeptId() ) ? -1L : dept.getDeptId();
        TrainCourseCategory info = trainCourseCategoryMapper.checkDeptNameUnique( dept.getName(), dept.getParentId() );
        if (StringUtils.isNotNull( info ) && info.getDeptId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }
}

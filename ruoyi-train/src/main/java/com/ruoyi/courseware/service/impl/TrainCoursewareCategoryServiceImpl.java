package com.ruoyi.courseware.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.courseware.domain.TrainCoursewareCategory;
import com.ruoyi.courseware.mapper.TrainCoursewareCategoryMapper;
import com.ruoyi.courseware.service.ITrainCoursewareCategoryService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课件分类管理 服务实现
 *
 * @author ruoyi
 */
@Service
public class TrainCoursewareCategoryServiceImpl extends AbstractBaseServiceImpl<TrainCoursewareCategoryMapper, TrainCoursewareCategory> implements ITrainCoursewareCategoryService {
    @Autowired
    private TrainCoursewareCategoryMapper trainCoursewareCategoryMapper;
    /**
     * 查询课件分类管理数据
     *
     * @return 课件分类信息集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<TrainCoursewareCategory> selectDeptList(TrainCoursewareCategory dept) {
        return trainCoursewareCategoryMapper.selectDeptList( dept );
    }

    /**
     * 查询课件分类管理树
     *
     * @return 所有课件分类信息
     */
    @Override
    public List<Map<String, Object>> selectDeptTree() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<TrainCoursewareCategory> deptList = selectDeptList( new TrainCoursewareCategory() );
        trees = getTrees( deptList, false, null );
        return trees;
    }


    /**
     * 对象转课件分类树
     *
     * @param deptList     课件分类列表
     * @param isCheck      是否需要选中
     * @param roleDeptList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<TrainCoursewareCategory> deptList, boolean isCheck, List<String> roleDeptList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (TrainCoursewareCategory dept : deptList) {
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
     * 查询课件分类人数
     *
     * @param parentId 课件分类ID
     * @return 结果
     */
    @Override
    public int selectDeptCount(Long parentId) {
        TrainCoursewareCategory dept = new TrainCoursewareCategory();
        dept.setParentId( parentId );
        return trainCoursewareCategoryMapper.selectDeptCount( dept );
    }

    /**
     * 查询课件分类是否存在用户
     *
     * @param deptId 课件分类ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = trainCoursewareCategoryMapper.checkDeptExistUser( deptId );
        return result > 0 ? true : false;
    }

    /**
     * 删除课件分类管理信息
     *
     * @param deptId 课件分类ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId) {
        return trainCoursewareCategoryMapper.deleteDeptById( deptId );
    }

    /**
     * 新增保存课件分类信息
     *
     * @param dept 课件分类信息
     * @return 结果
     */
    @Override
    public int insertDept(TrainCoursewareCategory dept) {
        TrainCoursewareCategory info = trainCoursewareCategoryMapper.selectDeptById( dept.getParentId() );
        dept.setParentIds( info.getParentIds() + "," + dept.getParentId() );
        return trainCoursewareCategoryMapper.insertDept( dept );
    }

    /**
     * 修改保存课件分类信息
     *
     * @param dept 课件分类信息
     * @return 结果
     */
    @Override
    public int updateDept(TrainCoursewareCategory dept) {
        TrainCoursewareCategory info = trainCoursewareCategoryMapper.selectDeptById( dept.getParentId() );
        if (StringUtils.isNotNull( info )) {
            String ancestors = info.getParentIds() + "," + dept.getParentId();
            dept.setParentIds( ancestors );
            updateDeptChildren( dept.getDeptId(), ancestors );
        }
        return trainCoursewareCategoryMapper.updateDept( dept );
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    课件分类ID
     * @param ancestors 元素列表
     */
    public void updateDeptChildren(Long deptId, String ancestors) {
        TrainCoursewareCategory dept = new TrainCoursewareCategory();
        dept.setParentId( deptId );
        List<TrainCoursewareCategory> childrens = trainCoursewareCategoryMapper.selectDeptList( dept );
        for (TrainCoursewareCategory children : childrens) {
            children.setParentIds( ancestors + "," + dept.getParentId() );
        }
        if (childrens.size() > 0) {
            trainCoursewareCategoryMapper.updateDeptChildren( childrens );
        }
    }

    /**
     * 根据课件分类ID查询信息
     *
     * @param deptId 课件分类ID
     * @return 课件分类信息
     */
    @Override
    public TrainCoursewareCategory selectDeptById(Long deptId) {
        return trainCoursewareCategoryMapper.selectDeptById( deptId );
    }

    /**
     * 校验课件分类名称是否唯一
     *
     * @param dept 课件分类信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(TrainCoursewareCategory dept) {
        Long deptId = StringUtils.isNull( dept.getDeptId() ) ? -1L : dept.getDeptId();
        TrainCoursewareCategory info = trainCoursewareCategoryMapper.checkDeptNameUnique( dept.getName(), dept.getParentId() );
        if (StringUtils.isNotNull( info ) && info.getDeptId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }
}

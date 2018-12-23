package com.ruoyi.train.courseware.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.train.courseware.domain.TrainCoursewareCategory;
import com.ruoyi.train.courseware.mapper.TrainCoursewareCategoryMapper;
import com.ruoyi.train.courseware.service.ITrainCoursewareCategoryService;
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
    public List<TrainCoursewareCategory> selectCategoryList(TrainCoursewareCategory dept) {
        return trainCoursewareCategoryMapper.selectCategoryList( dept );
    }

    /**
     * 查询课件分类管理树
     *
     * @return 所有课件分类信息
     */
    @Override
    public List<Map<String, Object>> selectCategoryTree() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<TrainCoursewareCategory> deptList = selectCategoryList( new TrainCoursewareCategory() );
        trees = getTrees( deptList, false, null );
        return trees;
    }


    /**
     * 对象转课件分类树
     *
     * @param deptList     课件分类列表
     * @param isCheck      是否需要选中
     * @param roleCategoryList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<TrainCoursewareCategory> deptList, boolean isCheck, List<String> roleCategoryList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (TrainCoursewareCategory dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals( dept.getDelFlag() )) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put( "id", dept.getId() );
                deptMap.put( "pId", dept.getParentId() );
                deptMap.put( "name", dept.getName() );
                deptMap.put( "title", dept.getName() );
                if (isCheck) {
                    deptMap.put( "checked", roleCategoryList.contains( dept.getId() + dept.getName() ) );
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
    public int selectCategoryCount(Long parentId) {
        TrainCoursewareCategory dept = new TrainCoursewareCategory();
        dept.setParentId( parentId );
        return trainCoursewareCategoryMapper.selectCategoryCount( dept );
    }

    /**
     * 查询课件分类是否存在用户
     *
     * @param deptId 课件分类ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkCategoryExistCourseware(Long deptId) {
        int result = trainCoursewareCategoryMapper.checkCategoryExistCourseware( deptId );
        return result > 0 ? true : false;
    }

    /**
     * 删除课件分类管理信息
     *
     * @param deptId 课件分类ID
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long deptId) {
        return trainCoursewareCategoryMapper.deleteCategoryById( deptId );
    }

    /**
     * 新增保存课件分类信息
     *
     * @param dept 课件分类信息
     * @return 结果
     */
    @Override
    public int insertCategory(TrainCoursewareCategory dept) {
        TrainCoursewareCategory info = trainCoursewareCategoryMapper.selectCategoryById( dept.getParentId() );
        dept.setParentIds( info.getParentIds() + "," + dept.getParentId() );
        return trainCoursewareCategoryMapper.insertCategory( dept );
    }

    /**
     * 修改保存课件分类信息
     *
     * @param category 课件分类信息
     * @return 结果
     */
    @Override
    public int updateCategory(TrainCoursewareCategory category) {
        TrainCoursewareCategory info = trainCoursewareCategoryMapper.selectCategoryById( category.getParentId() );
        if (StringUtils.isNotNull( info )) {
            String ancestors = info.getParentIds() + "," + category.getParentId();
            category.setParentIds( ancestors );
            updateCategoryChildren( category.getId(), ancestors );
        }
        return trainCoursewareCategoryMapper.updateCategory( category );
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    课件分类ID
     * @param ancestors 元素列表
     */
    public void updateCategoryChildren(Long deptId, String ancestors) {
        TrainCoursewareCategory dept = new TrainCoursewareCategory();
        dept.setParentId( deptId );
        List<TrainCoursewareCategory> childrens = trainCoursewareCategoryMapper.selectCategoryList( dept );
        for (TrainCoursewareCategory children : childrens) {
            children.setParentIds( ancestors + "," + dept.getParentId() );
        }
        if (childrens.size() > 0) {
            trainCoursewareCategoryMapper.updateCategoryChildren( childrens );
        }
    }

    /**
     * 根据课件分类ID查询信息
     *
     * @param deptId 课件分类ID
     * @return 课件分类信息
     */
    @Override
    public TrainCoursewareCategory selectCategoryById(Long id) {
        return trainCoursewareCategoryMapper.selectCategoryById( id );
    }

    /**
     * 校验课件分类名称是否唯一
     *
     * @param dept 课件分类信息
     * @return 结果
     */
    @Override
    public String checkCategoryNameUnique(TrainCoursewareCategory dept) {
        Long deptId = StringUtils.isNull( dept.getId() ) ? -1L : dept.getId();
        TrainCoursewareCategory info = trainCoursewareCategoryMapper.checkCategoryNameUnique( dept.getName(), dept.getParentId() );
        if (StringUtils.isNotNull( info ) && info.getId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }
}

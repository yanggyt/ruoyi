package com.ruoyi.courseware.service;

import com.ruoyi.courseware.domain.TrainCoursewareCategory;
import com.ruoyi.framework.web.base.AbstractBaseService;

import java.util.List;
import java.util.Map;

/**
 * 课件分类管理 服务层
 * 
 * @author ruoyi
 */
public interface ITrainCoursewareCategoryService extends AbstractBaseService<TrainCoursewareCategory>
{
    /**
     * 查询课件分类管理数据
     * 
     * @param dept 课件分类信息
     * @return 课件分类信息集合
     */
    public List<TrainCoursewareCategory> selectDeptList(TrainCoursewareCategory dept);

    /**
     * 查询课件分类管理树
     * 
     * @return 所有课件分类信息
     */
    public List<Map<String, Object>> selectDeptTree();



    /**
     * 查询课件分类人数
     * 
     * @param parentId 父课件分类ID
     * @return 结果
     */
    public int selectDeptCount(Long parentId);

    /**
     * 查询课件分类是否存在用户
     * 
     * @param deptId 课件分类ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 删除课件分类管理信息
     * 
     * @param deptId 课件分类ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增保存课件分类信息
     * 
     * @param dept 课件分类信息
     * @return 结果
     */
    public int insertDept(TrainCoursewareCategory dept);

    /**
     * 修改保存课件分类信息
     * 
     * @param dept 课件分类信息
     * @return 结果
     */
    public int updateDept(TrainCoursewareCategory dept);

    /**
     * 根据课件分类ID查询信息
     * 
     * @param deptId 课件分类ID
     * @return 课件分类信息
     */
    public TrainCoursewareCategory selectDeptById(Long deptId);

    /**
     * 校验课件分类名称是否唯一
     * 
     * @param dept 课件分类信息
     * @return 结果
     */
    public String checkDeptNameUnique(TrainCoursewareCategory dept);
}

package com.ruoyi.course.service;

import com.ruoyi.course.domain.TrainCourseCategory;
import com.ruoyi.framework.web.base.AbstractBaseService;

import java.util.List;
import java.util.Map;

/**
 * 课程分类管理 服务层
 * 
 * @author ruoyi
 */
public interface ITrainCourseCategoryService extends AbstractBaseService<TrainCourseCategory>
{
    /**
     * 查询课程分类管理数据
     * 
     * @param dept 课程分类信息
     * @return 课程分类信息集合
     */
    public List<TrainCourseCategory> selectDeptList(TrainCourseCategory dept);

    /**
     * 查询课程分类管理树
     * 
     * @return 所有课程分类信息
     */
    public List<Map<String, Object>> selectDeptTree();



    /**
     * 查询课程分类人数
     * 
     * @param parentId 父课程分类ID
     * @return 结果
     */
    public int selectDeptCount(Long parentId);

    /**
     * 查询课程分类是否存在用户
     * 
     * @param deptId 课程分类ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 删除课程分类管理信息
     * 
     * @param deptId 课程分类ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增保存课程分类信息
     * 
     * @param dept 课程分类信息
     * @return 结果
     */
    public int insertDept(TrainCourseCategory dept);

    /**
     * 修改保存课程分类信息
     * 
     * @param dept 课程分类信息
     * @return 结果
     */
    public int updateDept(TrainCourseCategory dept);

    /**
     * 根据课程分类ID查询信息
     * 
     * @param deptId 课程分类ID
     * @return 课程分类信息
     */
    public TrainCourseCategory selectDeptById(Long deptId);

    /**
     * 校验课程分类名称是否唯一
     * 
     * @param dept 课程分类信息
     * @return 结果
     */
    public String checkDeptNameUnique(TrainCourseCategory dept);
}

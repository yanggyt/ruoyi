package com.ruoyi.course.mapper;

import com.ruoyi.course.domain.TrainCourseCategory;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程分类管理 数据层
 *
 * @author ruoyi
 */
public interface TrainCourseCategoryMapper extends MyMapper<TrainCourseCategory>
{
    /**
     * 查询课程分类人数
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    public int selectDeptCount(TrainCourseCategory dept);

    /**
     * 查询课程分类是否存在用户
     *
     * @param deptId 课程分类ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 查询课程分类管理数据
     *
     * @param dept 课程分类信息
     * @return 课程分类信息集合
     */
    public List<TrainCourseCategory> selectDeptList(TrainCourseCategory dept);

    /**
     * 删除课程分类管理信息
     *
     * @param deptId 课程分类ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增课程分类信息
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    public int insertDept(TrainCourseCategory dept);

    /**
     * 修改课程分类信息
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    public int updateDept(TrainCourseCategory dept);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<TrainCourseCategory> depts);

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
     * @param deptName 课程分类名称
     * @param parentId 父课程分类ID
     * @return 结果
     */
    public TrainCourseCategory checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询课程分类
     *
     * @param roleId 角色ID
     * @return 课程分类列表
     */
    public List<String> selectRoleDeptTree(Long roleId);
}

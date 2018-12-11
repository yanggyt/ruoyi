package com.ruoyi.courseware.mapper;

import com.ruoyi.courseware.domain.TrainCoursewareCategory;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课件分类管理 数据层
 *
 * @author ruoyi
 */
public interface TrainCoursewareCategoryMapper extends MyMapper<TrainCoursewareCategory>
{
    /**
     * 查询课件分类人数
     *
     * @param dept 课件分类信息
     * @return 结果
     */
    public int selectDeptCount(TrainCoursewareCategory dept);

    /**
     * 查询课件分类是否存在用户
     *
     * @param deptId 课件分类ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 查询课件分类管理数据
     *
     * @param dept 课件分类信息
     * @return 课件分类信息集合
     */
    public List<TrainCoursewareCategory> selectDeptList(TrainCoursewareCategory dept);

    /**
     * 删除课件分类管理信息
     *
     * @param deptId 课件分类ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增课件分类信息
     *
     * @param dept 课件分类信息
     * @return 结果
     */
    public int insertDept(TrainCoursewareCategory dept);

    /**
     * 修改课件分类信息
     *
     * @param dept 课件分类信息
     * @return 结果
     */
    public int updateDept(TrainCoursewareCategory dept);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<TrainCoursewareCategory> depts);

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
     * @param deptName 课件分类名称
     * @param parentId 父课件分类ID
     * @return 结果
     */
    public TrainCoursewareCategory checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询课件分类
     *
     * @param roleId 角色ID
     * @return 课件分类列表
     */
    public List<String> selectRoleDeptTree(Long roleId);
}

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
public interface TrainCoursewareCategoryMapper extends MyMapper<TrainCoursewareCategory> {
    /**
     * 查询课件分类人数
     *
     * @param category 课件分类信息
     * @return 结果
     */
    public int selectCategoryCount(TrainCoursewareCategory category);

    /**
     * 查询课件分类是否存在用户
     *
     * @param id 课件分类ID
     * @return 结果
     */
    public int checkCategoryExistCourseware(Long id);

    /**
     * 查询课件分类管理数据
     *
     * @param category 课件分类信息
     * @return 课件分类信息集合
     */
    public List<TrainCoursewareCategory> selectCategoryList(TrainCoursewareCategory category);

    /**
     * 删除课件分类管理信息
     *
     * @param id 课件分类ID
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 新增课件分类信息
     *
     * @param category 课件分类信息
     * @return 结果
     */
    public int insertCategory(TrainCoursewareCategory category);

    /**
     * 修改课件分类信息
     *
     * @param category 课件分类信息
     * @return 结果
     */
    public int updateCategory(TrainCoursewareCategory category);

    /**
     * 修改子元素关系
     *
     * @param categorys 子元素
     * @return 结果
     */
    public int updateCategoryChildren(@Param("categorys") List<TrainCoursewareCategory> categorys);

    /**
     * 根据课件分类ID查询信息
     *
     * @param id 课件分类ID
     * @return 课件分类信息
     */
    public TrainCoursewareCategory selectCategoryById(Long id);

    /**
     * 校验课件分类名称是否唯一
     *
     * @param name 课件分类名称
     * @param parentId 父课件分类ID
     * @return 结果
     */
    public TrainCoursewareCategory checkCategoryNameUnique(@Param("name") String name, @Param("parentId") Long parentId);

}

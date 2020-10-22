package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.OnlineCourses;

/**
 * 线上课程Service接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface IOnlineCoursesService 
{
    /**
     * 查询线上课程
     * 
     * @param id 线上课程ID
     * @return 线上课程
     */
    public OnlineCourses selectOnlineCoursesById(Long id);

    /**
     * 查询线上课程列表
     * 
     * @param onlineCourses 线上课程
     * @return 线上课程集合
     */
    public List<OnlineCourses> selectOnlineCoursesList(OnlineCourses onlineCourses);

    /**
     * 新增线上课程
     * 
     * @param onlineCourses 线上课程
     * @return 结果
     */
    public int insertOnlineCourses(OnlineCourses onlineCourses);

    /**
     * 修改线上课程
     * 
     * @param onlineCourses 线上课程
     * @return 结果
     */
    public int updateOnlineCourses(OnlineCourses onlineCourses);

    /**
     * 批量删除线上课程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOnlineCoursesByIds(String ids);

    /**
     * 删除线上课程信息
     * 
     * @param id 线上课程ID
     * @return 结果
     */
    public int deleteOnlineCoursesById(Long id);
}

package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.OnlineCoursesMapper;
import com.ruoyi.front.domain.OnlineCourses;
import com.ruoyi.front.service.IOnlineCoursesService;
import com.ruoyi.common.core.text.Convert;

/**
 * 线上课程Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class OnlineCoursesServiceImpl implements IOnlineCoursesService 
{
    @Autowired
    private OnlineCoursesMapper onlineCoursesMapper;

    /**
     * 查询线上课程
     * 
     * @param id 线上课程ID
     * @return 线上课程
     */
    @Override
    public OnlineCourses selectOnlineCoursesById(Long id)
    {
        return onlineCoursesMapper.selectOnlineCoursesById(id);
    }

    /**
     * 查询线上课程列表
     * 
     * @param onlineCourses 线上课程
     * @return 线上课程
     */
    @Override
    public List<OnlineCourses> selectOnlineCoursesList(OnlineCourses onlineCourses)
    {
        return onlineCoursesMapper.selectOnlineCoursesList(onlineCourses);
    }

    /**
     * 新增线上课程
     * 
     * @param onlineCourses 线上课程
     * @return 结果
     */
    @Override
    public int insertOnlineCourses(OnlineCourses onlineCourses)
    {
        onlineCourses.setCreateTime(DateUtils.getNowDate());
        return onlineCoursesMapper.insertOnlineCourses(onlineCourses);
    }

    /**
     * 修改线上课程
     * 
     * @param onlineCourses 线上课程
     * @return 结果
     */
    @Override
    public int updateOnlineCourses(OnlineCourses onlineCourses)
    {
        onlineCourses.setUpdateTime(DateUtils.getNowDate());
        return onlineCoursesMapper.updateOnlineCourses(onlineCourses);
    }

    /**
     * 删除线上课程对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOnlineCoursesByIds(String ids)
    {
        return onlineCoursesMapper.deleteOnlineCoursesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除线上课程信息
     * 
     * @param id 线上课程ID
     * @return 结果
     */
    @Override
    public int deleteOnlineCoursesById(Long id)
    {
        return onlineCoursesMapper.deleteOnlineCoursesById(id);
    }
}

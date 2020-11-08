package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.EventRecruitment;

/**
 * 活动招募Service接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface IEventRecruitmentService 
{
    /**
     * 查询活动招募
     * 
     * @param id 活动招募ID
     * @return 活动招募
     */
    public EventRecruitment selectEventRecruitmentById(Long id);

    /**
     * 查询活动招募列表
     * 
     * @param eventRecruitment 活动招募
     * @return 活动招募集合
     */
    public List<EventRecruitment> selectEventRecruitmentList(EventRecruitment eventRecruitment);

    /**
     * 新增活动招募
     * 
     * @param eventRecruitment 活动招募
     * @return 结果
     */
    public int insertEventRecruitment(EventRecruitment eventRecruitment);

    /**
     * 修改活动招募
     * 
     * @param eventRecruitment 活动招募
     * @return 结果
     */
    public int updateEventRecruitment(EventRecruitment eventRecruitment);

    /**
     * 批量删除活动招募
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEventRecruitmentByIds(String ids);

    /**
     * 删除活动招募信息
     * 
     * @param id 活动招募ID
     * @return 结果
     */
    public int deleteEventRecruitmentById(Long id);
}

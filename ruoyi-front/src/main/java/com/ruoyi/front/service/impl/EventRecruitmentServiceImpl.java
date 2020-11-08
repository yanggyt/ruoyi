package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.EventRecruitmentMapper;
import com.ruoyi.front.domain.EventRecruitment;
import com.ruoyi.front.service.IEventRecruitmentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 活动招募Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class EventRecruitmentServiceImpl implements IEventRecruitmentService 
{
    @Autowired
    private EventRecruitmentMapper eventRecruitmentMapper;

    /**
     * 查询活动招募
     * 
     * @param id 活动招募ID
     * @return 活动招募
     */
    @Override
    public EventRecruitment selectEventRecruitmentById(Long id)
    {
        return eventRecruitmentMapper.selectEventRecruitmentById(id);
    }

    /**
     * 查询活动招募列表
     * 
     * @param eventRecruitment 活动招募
     * @return 活动招募
     */
    @Override
    public List<EventRecruitment> selectEventRecruitmentList(EventRecruitment eventRecruitment)
    {
        return eventRecruitmentMapper.selectEventRecruitmentList(eventRecruitment);
    }

    /**
     * 新增活动招募
     * 
     * @param eventRecruitment 活动招募
     * @return 结果
     */
    @Override
    public int insertEventRecruitment(EventRecruitment eventRecruitment)
    {
        eventRecruitment.setCreateTime(DateUtils.getNowDate());
        return eventRecruitmentMapper.insertEventRecruitment(eventRecruitment);
    }

    /**
     * 修改活动招募
     * 
     * @param eventRecruitment 活动招募
     * @return 结果
     */
    @Override
    public int updateEventRecruitment(EventRecruitment eventRecruitment)
    {
        eventRecruitment.setUpdateTime(DateUtils.getNowDate());
        return eventRecruitmentMapper.updateEventRecruitment(eventRecruitment);
    }

    /**
     * 删除活动招募对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEventRecruitmentByIds(String ids)
    {
        return eventRecruitmentMapper.deleteEventRecruitmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动招募信息
     * 
     * @param id 活动招募ID
     * @return 结果
     */
    @Override
    public int deleteEventRecruitmentById(Long id)
    {
        return eventRecruitmentMapper.deleteEventRecruitmentById(id);
    }
}

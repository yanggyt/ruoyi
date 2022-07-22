package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsNotificationMapper;
import com.ruoyi.news.domain.NewsNotification;
import com.ruoyi.news.service.INewsNotificationService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsNotificationServiceImpl implements INewsNotificationService 
{
    @Autowired
    private NewsNotificationMapper newsNotificationMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsNotification selectNewsNotificationById(Long id)
    {
        return newsNotificationMapper.selectNewsNotificationById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsNotification 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsNotification> selectNewsNotificationList(NewsNotification newsNotification)
    {
        return newsNotificationMapper.selectNewsNotificationList(newsNotification);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsNotification 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsNotification(NewsNotification newsNotification)
    {
        return newsNotificationMapper.insertNewsNotification(newsNotification);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsNotification 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsNotification(NewsNotification newsNotification)
    {
        return newsNotificationMapper.updateNewsNotification(newsNotification);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsNotificationByIds(String ids)
    {
        return newsNotificationMapper.deleteNewsNotificationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsNotificationById(Long id)
    {
        return newsNotificationMapper.deleteNewsNotificationById(id);
    }
}

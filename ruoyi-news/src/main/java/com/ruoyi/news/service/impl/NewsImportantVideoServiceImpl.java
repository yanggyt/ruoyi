package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsImportantVideoMapper;
import com.ruoyi.news.domain.NewsImportantVideo;
import com.ruoyi.news.service.INewsImportantVideoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsImportantVideoServiceImpl implements INewsImportantVideoService 
{
    @Autowired
    private NewsImportantVideoMapper newsImportantVideoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsImportantVideo selectNewsImportantVideoById(Long id)
    {
        return newsImportantVideoMapper.selectNewsImportantVideoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantVideo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsImportantVideo> selectNewsImportantVideoList(NewsImportantVideo newsImportantVideo)
    {
        return newsImportantVideoMapper.selectNewsImportantVideoList(newsImportantVideo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantVideo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsImportantVideo(NewsImportantVideo newsImportantVideo)
    {
        return newsImportantVideoMapper.insertNewsImportantVideo(newsImportantVideo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantVideo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsImportantVideo(NewsImportantVideo newsImportantVideo)
    {
        return newsImportantVideoMapper.updateNewsImportantVideo(newsImportantVideo);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantVideoByIds(String ids)
    {
        return newsImportantVideoMapper.deleteNewsImportantVideoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantVideoById(Long id)
    {
        return newsImportantVideoMapper.deleteNewsImportantVideoById(id);
    }
}

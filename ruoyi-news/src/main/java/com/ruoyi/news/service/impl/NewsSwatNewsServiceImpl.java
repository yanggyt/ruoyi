package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsSwatNewsMapper;
import com.ruoyi.news.domain.NewsSwatNews;
import com.ruoyi.news.service.INewsSwatNewsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsSwatNewsServiceImpl implements INewsSwatNewsService 
{
    @Autowired
    private NewsSwatNewsMapper newsSwatNewsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsSwatNews selectNewsSwatNewsById(Long id)
    {
        return newsSwatNewsMapper.selectNewsSwatNewsById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsSwatNews 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsSwatNews> selectNewsSwatNewsList(NewsSwatNews newsSwatNews)
    {
        return newsSwatNewsMapper.selectNewsSwatNewsList(newsSwatNews);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsSwatNews 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsSwatNews(NewsSwatNews newsSwatNews)
    {
        return newsSwatNewsMapper.insertNewsSwatNews(newsSwatNews);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsSwatNews 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsSwatNews(NewsSwatNews newsSwatNews)
    {
        return newsSwatNewsMapper.updateNewsSwatNews(newsSwatNews);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsSwatNewsByIds(String ids)
    {
        return newsSwatNewsMapper.deleteNewsSwatNewsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsSwatNewsById(Long id)
    {
        return newsSwatNewsMapper.deleteNewsSwatNewsById(id);
    }
}

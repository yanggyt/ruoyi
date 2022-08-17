package com.ruoyi.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsImportantNewsMapper;
import com.ruoyi.news.domain.NewsImportantNews;
import com.ruoyi.news.service.INewsImportantNewsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-08-17
 */
@Service
public class NewsImportantNewsServiceImpl implements INewsImportantNewsService {
    @Autowired
    private NewsImportantNewsMapper newsImportantNewsMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsImportantNews selectNewsImportantNewsById(Long id) {
        return newsImportantNewsMapper.selectNewsImportantNewsById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param newsImportantNews 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsImportantNews> selectNewsImportantNewsList(NewsImportantNews newsImportantNews) {
        return newsImportantNewsMapper.selectNewsImportantNewsList(newsImportantNews);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param newsImportantNews 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsImportantNews(NewsImportantNews newsImportantNews) {
        return newsImportantNewsMapper.insertNewsImportantNews(newsImportantNews);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param newsImportantNews 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsImportantNews(NewsImportantNews newsImportantNews) {
        return newsImportantNewsMapper.updateNewsImportantNews(newsImportantNews);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantNewsByIds(String ids) {
        return newsImportantNewsMapper.deleteNewsImportantNewsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantNewsById(Long id) {
        return newsImportantNewsMapper.deleteNewsImportantNewsById(id);
    }
}

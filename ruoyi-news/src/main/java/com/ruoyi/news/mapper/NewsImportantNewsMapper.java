package com.ruoyi.news.mapper;

import java.util.List;
import com.ruoyi.news.domain.NewsImportantNews;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public interface NewsImportantNewsMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public NewsImportantNews selectNewsImportantNewsById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantNews 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<NewsImportantNews> selectNewsImportantNewsList(NewsImportantNews newsImportantNews);

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantNews 【请填写功能名称】
     * @return 结果
     */
    public int insertNewsImportantNews(NewsImportantNews newsImportantNews);

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantNews 【请填写功能名称】
     * @return 结果
     */
    public int updateNewsImportantNews(NewsImportantNews newsImportantNews);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNewsImportantNewsById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsImportantNewsByIds(String[] ids);
}

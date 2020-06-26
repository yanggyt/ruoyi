package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BArticle;

/**
 * 文章Service接口
 * 
 * @author anjie
 * @date 2020-06-26
 */
public interface IBArticleService 
{
    /**
     * 查询文章
     * 
     * @param id 文章ID
     * @return 文章
     */
    public BArticle selectBArticleById(Long id);

    /**
     * 查询文章列表
     * 
     * @param bArticle 文章
     * @return 文章集合
     */
    public List<BArticle> selectBArticleList(BArticle bArticle);

    /**
     * 新增文章
     * 
     * @param bArticle 文章
     * @return 结果
     */
    public int insertBArticle(BArticle bArticle);

    /**
     * 修改文章
     * 
     * @param bArticle 文章
     * @return 结果
     */
    public int updateBArticle(BArticle bArticle);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBArticleByIds(String ids);

    /**
     * 删除文章信息
     * 
     * @param id 文章ID
     * @return 结果
     */
    public int deleteBArticleById(Long id);
}

package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.Article;

/**
 * 内容管理Service接口
 * 
 * @author bend
 * @date 2020-08-30
 */
public interface IArticleService 
{
    /**
     * 查询内容管理
     * 
     * @param id 内容管理ID
     * @return 内容管理
     */
    public Article selectArticleById(Long id);

    /**
     * 查询内容管理列表
     * 
     * @param article 内容管理
     * @return 内容管理集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增内容管理
     * 
     * @param article 内容管理
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改内容管理
     * 
     * @param article 内容管理
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 批量删除内容管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleByIds(String ids);

    /**
     * 删除内容管理信息
     * 
     * @param id 内容管理ID
     * @return 结果
     */
    public int deleteArticleById(Long id);
}

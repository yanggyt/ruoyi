package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.ArticleMapper;
import com.ruoyi.bend.domain.Article;
import com.ruoyi.bend.service.IArticleService;
import com.ruoyi.common.core.text.Convert;

/**
 * 内容管理Service业务层处理
 * 
 * @author bend
 * @date 2020-08-30
 */
@Service
public class ArticleServiceImpl implements IArticleService 
{
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查询内容管理
     * 
     * @param id 内容管理ID
     * @return 内容管理
     */
    @Override
    public Article selectArticleById(Long id)
    {
        return articleMapper.selectArticleById(id);
    }

    /**
     * 查询内容管理列表
     * 
     * @param article 内容管理
     * @return 内容管理
     */
    @Override
    public List<Article> selectArticleList(Article article)
    {
        return articleMapper.selectArticleList(article);
    }

    /**
     * 新增内容管理
     * 
     * @param article 内容管理
     * @return 结果
     */
    @Override
    public int insertArticle(Article article)
    {
        article.setCreateTime(DateUtils.getNowDate());
        return articleMapper.insertArticle(article);
    }

    /**
     * 修改内容管理
     * 
     * @param article 内容管理
     * @return 结果
     */
    @Override
    public int updateArticle(Article article)
    {
        article.setUpdateTime(DateUtils.getNowDate());
        return articleMapper.updateArticle(article);
    }

    /**
     * 删除内容管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteArticleByIds(String ids)
    {
        return articleMapper.deleteArticleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除内容管理信息
     * 
     * @param id 内容管理ID
     * @return 结果
     */
    @Override
    public int deleteArticleById(Long id)
    {
        return articleMapper.deleteArticleById(id);
    }
}

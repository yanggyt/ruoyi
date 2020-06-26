package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.business.domain.BArticleClass;
import com.ruoyi.business.mapper.BArticleClassMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BArticleMapper;
import com.ruoyi.business.domain.BArticle;
import com.ruoyi.business.service.IBArticleService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文章Service业务层处理
 *
 * @author anjie
 * @date 2020-06-26
 */
@Service
public class BArticleServiceImpl implements IBArticleService
{
    @Autowired
    private BArticleMapper bArticleMapper;

    @Autowired
    private BArticleClassMapper bArticleClassMapper;

    /**
     * 查询文章
     *
     * @param id 文章ID
     * @return 文章
     */
    @Override
    public BArticle selectBArticleById(Long id)
    {
        BArticle bArticle = bArticleMapper.selectBArticleById(id);
        if(bArticle.getArticleclassid()!=null) {
            BArticleClass bArticleClass = bArticleClassMapper.selectBArticleClassById(bArticle.getArticleclassid());
            bArticle.setParentName(bArticleClass.getName());
        }
        return bArticle;
    }

    /**
     * 查询文章列表
     *
     * @param bArticle 文章
     * @return 文章
     */
    @Override
    public List<BArticle> selectBArticleList(BArticle bArticle)
    {
        return bArticleMapper.selectBArticleList(bArticle);
    }

    /**
     * 新增文章
     *
     * @param bArticle 文章
     * @return 结果
     */
    @Override
    public int insertBArticle(BArticle bArticle)
    {
        bArticle.setCreateTime(DateUtils.getNowDate());
        return bArticleMapper.insertBArticle(bArticle);
    }

    /**
     * 修改文章
     *
     * @param bArticle 文章
     * @return 结果
     */
    @Override
    public int updateBArticle(BArticle bArticle)
    {
        bArticle.setUpdateTime(DateUtils.getNowDate());
        return bArticleMapper.updateBArticle(bArticle);
    }

    /**
     * 删除文章对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBArticleByIds(String ids)
    {
        return bArticleMapper.deleteBArticleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章信息
     *
     * @param id 文章ID
     * @return 结果
     */
    @Override
    public int deleteBArticleById(Long id)
    {
        return bArticleMapper.deleteBArticleById(id);
    }
}

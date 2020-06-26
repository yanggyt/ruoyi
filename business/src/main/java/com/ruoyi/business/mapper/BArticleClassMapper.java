package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BArticleClass;

/**
 * 文章分类Mapper接口
 * 
 * @author anjie
 * @date 2020-06-26
 */
public interface BArticleClassMapper 
{
    /**
     * 查询文章分类
     * 
     * @param id 文章分类ID
     * @return 文章分类
     */
    public BArticleClass selectBArticleClassById(Long id);

    /**
     * 查询文章分类列表
     * 
     * @param bArticleClass 文章分类
     * @return 文章分类集合
     */
    public List<BArticleClass> selectBArticleClassList(BArticleClass bArticleClass);

    /**
     * 新增文章分类
     * 
     * @param bArticleClass 文章分类
     * @return 结果
     */
    public int insertBArticleClass(BArticleClass bArticleClass);

    /**
     * 修改文章分类
     * 
     * @param bArticleClass 文章分类
     * @return 结果
     */
    public int updateBArticleClass(BArticleClass bArticleClass);

    /**
     * 删除文章分类
     * 
     * @param id 文章分类ID
     * @return 结果
     */
    public int deleteBArticleClassById(Long id);

    /**
     * 批量删除文章分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBArticleClassByIds(String[] ids);
}

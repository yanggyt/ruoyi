package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BArticleClass;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 文章分类Service接口
 * 
 * @author anjie
 * @date 2020-06-26
 */
public interface IBArticleClassService 
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
     * 批量删除文章分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBArticleClassByIds(String ids);

    /**
     * 删除文章分类信息
     * 
     * @param id 文章分类ID
     * @return 结果
     */
    public int deleteBArticleClassById(Long id);

    /**
     * 查询文章分类树列表
     * 
     * @return 所有文章分类信息
     */
    public List<Ztree> selectBArticleClassTree();
}

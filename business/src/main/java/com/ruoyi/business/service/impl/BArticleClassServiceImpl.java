package com.ruoyi.business.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BArticleClassMapper;
import com.ruoyi.business.domain.BArticleClass;
import com.ruoyi.business.service.IBArticleClassService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文章分类Service业务层处理
 * 
 * @author anjie
 * @date 2020-06-26
 */
@Service
public class BArticleClassServiceImpl implements IBArticleClassService 
{
    @Autowired
    private BArticleClassMapper bArticleClassMapper;

    /**
     * 查询文章分类
     * 
     * @param id 文章分类ID
     * @return 文章分类
     */
    @Override
    public BArticleClass selectBArticleClassById(Long id)
    {
        return bArticleClassMapper.selectBArticleClassById(id);
    }

    /**
     * 查询文章分类列表
     * 
     * @param bArticleClass 文章分类
     * @return 文章分类
     */
    @Override
    public List<BArticleClass> selectBArticleClassList(BArticleClass bArticleClass)
    {
        return bArticleClassMapper.selectBArticleClassList(bArticleClass);
    }

    /**
     * 新增文章分类
     * 
     * @param bArticleClass 文章分类
     * @return 结果
     */
    @Override
    public int insertBArticleClass(BArticleClass bArticleClass)
    {
        bArticleClass.setCreateTime(DateUtils.getNowDate());
        return bArticleClassMapper.insertBArticleClass(bArticleClass);
    }

    /**
     * 修改文章分类
     * 
     * @param bArticleClass 文章分类
     * @return 结果
     */
    @Override
    public int updateBArticleClass(BArticleClass bArticleClass)
    {
        bArticleClass.setUpdateTime(DateUtils.getNowDate());
        return bArticleClassMapper.updateBArticleClass(bArticleClass);
    }

    /**
     * 删除文章分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBArticleClassByIds(String ids)
    {
        return bArticleClassMapper.deleteBArticleClassByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章分类信息
     * 
     * @param id 文章分类ID
     * @return 结果
     */
    @Override
    public int deleteBArticleClassById(Long id)
    {
        return bArticleClassMapper.deleteBArticleClassById(id);
    }

    /**
     * 查询文章分类树列表
     * 
     * @return 所有文章分类信息
     */
    @Override
    public List<Ztree> selectBArticleClassTree()
    {
        List<BArticleClass> bArticleClassList = bArticleClassMapper.selectBArticleClassList(new BArticleClass());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (BArticleClass bArticleClass : bArticleClassList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(bArticleClass.getId());
            ztree.setpId(bArticleClass.getParentId());
            ztree.setName(bArticleClass.getName());
            ztree.setTitle(bArticleClass.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}

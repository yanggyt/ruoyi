package com.ruoyi.business.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BProductClassMapper;
import com.ruoyi.business.domain.BProductClass;
import com.ruoyi.business.service.IBProductClassService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商品分类Service业务层处理
 * 
 * @author anjie
 * @date 2020-06-21
 */
@Service
public class BProductClassServiceImpl implements IBProductClassService 
{
    @Autowired
    private BProductClassMapper bProductClassMapper;

    /**
     * 查询商品分类
     * 
     * @param id 商品分类ID
     * @return 商品分类
     */
    @Override
    public BProductClass selectBProductClassById(Long id)
    {
        return bProductClassMapper.selectBProductClassById(id);
    }

    /**
     * 查询商品分类列表
     * 
     * @param bProductClass 商品分类
     * @return 商品分类
     */
    @Override
    public List<BProductClass> selectBProductClassList(BProductClass bProductClass)
    {
        return bProductClassMapper.selectBProductClassList(bProductClass);
    }

    /**
     * 新增商品分类
     * 
     * @param bProductClass 商品分类
     * @return 结果
     */
    @Override
    public int insertBProductClass(BProductClass bProductClass)
    {
        bProductClass.setCreateTime(DateUtils.getNowDate());
        return bProductClassMapper.insertBProductClass(bProductClass);
    }

    /**
     * 修改商品分类
     * 
     * @param bProductClass 商品分类
     * @return 结果
     */
    @Override
    public int updateBProductClass(BProductClass bProductClass)
    {
        bProductClass.setUpdateTime(DateUtils.getNowDate());
        return bProductClassMapper.updateBProductClass(bProductClass);
    }

    /**
     * 删除商品分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBProductClassByIds(String ids)
    {
        return bProductClassMapper.deleteBProductClassByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品分类信息
     * 
     * @param id 商品分类ID
     * @return 结果
     */
    @Override
    public int deleteBProductClassById(Long id)
    {
        return bProductClassMapper.deleteBProductClassById(id);
    }

    /**
     * 查询商品分类树列表
     * 
     * @return 所有商品分类信息
     */
    @Override
    public List<Ztree> selectBProductClassTree()
    {
        List<BProductClass> bProductClassList = bProductClassMapper.selectBProductClassList(new BProductClass());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (BProductClass bProductClass : bProductClassList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(bProductClass.getId());
            ztree.setpId(bProductClass.getParentId());
            ztree.setName(bProductClass.getName());
            ztree.setTitle(bProductClass.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}

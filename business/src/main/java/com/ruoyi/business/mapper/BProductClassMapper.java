package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BProductClass;

/**
 * 商品分类Mapper接口
 * 
 * @author anjie
 * @date 2020-06-21
 */
public interface BProductClassMapper 
{
    /**
     * 查询商品分类
     * 
     * @param id 商品分类ID
     * @return 商品分类
     */
    public BProductClass selectBProductClassById(Long id);

    /**
     * 查询商品分类列表
     * 
     * @param bProductClass 商品分类
     * @return 商品分类集合
     */
    public List<BProductClass> selectBProductClassList(BProductClass bProductClass);

    /**
     * 新增商品分类
     * 
     * @param bProductClass 商品分类
     * @return 结果
     */
    public int insertBProductClass(BProductClass bProductClass);

    /**
     * 修改商品分类
     * 
     * @param bProductClass 商品分类
     * @return 结果
     */
    public int updateBProductClass(BProductClass bProductClass);

    /**
     * 删除商品分类
     * 
     * @param id 商品分类ID
     * @return 结果
     */
    public int deleteBProductClassById(Long id);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBProductClassByIds(String[] ids);
}

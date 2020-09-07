package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizProductType;

/**
 * 产品分类Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-05
 */
public interface BizProductTypeMapper 
{
    /**
     * 查询产品分类
     * 
     * @param productTypeId 产品分类ID
     * @return 产品分类
     */
    public BizProductType selectBizProductTypeById(Long productTypeId);

    /**
     * 查询产品分类列表
     * 
     * @param bizProductType 产品分类
     * @return 产品分类集合
     */
    public List<BizProductType> selectBizProductTypeList(BizProductType bizProductType);

    /**
     * 新增产品分类
     * 
     * @param bizProductType 产品分类
     * @return 结果
     */
    public int insertBizProductType(BizProductType bizProductType);

    /**
     * 修改产品分类
     * 
     * @param bizProductType 产品分类
     * @return 结果
     */
    public int updateBizProductType(BizProductType bizProductType);

    /**
     * 删除产品分类
     * 
     * @param productTypeId 产品分类ID
     * @return 结果
     */
    public int deleteBizProductTypeById(Long productTypeId);

    /**
     * 批量删除产品分类
     * 
     * @param productTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProductTypeByIds(String[] productTypeIds);
}

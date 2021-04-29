package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductTag;
import java.util.List;

/**
 * productMapper接口
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
public interface ProductTagMapper 
{
    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    public ProductTag selectProductTagById(Long id);

    /**
     * 查询product列表
     * 
     * @param productTag product
     * @return product集合
     */
    public List<ProductTag> selectProductTagList(ProductTag productTag);

    /**
     * 新增product
     * 
     * @param productTag product
     * @return 结果
     */
    public int insertProductTag(ProductTag productTag);

    /**
     * 修改product
     * 
     * @param productTag product
     * @return 结果
     */
    public int updateProductTag(ProductTag productTag);

    /**
     * 删除product
     * 
     * @param id productID
     * @return 结果
     */
    public int deleteProductTagById(Long id);

    /**
     * 批量删除product
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductTagByIds(String[] ids);
}

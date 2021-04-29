package com.ruoyi.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductTagMapper;
import com.ruoyi.product.domain.ProductTag;
import com.ruoyi.product.service.IProductTagService;
import com.ruoyi.common.core.text.Convert;

/**
 * productService业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
@Service
public class ProductTagServiceImpl implements IProductTagService 
{
    @Autowired
    private ProductTagMapper productTagMapper;

    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    @Override
    public ProductTag selectProductTagById(Long id)
    {
        return productTagMapper.selectProductTagById(id);
    }

    /**
     * 查询product列表
     * 
     * @param productTag product
     * @return product
     */
    @Override
    public List<ProductTag> selectProductTagList(ProductTag productTag)
    {
        return productTagMapper.selectProductTagList(productTag);
    }

    /**
     * 新增product
     * 
     * @param productTag product
     * @return 结果
     */
    @Override
    public int insertProductTag(ProductTag productTag)
    {
        return productTagMapper.insertProductTag(productTag);
    }

    /**
     * 修改product
     * 
     * @param productTag product
     * @return 结果
     */
    @Override
    public int updateProductTag(ProductTag productTag)
    {
        return productTagMapper.updateProductTag(productTag);
    }

    /**
     * 删除product对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductTagByIds(String ids)
    {
        return productTagMapper.deleteProductTagByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除product信息
     * 
     * @param id productID
     * @return 结果
     */
    @Override
    public int deleteProductTagById(Long id)
    {
        return productTagMapper.deleteProductTagById(id);
    }
}

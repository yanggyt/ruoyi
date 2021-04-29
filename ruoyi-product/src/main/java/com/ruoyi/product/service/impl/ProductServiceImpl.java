package com.ruoyi.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductMapper;
import com.ruoyi.product.domain.Product;
import com.ruoyi.product.service.IProductService;
import com.ruoyi.common.core.text.Convert;

/**
 * productService业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
@Service
public class ProductServiceImpl implements IProductService 
{
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    @Override
    public Product selectProductById(Long id)
    {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询product列表
     * 
     * @param product product
     * @return product
     */
    @Override
    public List<Product> selectProductList(Product product)
    {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增product
     * 
     * @param product product
     * @return 结果
     */
    @Override
    public int insertProduct(Product product)
    {
        return productMapper.insertProduct(product);
    }

    /**
     * 修改product
     * 
     * @param product product
     * @return 结果
     */
    @Override
    public int updateProduct(Product product)
    {
        return productMapper.updateProduct(product);
    }

    /**
     * 删除product对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductByIds(String ids)
    {
        return productMapper.deleteProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除product信息
     * 
     * @param id productID
     * @return 结果
     */
    @Override
    public int deleteProductById(Long id)
    {
        return productMapper.deleteProductById(id);
    }
}

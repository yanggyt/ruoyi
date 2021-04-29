package com.ruoyi.product.service;

import com.ruoyi.product.domain.Product;
import java.util.List;

/**
 * productService接口
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
public interface IProductService 
{
    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    public Product selectProductById(Long id);

    /**
     * 查询product列表
     * 
     * @param product product
     * @return product集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增product
     * 
     * @param product product
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改product
     * 
     * @param product product
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 批量删除product
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductByIds(String ids);

    /**
     * 删除product信息
     * 
     * @param id productID
     * @return 结果
     */
    public int deleteProductById(Long id);
}

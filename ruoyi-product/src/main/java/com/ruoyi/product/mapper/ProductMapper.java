package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.Product;
import com.ruoyi.product.domain.ProductInformation;

import java.util.List;

/**
 * productMapper接口
 *
 * @author ruoyi
 * @date 2021-04-29
 */
public interface ProductMapper
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
     * 删除product
     *
     * @param id productID
     * @return 结果
     */
    public int deleteProductById(Long id);

    /**
     * 批量删除product
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductByIds(String[] ids);

    /**
     * @Description: 获取产品的信息
     *
     * @param
     * @return
     * @Date
     * @author: wanghao
     *
     */
    public List<Product> selectProduct();
}

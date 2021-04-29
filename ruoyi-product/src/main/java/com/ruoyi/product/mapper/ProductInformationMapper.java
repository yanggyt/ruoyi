package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductInformation;
import java.util.List;

/**
 * productMapper接口
 *
 * @author ruoyi
 * @date 2021-04-29
 */
public interface ProductInformationMapper
{
    /**
     * 查询product
     *
     * @param id productID
     * @return product
     */
    public ProductInformation selectProductInformationById(Long id);

    /**
     * 查询product列表
     *
     * @param productInformation product
     * @return product集合
     */
    public List<ProductInformation> selectProductInformationList(ProductInformation productInformation);

    /**
     * 新增product
     *
     * @param productInformation product
     * @return 结果
     */
    public int insertProductInformation(ProductInformation productInformation);

    /**
     * 修改product
     *
     * @param productInformation product
     * @return 结果
     */
    public int updateProductInformation(ProductInformation productInformation);

    /**
     * 删除product
     *
     * @param id productID
     * @return 结果
     */
    public int deleteProductInformationById(Long id);

    /**
     * 批量删除product
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductInformationByIds(String[] ids);




}

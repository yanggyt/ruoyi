package com.ruoyi.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductInformationMapper;
import com.ruoyi.product.domain.ProductInformation;
import com.ruoyi.product.service.IProductInformationService;
import com.ruoyi.common.core.text.Convert;

/**
 * productService业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
@Service
public class ProductInformationServiceImpl implements IProductInformationService 
{
    @Autowired
    private ProductInformationMapper productInformationMapper;

    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    @Override
    public ProductInformation selectProductInformationById(Long id)
    {
        return productInformationMapper.selectProductInformationById(id);
    }

    /**
     * 查询product列表
     * 
     * @param productInformation product
     * @return product
     */
    @Override
    public List<ProductInformation> selectProductInformationList(ProductInformation productInformation)
    {
        return productInformationMapper.selectProductInformationList(productInformation);
    }

    /**
     * 新增product
     * 
     * @param productInformation product
     * @return 结果
     */
    @Override
    public int insertProductInformation(ProductInformation productInformation)
    {
        return productInformationMapper.insertProductInformation(productInformation);
    }

    /**
     * 修改product
     * 
     * @param productInformation product
     * @return 结果
     */
    @Override
    public int updateProductInformation(ProductInformation productInformation)
    {
        return productInformationMapper.updateProductInformation(productInformation);
    }

    /**
     * 删除product对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductInformationByIds(String ids)
    {
        return productInformationMapper.deleteProductInformationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除product信息
     * 
     * @param id productID
     * @return 结果
     */
    @Override
    public int deleteProductInformationById(Long id)
    {
        return productInformationMapper.deleteProductInformationById(id);
    }
}

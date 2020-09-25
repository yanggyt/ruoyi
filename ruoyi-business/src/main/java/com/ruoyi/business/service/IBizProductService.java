package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.domain.BizProductImage;

/**
 * 产品Service接口
 * 
 * @author ruoyi
 * @date 2020-09-06
 */
public interface IBizProductService 
{
    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    public BizProduct selectBizProductById(Long productId);

    /**
     * 查询产品列表
     * 
     * @param bizProduct 产品
     * @return 产品集合
     */
    public List<BizProduct> selectBizProductList(BizProduct bizProduct);

    /**
     * 查询团队产品列表
     *
     * @param
     * @return 产品集合
     */
    public List<BizProduct> selectTeamProductList();

    /**
     * 新增产品
     * 
     * @param bizProduct 产品
     * @return 结果
     */
    public int insertBizProduct(BizProduct bizProduct);

    /**
     * 修改产品
     * 
     * @param bizProduct 产品
     * @return 结果
     */
    public int updateBizProduct(BizProduct bizProduct);

    /**
     * 上架下架产品
     *
     * @param productID 产品
     * @return 结果
     */
    public int updateBizProductStatus(Long productID);

    /**
     * 批量删除产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProductByIds(String ids);

    /**
     * 删除产品信息
     * 
     * @param productId 产品ID
     * @return 结果
     */
    public int deleteBizProductById(Long productId);
}

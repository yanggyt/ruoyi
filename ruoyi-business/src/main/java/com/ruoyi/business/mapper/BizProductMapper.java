package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.domain.BizProductImage;

/**
 * 产品Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-06
 */
public interface BizProductMapper 
{
    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    public BizProduct selectBizProductById(Long productId);

    /**
     * 查询产品
     *
     * @param productCode 产品编码
     * @return 产品
     */
    public BizProduct selectBizProductByCode(String productCode);

    /**
     * 查询产品图片列表
     *
     * @param productId 产品ID
     * @return 产品图片列表
     */
    public List<BizProductImage> selectBizProductImageList(Long productId);

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
     * 删除产品
     * 
     * @param productId 产品ID
     * @return 结果
     */
    public int deleteBizProductById(Long productId);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProductByIds(String[] productIds);

    /**
     * 批量删除产品图片
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProductImageByProductIds(String[] productIds);
    
    /**
     * 批量新增产品图片
     * 
     * @param bizProductImageList 产品图片列表
     * @return 结果
     */
    public int batchBizProductImage(List<BizProductImage> bizProductImageList);
    

    /**
     * 通过产品ID删除产品图片信息
     * 
     * @param productId 角色ID
     * @return 结果
     */
    public int deleteBizProductImageByProductId(Long productId);
}

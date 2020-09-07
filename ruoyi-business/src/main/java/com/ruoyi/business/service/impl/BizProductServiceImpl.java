package com.ruoyi.business.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.business.domain.BizProductImage;
import com.ruoyi.business.mapper.BizProductMapper;
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.service.IBizProductService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-06
 */
@Service
public class BizProductServiceImpl implements IBizProductService 
{
    @Autowired
    private BizProductMapper bizProductMapper;

    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    @Override
    public BizProduct selectBizProductById(Long productId)
    {
        BizProduct bizProduct = bizProductMapper.selectBizProductById(productId);
        List<BizProductImage> imageList = bizProductMapper.selectBizProductImageList(productId);
        List<String> detailImages = new ArrayList<String>();
        List<String> loopImages = new ArrayList<String>();
        //取出商品三种图片数据
        for (BizProductImage image : imageList) {
            String url = image.getImageUrl();
            switch (image.getImageType()) {
                case 0:
                    bizProduct.setMainImage(url);
                    break;
                case 1:
                    detailImages.add(url);
                    break;
                case 2:
                    loopImages.add(url);
                    break;
            }
        }
        bizProduct.setDetailImages(detailImages);
        bizProduct.setLoopImages(loopImages);
        return bizProduct;
    }

    /**
     * 查询产品列表
     * 
     * @param bizProduct 产品
     * @return 产品
     */
    @Override
    public List<BizProduct> selectBizProductList(BizProduct bizProduct)
    {
        return bizProductMapper.selectBizProductList(bizProduct);
    }

    /**
     * 新增产品
     * 
     * @param bizProduct 产品
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBizProduct(BizProduct bizProduct)
    {
        bizProduct.setCreateTime(DateUtils.getNowDate());
        int rows = bizProductMapper.insertBizProduct(bizProduct);
        insertBizProductImage(bizProduct);
        return rows;
    }

    /**
     * 修改产品
     * 
     * @param bizProduct 产品
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBizProduct(BizProduct bizProduct)
    {
        bizProduct.setUpdateTime(DateUtils.getNowDate());
        bizProductMapper.deleteBizProductImageByProductId(bizProduct.getProductId());
        insertBizProductImage(bizProduct);
        return bizProductMapper.updateBizProduct(bizProduct);
    }

    /**
     * 上架下架产品
     *
     * @param productID 产品
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBizProductStatus(Long productID)
    {
        BizProduct bizProduct = bizProductMapper.selectBizProductById(productID);
        Date now = new Date();
        switch (bizProduct.getOnlineStatus())
        {
            case 0:
                bizProduct.setOnlineTime(now);
                bizProduct.setOnlineStatus(1);
                break;
            case 1:
                bizProduct.setOfflineTime(now);
                bizProduct.setOnlineStatus(0);
                break;
        }
        return bizProductMapper.updateBizProduct(bizProduct);
    }

    /**
     * 删除产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBizProductByIds(String ids)
    {
        bizProductMapper.deleteBizProductImageByProductIds(Convert.toStrArray(ids));
        return bizProductMapper.deleteBizProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品信息
     * 
     * @param productId 产品ID
     * @return 结果
     */
    @Override
    public int deleteBizProductById(Long productId)
    {
        bizProductMapper.deleteBizProductImageByProductId(productId);
        return bizProductMapper.deleteBizProductById(productId);
    }

    /**
     * 新增产品图片信息
     *
     * @param bizProduct 产品对象
     */
    public void insertBizProductImage(BizProduct bizProduct)
    {
        List<String> detailImages = bizProduct.getDetailImages();
        List<String> loopImages = bizProduct.getLoopImages();
        String mainImage = bizProduct.getMainImage();
        Long productId = bizProduct.getProductId();
        List<BizProductImage> list = new ArrayList<BizProductImage>();
        //插入三类产品图片
        if (StringUtils.isNotEmpty(mainImage)) {
            list.add(getInsertProductImage(productId, 0, mainImage));
        }
        if (StringUtils.isNotNull(detailImages)) {
            for (String url : detailImages) {
                list.add(getInsertProductImage(productId, 1, url));
            }
        }
        if (StringUtils.isNotNull(loopImages)) {
            for (String url : loopImages) {
                list.add(getInsertProductImage(productId, 2, url));
            }
        }
        if (list.size() > 0) {
            bizProductMapper.batchBizProductImage(list);
        }
    }

    //新增企业图片
    public BizProductImage getInsertProductImage(Long productId, Integer type, String url) {
        BizProductImage productImage = new BizProductImage();
        productImage.setProductId(productId);
        productImage.setImageType(type);
        productImage.setImageUrl(url);
        return productImage;
    }
}

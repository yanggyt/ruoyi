package com.ruoyi.business.sync;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.domain.BizProductImage;
import com.ruoyi.business.domain.BizProductType;
import com.ruoyi.business.mapper.BizProductMapper;
import com.ruoyi.business.mapper.BizProductTypeMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GoodsDataListener extends AnalysisEventListener<GoodsData> {

    private BizProductMapper productMapper;

    private BizProductTypeMapper productTypeMapper;

    public GoodsDataListener(BizProductMapper productMapper, BizProductTypeMapper productTypeMapper) {
        this.productMapper = productMapper;
        this.productTypeMapper = productTypeMapper;
    }

    @Override
    public void invoke(GoodsData goodsData, AnalysisContext analysisContext) {
        BizProduct product = new BizProduct();
        product.setId(Long.valueOf(goodsData.getId()));
        product.setAmount(new BigDecimal(goodsData.getUnitPrice()));
        product.setProductName(goodsData.getGoodsName());
        product.setProductCode(goodsData.getGoodsId());
        product.setRemark(goodsData.getGoodsDescribe());
        product.setSort(1);
        product.setOnlineStatus("Y".equals(goodsData.getEnable()) ? 1 : 0);
        product.setOnlineTime(DateUtils.parseDate(goodsData.getAddtime()));
        product.setCreateBy("admin");
        product.setCreateTime(DateUtils.parseDate(goodsData.getAddtime()));
        product.setProductClass("Y".equals(goodsData.getIsProduct()) ? 1 : 0);

        BizProductType productType = productTypeMapper.selectBizProductTypeByCode(goodsData.getGoodsType());
        if (!Objects.isNull(productType)) {
            product.setProductTypeId(productType.getId());
            productMapper.insertBizProduct(product);
        }

        List<BizProductImage> images = Lists.newArrayList();
        if (StringUtils.isNotEmpty(goodsData.getExterd1())) {
            BizProductImage mainImage = new BizProductImage();
            mainImage.setProductId(product.getId());
            mainImage.setImageUrl(goodsData.getExterd1());
            mainImage.setImageType(0);
            images.add(mainImage);
        }

        if (StringUtils.isNotEmpty(goodsData.getExterd2())) {
            String[] detailImages = goodsData.getExterd2().split(",");
            for (String image : detailImages) {
                BizProductImage detailImage = new BizProductImage();
                detailImage.setProductId(product.getId());
                detailImage.setImageUrl(image);
                detailImage.setImageType(1);
                images.add(detailImage);
            }
        }

        if (StringUtils.isNotEmpty(goodsData.getExterd3())) {
            String[] runImages = goodsData.getExterd3().split(",");
            for (String image : runImages) {
                BizProductImage runImage = new BizProductImage();
                runImage.setProductId(product.getId());
                runImage.setImageUrl(image);
                runImage.setImageType(2);
                images.add(runImage);
            }
        }

        if (!CollectionUtils.isEmpty(images)) {
            productMapper.batchBizProductImage(images);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

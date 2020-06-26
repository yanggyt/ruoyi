package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.business.domain.BProductClass;
import com.ruoyi.business.mapper.BProductClassMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BProductMapper;
import com.ruoyi.business.domain.BProduct;
import com.ruoyi.business.service.IBProductService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品Service业务层处理
 *
 * @author anjie
 * @date 2020-06-21
 */
@Service
public class BProductServiceImpl implements IBProductService
{
    @Autowired
    private BProductMapper bProductMapper;

    @Autowired
    private BProductClassMapper bProductClassMapper;

    /**
     * 查询产品
     *
     * @param id 产品ID
     * @return 产品
     */
    @Override
    public BProduct selectBProductById(Long id)
    {
        BProduct bProduct = bProductMapper.selectBProductById(id);
        if(bProduct.getClassId()!=null) {
            BProductClass bProductClass = bProductClassMapper.selectBProductClassById(bProduct.getClassId());
            bProduct.setClassName(bProductClass.getName());
        }
        return bProduct;
    }

    /**
     * 查询产品列表
     *
     * @param bProduct 产品
     * @return 产品
     */
    @Override
    public List<BProduct> selectBProductList(BProduct bProduct)
    {
        return bProductMapper.selectBProductList(bProduct);
    }

    /**
     * 新增产品
     *
     * @param bProduct 产品
     * @return 结果
     */
    @Override
    public int insertBProduct(BProduct bProduct)
    {
        bProduct.setCreateTime(DateUtils.getNowDate());
        return bProductMapper.insertBProduct(bProduct);
    }

    /**
     * 修改产品
     *
     * @param bProduct 产品
     * @return 结果
     */
    @Override
    public int updateBProduct(BProduct bProduct)
    {
        bProduct.setUpdateTime(DateUtils.getNowDate());
        return bProductMapper.updateBProduct(bProduct);
    }

    /**
     * 删除产品对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBProductByIds(String ids)
    {
        return bProductMapper.deleteBProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品信息
     *
     * @param id 产品ID
     * @return 结果
     */
    @Override
    public int deleteBProductById(Long id)
    {
        return bProductMapper.deleteBProductById(id);
    }
}

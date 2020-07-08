package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BProduct;

/**
 * 产品Mapper接口
 * 
 * @author anjie
 * @date 2020-06-21
 */
public interface BProductMapper 
{
    /**
     * 查询产品
     * 
     * @param id 产品ID
     * @return 产品
     */
    public BProduct selectBProductById(Long id);

    /**
     * 查询产品列表
     * 
     * @param bProduct 产品
     * @return 产品集合
     */
    public List<BProduct> selectBProductList(BProduct bProduct);

    /**
     * 新增产品
     * 
     * @param bProduct 产品
     * @return 结果
     */
    public int insertBProduct(BProduct bProduct);

    /**
     * 修改产品
     * 
     * @param bProduct 产品
     * @return 结果
     */
    public int updateBProduct(BProduct bProduct);

    /**
     * 删除产品
     * 
     * @param id 产品ID
     * @return 结果
     */
    public int deleteBProductById(Long id);

    /**
     * 批量删除产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBProductByIds(String[] ids);
}

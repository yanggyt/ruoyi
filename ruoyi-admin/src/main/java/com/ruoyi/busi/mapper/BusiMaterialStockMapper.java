package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiMaterialStock;

/**
 * 物料库存Mapper接口
 * 
 * @author WangCL
 * @date 2021-12-25
 */
public interface BusiMaterialStockMapper 
{
    /**
     * 查询物料库存
     * 
     * @param id 物料库存主键
     * @return 物料库存
     */
    public BusiMaterialStock selectBusiMaterialStockById(String id);

    /**
     * 查询物料库存列表
     * 
     * @param busiMaterialStock 物料库存
     * @return 物料库存集合
     */
    public List<BusiMaterialStock> selectBusiMaterialStockList(BusiMaterialStock busiMaterialStock);

    /**
     * 新增物料库存
     * 
     * @param busiMaterialStock 物料库存
     * @return 结果
     */
    public int insertBusiMaterialStock(BusiMaterialStock busiMaterialStock);

    /**
     * 修改物料库存
     * 
     * @param busiMaterialStock 物料库存
     * @return 结果
     */
    public int updateBusiMaterialStock(BusiMaterialStock busiMaterialStock);

    /**
     * 删除物料库存
     * 
     * @param id 物料库存主键
     * @return 结果
     */
    public int deleteBusiMaterialStockById(String id);

    /**
     * 批量删除物料库存
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiMaterialStockByIds(String[] ids);
}

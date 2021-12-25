package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiMaterialStockMapper;
import com.ruoyi.busi.domain.BusiMaterialStock;
import com.ruoyi.busi.service.IBusiMaterialStockService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物料库存Service业务层处理
 * 
 * @author WangCL
 * @date 2021-12-25
 */
@Service
public class BusiMaterialStockServiceImpl implements IBusiMaterialStockService 
{
    @Autowired
    private BusiMaterialStockMapper busiMaterialStockMapper;

    /**
     * 查询物料库存
     * 
     * @param id 物料库存主键
     * @return 物料库存
     */
    @Override
    public BusiMaterialStock selectBusiMaterialStockById(String id)
    {
        return busiMaterialStockMapper.selectBusiMaterialStockById(id);
    }

    /**
     * 查询物料库存列表
     * 
     * @param busiMaterialStock 物料库存
     * @return 物料库存
     */
    @Override
    public List<BusiMaterialStock> selectBusiMaterialStockList(BusiMaterialStock busiMaterialStock)
    {
        return busiMaterialStockMapper.selectBusiMaterialStockList(busiMaterialStock);
    }

    /**
     * 新增物料库存
     * 
     * @param busiMaterialStock 物料库存
     * @return 结果
     */
    @Override
    public int insertBusiMaterialStock(BusiMaterialStock busiMaterialStock)
    {
        busiMaterialStock.setCreateTime(DateUtils.getNowDate());
        return busiMaterialStockMapper.insertBusiMaterialStock(busiMaterialStock);
    }

    /**
     * 修改物料库存
     * 
     * @param busiMaterialStock 物料库存
     * @return 结果
     */
    @Override
    public int updateBusiMaterialStock(BusiMaterialStock busiMaterialStock)
    {
        busiMaterialStock.setUpdateTime(DateUtils.getNowDate());
        return busiMaterialStockMapper.updateBusiMaterialStock(busiMaterialStock);
    }

    /**
     * 批量删除物料库存
     * 
     * @param ids 需要删除的物料库存主键
     * @return 结果
     */
    @Override
    public int deleteBusiMaterialStockByIds(String ids)
    {
        return busiMaterialStockMapper.deleteBusiMaterialStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物料库存信息
     * 
     * @param id 物料库存主键
     * @return 结果
     */
    @Override
    public int deleteBusiMaterialStockById(String id)
    {
        return busiMaterialStockMapper.deleteBusiMaterialStockById(id);
    }
}

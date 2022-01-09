package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiProductStockMapper;
import com.ruoyi.busi.domain.BusiProductStock;
import com.ruoyi.busi.service.IBusiProductStockService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成品库存Service业务层处理
 * 
 * @author WangCL
 * @date 2022-01-08
 */
@Service
public class BusiProductStockServiceImpl implements IBusiProductStockService 
{
    @Autowired
    private BusiProductStockMapper busiProductStockMapper;

    /**
     * 查询成品库存
     * 
     * @param id 成品库存主键
     * @return 成品库存
     */
    @Override
    public BusiProductStock selectBusiProductStockById(String id)
    {
        return busiProductStockMapper.selectBusiProductStockById(id);
    }

    /**
     * 查询成品库存列表
     * 
     * @param busiProductStock 成品库存
     * @return 成品库存
     */
    @Override
    public List<BusiProductStock> selectBusiProductStockList(BusiProductStock busiProductStock)
    {
        return busiProductStockMapper.selectBusiProductStockList(busiProductStock);
    }

    /**
     * 新增成品库存
     * 
     * @param busiProductStock 成品库存
     * @return 结果
     */
    @Override
    public int insertBusiProductStock(BusiProductStock busiProductStock)
    {
        busiProductStock.setCreateTime(DateUtils.getNowDate());
        return busiProductStockMapper.insertBusiProductStock(busiProductStock);
    }

    /**
     * 修改成品库存
     * 
     * @param busiProductStock 成品库存
     * @return 结果
     */
    @Override
    public int updateBusiProductStock(BusiProductStock busiProductStock)
    {
        busiProductStock.setUpdateTime(DateUtils.getNowDate());
        return busiProductStockMapper.updateBusiProductStock(busiProductStock);
    }

    /**
     * 批量删除成品库存
     * 
     * @param ids 需要删除的成品库存主键
     * @return 结果
     */
    @Override
    public int deleteBusiProductStockByIds(String ids)
    {
        return busiProductStockMapper.deleteBusiProductStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成品库存信息
     * 
     * @param id 成品库存主键
     * @return 结果
     */
    @Override
    public int deleteBusiProductStockById(String id)
    {
        return busiProductStockMapper.deleteBusiProductStockById(id);
    }
}

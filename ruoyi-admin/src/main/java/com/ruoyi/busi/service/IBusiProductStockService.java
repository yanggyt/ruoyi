package com.ruoyi.busi.service;

import java.util.List;
import com.ruoyi.busi.domain.BusiProductStock;

/**
 * 成品库存Service接口
 * 
 * @author WangCL
 * @date 2022-01-08
 */
public interface IBusiProductStockService 
{
    /**
     * 查询成品库存
     * 
     * @param id 成品库存主键
     * @return 成品库存
     */
    public BusiProductStock selectBusiProductStockById(String id);

    /**
     * 查询成品库存列表
     * 
     * @param busiProductStock 成品库存
     * @return 成品库存集合
     */
    public List<BusiProductStock> selectBusiProductStockList(BusiProductStock busiProductStock);

    /**
     * 新增成品库存
     * 
     * @param busiProductStock 成品库存
     * @return 结果
     */
    public int insertBusiProductStock(BusiProductStock busiProductStock);

    /**
     * 修改成品库存
     * 
     * @param busiProductStock 成品库存
     * @return 结果
     */
    public int updateBusiProductStock(BusiProductStock busiProductStock);

    /**
     * 批量删除成品库存
     * 
     * @param ids 需要删除的成品库存主键集合
     * @return 结果
     */
    public int deleteBusiProductStockByIds(String ids);

    /**
     * 删除成品库存信息
     * 
     * @param id 成品库存主键
     * @return 结果
     */
    public int deleteBusiProductStockById(String id);
}

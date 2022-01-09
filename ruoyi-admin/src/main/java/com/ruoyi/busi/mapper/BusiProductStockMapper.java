package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiProductStock;

/**
 * 成品库存Mapper接口
 * 
 * @author WangCL
 * @date 2022-01-08
 */
public interface BusiProductStockMapper 
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
     * 删除成品库存
     * 
     * @param id 成品库存主键
     * @return 结果
     */
    public int deleteBusiProductStockById(String id);

    /**
     * 批量删除成品库存
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiProductStockByIds(String[] ids);
}

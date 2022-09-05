package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Commodity;

import java.util.List;

/**
 * CC码Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
public interface CommodityMapper 
{
    /**
     * 查询CC码
     * 
     * @param commodityNo CC码主键
     * @return CC码
     */
    public Commodity selectCommodityByCommodityNo(Long commodityNo);

    /**
     * 查询CC码列表
     * 
     * @param commodity CC码
     * @return CC码集合
     */
    public List<Commodity> selectCommodityList(Commodity commodity);

    /**
     * 新增CC码
     * 
     * @param commodity CC码
     * @return 结果
     */
    public int insertCommodity(Commodity commodity);

    /**
     * 修改CC码
     * 
     * @param commodity CC码
     * @return 结果
     */
    public int updateCommodity(Commodity commodity);

    /**
     * 删除CC码
     * 
     * @param commodityNo CC码主键
     * @return 结果
     */
    public int deleteCommodityByCommodityNo(Long commodityNo);

    /**
     * 批量删除CC码
     * 
     * @param commodityNos 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommodityByCommodityNos(String[] commodityNos);
}

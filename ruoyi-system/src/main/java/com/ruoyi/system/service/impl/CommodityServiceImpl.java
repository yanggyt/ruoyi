package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.Commodity;
import com.ruoyi.system.mapper.CommodityMapper;
import com.ruoyi.system.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CC码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
@Service
public class CommodityServiceImpl implements ICommodityService 
{
    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 查询CC码
     * 
     * @param commodityNo CC码主键
     * @return CC码
     */
    @Override
    public Commodity selectCommodityByCommodityNo(Long commodityNo)
    {
        return commodityMapper.selectCommodityByCommodityNo(commodityNo);
    }

    /**
     * 查询CC码列表
     * 
     * @param commodity CC码
     * @return CC码
     */
    @Override
    public List<Commodity> selectCommodityList(Commodity commodity)
    {
        return commodityMapper.selectCommodityList(commodity);
    }

    /**
     * 新增CC码
     * 
     * @param commodity CC码
     * @return 结果
     */
    @Override
    public int insertCommodity(Commodity commodity)
    {
        return commodityMapper.insertCommodity(commodity);
    }

    /**
     * 修改CC码
     * 
     * @param commodity CC码
     * @return 结果
     */
    @Override
    public int updateCommodity(Commodity commodity)
    {
        return commodityMapper.updateCommodity(commodity);
    }

    /**
     * 批量删除CC码
     * 
     * @param commodityNos 需要删除的CC码主键
     * @return 结果
     */
    @Override
    public int deleteCommodityByCommodityNos(String commodityNos)
    {
        return commodityMapper.deleteCommodityByCommodityNos(Convert.toStrArray(commodityNos));
    }

    /**
     * 删除CC码信息
     * 
     * @param commodityNo CC码主键
     * @return 结果
     */
    @Override
    public int deleteCommodityByCommodityNo(Long commodityNo)
    {
        return commodityMapper.deleteCommodityByCommodityNo(commodityNo);
    }
}

package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.LiquidMapper;
import com.ruoyi.system.domain.Liquid;
import com.ruoyi.system.service.ILiquidService;
import com.ruoyi.common.core.text.Convert;

/**
 * 液体数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class LiquidServiceImpl implements ILiquidService 
{
    @Autowired
    private LiquidMapper liquidMapper;

    /**
     * 查询液体数据
     * 
     * @param liquidNumber 液体数据ID
     * @return 液体数据
     */
    @Override
    public Liquid selectLiquidById(Long liquidNumber)
    {
        return liquidMapper.selectLiquidById(liquidNumber);
    }

    /**
     * 查询液体数据列表
     * 
     * @param liquid 液体数据
     * @return 液体数据
     */
    @Override
    public List<Liquid> selectLiquidList(Liquid liquid)
    {
        return liquidMapper.selectLiquidList(liquid);
    }

    /**
     * 新增液体数据
     * 
     * @param liquid 液体数据
     * @return 结果
     */
    @Override
    public int insertLiquid(Liquid liquid)
    {
        return liquidMapper.insertLiquid(liquid);
    }

    /**
     * 修改液体数据
     * 
     * @param liquid 液体数据
     * @return 结果
     */
    @Override
    public int updateLiquid(Liquid liquid)
    {
        return liquidMapper.updateLiquid(liquid);
    }

    /**
     * 删除液体数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLiquidByIds(String ids)
    {
        return liquidMapper.deleteLiquidByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除液体数据信息
     * 
     * @param liquidNumber 液体数据ID
     * @return 结果
     */
    @Override
    public int deleteLiquidById(Long liquidNumber)
    {
        return liquidMapper.deleteLiquidById(liquidNumber);
    }
}

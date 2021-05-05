package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Liquid;

/**
 * 液体数据Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface LiquidMapper 
{
    /**
     * 查询液体数据
     * 
     * @param liquidNumber 液体数据ID
     * @return 液体数据
     */
    public Liquid selectLiquidById(Long liquidNumber);

    /**
     * 查询液体数据列表
     * 
     * @param liquid 液体数据
     * @return 液体数据集合
     */
    public List<Liquid> selectLiquidList(Liquid liquid);

    /**
     * 新增液体数据
     * 
     * @param liquid 液体数据
     * @return 结果
     */
    public int insertLiquid(Liquid liquid);

    /**
     * 修改液体数据
     * 
     * @param liquid 液体数据
     * @return 结果
     */
    public int updateLiquid(Liquid liquid);

    /**
     * 删除液体数据
     * 
     * @param liquidNumber 液体数据ID
     * @return 结果
     */
    public int deleteLiquidById(Long liquidNumber);

    /**
     * 批量删除液体数据
     * 
     * @param liquidNumbers 需要删除的数据ID
     * @return 结果
     */
    public int deleteLiquidByIds(String[] liquidNumbers);
}

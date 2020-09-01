package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisFeeItem;

import java.util.List;

/**
 * 费用类型Service接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface IHisFeeItemService
{
    /**
     * 查询费用类型
     * 
     * @param id 费用类型ID
     * @return 费用类型
     */
    public HisFeeItem selectHisFeeItemById(Long id);

    /**
     * 查询费用类型
     *
     * @param hisFeeItem 费用类型
     * @return 费用类型
     */
    public HisFeeItem selectHisFeeItem(HisFeeItem hisFeeItem);

    /**
     * 查询费用类型列表
     * 
     * @param hisFeeItem 费用类型
     * @return 费用类型集合
     */
    public List<HisFeeItem> selectHisFeeItemList(HisFeeItem hisFeeItem);

    /**
     * 新增费用类型
     * 
     * @param hisFeeItem 费用类型
     * @return 结果
     */
    public int insertHisFeeItem(HisFeeItem hisFeeItem);

    /**
     * 批量新增费用类型
     *
     * @param hisFeeItemList 费用类型列表
     * @return 结果
     */
    public int insertHisFeeItemBatch(List<HisFeeItem> hisFeeItemList);

    /**
     * 修改费用类型
     * 
     * @param hisFeeItem 费用类型
     * @return 结果
     */
    public int updateHisFeeItem(HisFeeItem hisFeeItem);

    /**
     * 批量删除费用类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisFeeItemByIds(String ids);

    /**
     * 删除费用类型信息
     * 
     * @param id 费用类型ID
     * @return 结果
     */
    public int deleteHisFeeItemById(Long id);

    public int changeStatus(HisFeeItem hisFeeItem);
}

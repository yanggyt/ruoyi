package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisFeeItem;

import java.util.List;

/**
 * 费用类型Mapper接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface HisFeeItemMapper extends RuoYiBaseMapper<HisFeeItem>
{

    /**
     * 查询费用类型
     *
     * @param id 费用类型ID
     * @return 费用类型
     */
    public HisFeeItem selectHisFeeItemById(Long id);

    /**
     * 查询费用类型列表
     * 
     * @param hisFeeItem 费用类型
     * @return 费用类型集合
     */
    public List<HisFeeItem> selectHisFeeItemList(HisFeeItem hisFeeItem);


}

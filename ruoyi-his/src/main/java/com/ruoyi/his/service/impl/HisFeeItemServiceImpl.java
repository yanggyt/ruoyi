package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisFeeItemMapper;
import com.ruoyi.his.domain.HisFeeItem;
import com.ruoyi.his.service.IHisFeeItemService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 费用类型Service业务层处理
 * 
 * @author bend
 * @date 2020-07-14
 */
@Service
public class HisFeeItemServiceImpl implements IHisFeeItemService
{
    @Resource
    private HisFeeItemMapper hisFeeItemMapper;

    /**
     * 查询费用类型
     * 
     * @param id 费用类型ID
     * @return 费用类型
     */
    @Override
    public HisFeeItem selectHisFeeItemById(Long id)
    {
        return hisFeeItemMapper.selectHisFeeItemById(id);
    }

    /**
     * 查询费用类型
     *
     * @param hisFeeItem 费用类型ID
     * @return 费用类型
     */
    @Override
    public HisFeeItem selectHisFeeItem(HisFeeItem hisFeeItem)
    {
        return hisFeeItemMapper.selectOne(hisFeeItem);
    }

    /**
     * 查询费用类型列表
     * 
     * @param hisFeeItem 费用类型
     * @return 费用类型
     */
    @Override
    public List<HisFeeItem> selectHisFeeItemList(HisFeeItem hisFeeItem)
    {
        return hisFeeItemMapper.selectHisFeeItemList(hisFeeItem);
    }

    /**
     * 新增费用类型
     * 
     * @param hisFeeItem 费用类型
     * @return 结果
     */
    @Override
    public int insertHisFeeItem(HisFeeItem hisFeeItem)
    {
        return hisFeeItemMapper.insertSelective(hisFeeItem);
    }

    /**
     * 批量新增费用类型
     *
     * @param hisFeeItemList 费用类型列表
     * @return 结果
     */
    @Override
    public int insertHisFeeItemBatch(List<HisFeeItem> hisFeeItemList)
    {
        return hisFeeItemMapper.insertList(hisFeeItemList);
    }

    /**
     * 修改费用类型
     * 
     * @param hisFeeItem 费用类型
     * @return 结果
     */
    @Override
    public int updateHisFeeItem(HisFeeItem hisFeeItem)
    {
        return hisFeeItemMapper.updateByPrimaryKeySelective(hisFeeItem);
    }

    /**
     * 删除费用类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisFeeItemByIds(String ids)
    {
        return hisFeeItemMapper.deleteByIds(ids);
    }

    /**
     * 删除费用类型信息
     * 
     * @param id 费用类型ID
     * @return 结果
     */
    @Override
    public int deleteHisFeeItemById(Long id)
    {
        return hisFeeItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int changeStatus(HisFeeItem hisFeeItem)
    {
        return hisFeeItemMapper.updateByPrimaryKeySelective(hisFeeItem);
    }
}

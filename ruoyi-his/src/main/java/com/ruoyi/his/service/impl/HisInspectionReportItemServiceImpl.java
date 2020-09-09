package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisInspectionReportItemMapper;
import com.ruoyi.his.domain.HisInspectionReportItem;
import com.ruoyi.his.service.IHisInspectionReportItemService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 检查检验明细Service业务层处理
 * 
 * @author bend
 * @date 2020-07-10
 */
@Service
public class HisInspectionReportItemServiceImpl implements IHisInspectionReportItemService
{
    @Resource
    private HisInspectionReportItemMapper hisInspectionReportItemMapper;

    /**
     * 查询检查检验明细
     * 
     * @param id 检查检验明细ID
     * @return 检查检验明细
     */
    @Override
    public HisInspectionReportItem selectHisInspectionReportItemById(Long id)
    {
        return hisInspectionReportItemMapper.selectHisInspectionReportItemById(id);
    }

    /**
     * 查询检查检验明细
     *
     * @param hisInspectionReportItem 检查检验明细ID
     * @return 检查检验明细
     */
    @Override
    public HisInspectionReportItem selectHisInspectionReportItem(HisInspectionReportItem hisInspectionReportItem)
    {
        return hisInspectionReportItemMapper.selectOne(hisInspectionReportItem);
    }

    /**
     * 查询检查检验明细列表
     * 
     * @param hisInspectionReportItem 检查检验明细
     * @return 检查检验明细
     */
    @Override
    public List<HisInspectionReportItem> selectHisInspectionReportItemList(HisInspectionReportItem hisInspectionReportItem)
    {
        return hisInspectionReportItemMapper.selectHisInspectionReportItemList(hisInspectionReportItem);
    }

    /**
     * 新增检查检验明细
     * 
     * @param hisInspectionReportItem 检查检验明细
     * @return 结果
     */
    @Override
    public int insertHisInspectionReportItem(HisInspectionReportItem hisInspectionReportItem)
    {
        return hisInspectionReportItemMapper.insertSelective(hisInspectionReportItem);
    }

    /**
     * 批量新增检查检验明细
     *
     * @param hisInspectionReportItemList 检查检验明细列表
     * @return 结果
     */
    @Override
    public int insertHisInspectionReportItemBatch(List<HisInspectionReportItem> hisInspectionReportItemList)
    {
        return hisInspectionReportItemMapper.insertList(hisInspectionReportItemList);
    }

    /**
     * 修改检查检验明细
     * 
     * @param hisInspectionReportItem 检查检验明细
     * @return 结果
     */
    @Override
    public int updateHisInspectionReportItem(HisInspectionReportItem hisInspectionReportItem)
    {
        return hisInspectionReportItemMapper.updateByPrimaryKeySelective(hisInspectionReportItem);
    }

    /**
     * 删除检查检验明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisInspectionReportItemByIds(String ids)
    {
        return hisInspectionReportItemMapper.deleteByIds(ids);
    }

    /**
     * 删除检查检验明细信息
     * 
     * @param id 检查检验明细ID
     * @return 结果
     */
    @Override
    public int deleteHisInspectionReportItemById(Long id)
    {
        return hisInspectionReportItemMapper.deleteByPrimaryKey(id);
    }
}

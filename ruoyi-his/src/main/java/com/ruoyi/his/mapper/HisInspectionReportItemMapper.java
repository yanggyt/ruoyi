package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisInspectionReportItem;

import java.util.List;

/**
 * 检查检验明细Mapper接口
 * 
 * @author bend
 * @date 2020-07-10
 */
public interface HisInspectionReportItemMapper extends RuoYiBaseMapper<HisInspectionReportItem>
{

    /**
     * 查询检查检验明细
     *
     * @param id 检查检验明细ID
     * @return 检查检验明细
     */
    public HisInspectionReportItem selectHisInspectionReportItemById(Long id);

    /**
     * 查询检查检验明细列表
     * 
     * @param hisInspectionReportItem 检查检验明细
     * @return 检查检验明细集合
     */
    public List<HisInspectionReportItem> selectHisInspectionReportItemList(HisInspectionReportItem hisInspectionReportItem);


}

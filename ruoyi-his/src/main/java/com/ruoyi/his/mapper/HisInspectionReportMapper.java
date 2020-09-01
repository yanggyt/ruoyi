package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisInspectionReport;
import com.ruoyi.his.domain.HisInspectionReportItem;

import java.util.List;

/**
 * 检查检验Mapper接口
 * 
 * @author bend
 * @date 2020-07-10
 */
public interface HisInspectionReportMapper extends RuoYiBaseMapper<HisInspectionReport>
{

    /**
     * 查询检查检验
     *
     * @param id 检查检验ID
     * @return 检查检验
     */
    public HisInspectionReport selectHisInspectionReportById(Long id);

    /**
     * 查询检查检验列表
     * 
     * @param hisInspectionReport 检查检验
     * @return 检查检验集合
     */
    public List<HisInspectionReport> selectHisInspectionReportList(HisInspectionReport hisInspectionReport);


    /**
     * 批量删除检查检验明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisInspectionReportItemByReportIds(String[] ids);

    /**
     * 批量新增检查检验明细
     *
     * @param hisInspectionReportItemList 检查检验明细列表
     * @return 结果
     */
    public int batchHisInspectionReportItem(List<HisInspectionReportItem> hisInspectionReportItemList);


    /**
     * 通过检查检验ID删除检查检验明细信息
     *
     * @param id 角色ID
     * @return 结果
     */
    public int deleteHisInspectionReportItemByReportId(Long id);

}

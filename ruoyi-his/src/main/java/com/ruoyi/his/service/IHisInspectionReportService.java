package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisInspectionReport;
import com.ruoyi.his.domain.HisInspectionReportItem;

import java.util.List;

/**
 * 检查检验Service接口
 * 
 * @author bend
 * @date 2020-07-10
 */
public interface IHisInspectionReportService
{
    /**
     * 查询检查检验
     * 
     * @param id 检查检验ID
     * @return 检查检验
     */
    public HisInspectionReport selectHisInspectionReportById(Long id);

    /**
     * 查询检查检验
     *
     * @param hisInspectionReport 检查检验
     * @return 检查检验
     */
    public HisInspectionReport selectHisInspectionReport(HisInspectionReport hisInspectionReport);

    /**
     * 查询检查检验列表
     * 
     * @param hisInspectionReport 检查检验
     * @return 检查检验集合
     */
    public List<HisInspectionReport> selectHisInspectionReportList(HisInspectionReport hisInspectionReport);

    /**
     * 新增检查检验
     * 
     * @param hisInspectionReport 检查检验
     * @return 结果
     */
    public int insertHisInspectionReport(HisInspectionReport hisInspectionReport);

    /**
     * 批量新增检查检验
     *
     * @param hisInspectionReportList 检查检验列表
     * @return 结果
     */
    public int insertHisInspectionReportBatch(List<HisInspectionReport> hisInspectionReportList);

    /**
     * 修改检查检验
     * 
     * @param hisInspectionReport 检查检验
     * @return 结果
     */
    public int updateHisInspectionReport(HisInspectionReport hisInspectionReport);

    /**
     * 批量删除检查检验
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisInspectionReportByIds(String ids);

    /**
     * 删除检查检验信息
     * 
     * @param id 检查检验ID
     * @return 结果
     */
    public int deleteHisInspectionReportById(Long id);

    /**
     *
     * @param hisInspectionReportItem 检查检验项目详情
     * @return 检查检验项目详情列表
     */
    public List<HisInspectionReportItem> selectHisInspectionReportItemList(HisInspectionReportItem hisInspectionReportItem);
}

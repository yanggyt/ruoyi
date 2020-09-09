package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisInspectionReportItem;

import java.util.List;

/**
 * 检查检验明细Service接口
 * 
 * @author bend
 * @date 2020-07-10
 */
public interface IHisInspectionReportItemService
{
    /**
     * 查询检查检验明细
     * 
     * @param id 检查检验明细ID
     * @return 检查检验明细
     */
    public HisInspectionReportItem selectHisInspectionReportItemById(Long id);

    /**
     * 查询检查检验明细
     *
     * @param hisInspectionReportItem 检查检验明细
     * @return 检查检验明细
     */
    public HisInspectionReportItem selectHisInspectionReportItem(HisInspectionReportItem hisInspectionReportItem);

    /**
     * 查询检查检验明细列表
     * 
     * @param hisInspectionReportItem 检查检验明细
     * @return 检查检验明细集合
     */
    public List<HisInspectionReportItem> selectHisInspectionReportItemList(HisInspectionReportItem hisInspectionReportItem);

    /**
     * 新增检查检验明细
     * 
     * @param hisInspectionReportItem 检查检验明细
     * @return 结果
     */
    public int insertHisInspectionReportItem(HisInspectionReportItem hisInspectionReportItem);

    /**
     * 批量新增检查检验明细
     *
     * @param hisInspectionReportItemList 检查检验明细列表
     * @return 结果
     */
    public int insertHisInspectionReportItemBatch(List<HisInspectionReportItem> hisInspectionReportItemList);

    /**
     * 修改检查检验明细
     * 
     * @param hisInspectionReportItem 检查检验明细
     * @return 结果
     */
    public int updateHisInspectionReportItem(HisInspectionReportItem hisInspectionReportItem);

    /**
     * 批量删除检查检验明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisInspectionReportItemByIds(String ids);

    /**
     * 删除检查检验明细信息
     * 
     * @param id 检查检验明细ID
     * @return 结果
     */
    public int deleteHisInspectionReportItemById(Long id);
}

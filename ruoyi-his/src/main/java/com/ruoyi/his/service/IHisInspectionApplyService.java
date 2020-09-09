package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisInspectionApply;

import java.util.List;

/**
 * 申请单Service接口
 * 
 * @author bend
 * @date 2020-07-10
 */
public interface IHisInspectionApplyService
{
    /**
     * 查询申请单
     * 
     * @param id 申请单ID
     * @return 申请单
     */
    public HisInspectionApply selectHisInspectionApplyById(Long id);

    /**
     * 查询申请单
     *
     * @param hisInspectionApply 申请单
     * @return 申请单
     */
    public HisInspectionApply selectHisInspectionApply(HisInspectionApply hisInspectionApply);

    /**
     * 查询申请单列表
     * 
     * @param hisInspectionApply 申请单
     * @return 申请单集合
     */
    public List<HisInspectionApply> selectHisInspectionApplyList(HisInspectionApply hisInspectionApply);

    /**
     * 新增申请单
     * 
     * @param hisInspectionApply 申请单
     * @return 结果
     */
    public int insertHisInspectionApply(HisInspectionApply hisInspectionApply);

    /**
     * 批量新增申请单
     *
     * @param hisInspectionApplyList 申请单列表
     * @return 结果
     */
    public int insertHisInspectionApplyBatch(List<HisInspectionApply> hisInspectionApplyList);

    /**
     * 修改申请单
     * 
     * @param hisInspectionApply 申请单
     * @return 结果
     */
    public int updateHisInspectionApply(HisInspectionApply hisInspectionApply);

    /**
     * 批量删除申请单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisInspectionApplyByIds(String ids);

    /**
     * 删除申请单信息
     * 
     * @param id 申请单ID
     * @return 结果
     */
    public int deleteHisInspectionApplyById(Long id);
}

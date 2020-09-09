package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisOutpatientPayment;

import java.util.List;

/**
 * 门诊预交Service接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface IHisOutpatientPaymentService
{
    /**
     * 查询门诊预交
     * 
     * @param id 门诊预交ID
     * @return 门诊预交
     */
    public HisOutpatientPayment selectHisOutpatientPaymentById(Long id);

    /**
     * 查询门诊预交
     *
     * @param hisOutpatientPayment 门诊预交
     * @return 门诊预交
     */
    public HisOutpatientPayment selectHisOutpatientPayment(HisOutpatientPayment hisOutpatientPayment);

    /**
     * 查询门诊预交列表
     * 
     * @param hisOutpatientPayment 门诊预交
     * @return 门诊预交集合
     */
    public List<HisOutpatientPayment> selectHisOutpatientPaymentList(HisOutpatientPayment hisOutpatientPayment);

    /**
     * 新增门诊预交
     * 
     * @param hisOutpatientPayment 门诊预交
     * @return 结果
     */
    public int insertHisOutpatientPayment(HisOutpatientPayment hisOutpatientPayment);

    /**
     * 批量新增门诊预交
     *
     * @param hisOutpatientPaymentList 门诊预交列表
     * @return 结果
     */
    public int insertHisOutpatientPaymentBatch(List<HisOutpatientPayment> hisOutpatientPaymentList);

    /**
     * 修改门诊预交
     * 
     * @param hisOutpatientPayment 门诊预交
     * @return 结果
     */
    public int updateHisOutpatientPayment(HisOutpatientPayment hisOutpatientPayment);

    /**
     * 批量删除门诊预交
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisOutpatientPaymentByIds(String ids);

    /**
     * 删除门诊预交信息
     * 
     * @param id 门诊预交ID
     * @return 结果
     */
    public int deleteHisOutpatientPaymentById(Long id);
}

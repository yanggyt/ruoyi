package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisPatientExpensesDetail;

import java.util.List;

/**
 * 费用详情Service接口
 * 
 * @author bend
 * @date 2020-07-09
 */
public interface IHisPatientExpensesDetailService
{

    /**
     * 查询费用详情列表
     * 
     * @param hisPatientExpensesDetail 费用详情
     * @return 费用详情集合
     */
    public List<HisPatientExpensesDetail> selectHisPatientExpensesDetailList(HisPatientExpensesDetail hisPatientExpensesDetail);

}

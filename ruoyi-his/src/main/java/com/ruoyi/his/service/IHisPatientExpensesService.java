package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisPatientExpenses;
import com.ruoyi.his.domain.HisPatientExpensesDetail;

import java.util.List;

/**
 * 费用记录Service接口
 * 
 * @author bend
 * @date 2020-07-09
 */
public interface IHisPatientExpensesService
{
    /**
     * 查询费用记录
     * 
     * @param id 费用记录ID
     * @return 费用记录
     */
    public HisPatientExpenses selectHisPatientExpensesById(Long id);

    /**
     * 查询费用记录
     *
     * @param hisPatientExpenses 费用记录
     * @return 费用记录
     */
    public HisPatientExpenses selectHisPatientExpenses(HisPatientExpenses hisPatientExpenses);

    /**
     * 查询费用记录列表
     * 
     * @param hisPatientExpenses 费用记录
     * @return 费用记录集合
     */
    public List<HisPatientExpenses> selectHisPatientExpensesList(HisPatientExpenses hisPatientExpenses);

    /**
     * 新增费用记录
     * 
     * @param hisPatientExpenses 费用记录
     * @return 结果
     */
    public int insertHisPatientExpenses(HisPatientExpenses hisPatientExpenses);

    /**
     * 批量新增费用记录
     *
     * @param hisPatientExpensesList 费用记录列表
     */
    public void insertHisPatientExpensesBatch(List<HisPatientExpenses> hisPatientExpensesList);

    /**
     * 修改费用记录
     * 
     * @param hisPatientExpenses 费用记录
     * @return 结果
     */
    public int updateHisPatientExpenses(HisPatientExpenses hisPatientExpenses);

    /**
     * 批量删除费用记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisPatientExpensesByIds(String ids);

    /**
     * 删除费用记录信息
     * 
     * @param id 费用记录ID
     * @return 结果
     */
    public int deleteHisPatientExpensesById(Long id);

    /**
     *
     * @param hisPatientExpensesList 费用列表
     */
    public void updateHisPatientExpensesBatch(List<HisPatientExpenses> hisPatientExpensesList);

    /**
     *
     * @param hisPatientExpensesDetail 费用详情
     * @return 费用详情列表
     */
    public List<HisPatientExpensesDetail> selectHisPatientExpensesDetailList(HisPatientExpensesDetail hisPatientExpensesDetail);

    /**
     *
     * @param hisPatientExpensesList
     */
    public void deleteHisPatientExpensesBatch(List<HisPatientExpenses> hisPatientExpensesList);
}

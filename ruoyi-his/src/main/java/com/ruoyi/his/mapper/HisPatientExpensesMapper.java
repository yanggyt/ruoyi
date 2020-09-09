package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisPatientExpenses;
import com.ruoyi.his.domain.HisPatientExpensesDetail;

import java.util.List;

/**
 * 费用记录Mapper接口
 * 
 * @author bend
 * @date 2020-07-09
 */
public interface HisPatientExpensesMapper extends RuoYiBaseMapper<HisPatientExpenses>
{

    /**
     * 查询费用记录
     *
     * @param id 费用记录ID
     * @return 费用记录
     */
    public HisPatientExpenses selectHisPatientExpensesById(Long id);

    /**
     * 查询费用记录列表
     * 
     * @param hisPatientExpenses 费用记录
     * @return 费用记录集合
     */
    public List<HisPatientExpenses> selectHisPatientExpensesList(HisPatientExpenses hisPatientExpenses);


    /**
     * 批量删除费用详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisPatientExpensesDetailByExpensesIds(String[] ids);

    /**
     * 批量新增费用详情
     *
     * @param hisPatientExpensesDetailList 费用详情列表
     * @return 结果
     */
    public int batchHisPatientExpensesDetail(List<HisPatientExpensesDetail> hisPatientExpensesDetailList);


    /**
     * 通过费用记录ID删除费用详情信息
     *
     * @param id 角色ID
     * @return 结果
     */
    public int deleteHisPatientExpensesDetailByExpensesId(Long id);

}

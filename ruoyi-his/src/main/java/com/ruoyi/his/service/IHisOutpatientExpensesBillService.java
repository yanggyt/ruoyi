package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisOutpatientExpensesBill;
import com.ruoyi.his.domain.HisOutpatientExpensesBillDetail;

import java.util.List;

/**
 * 待缴费单Service接口
 *
 * @author bend
 * @date 2020-07-09
 */
public interface IHisOutpatientExpensesBillService {
    /**
     * 查询待缴费单
     *
     * @param id 待缴费单ID
     * @return 待缴费单
     */
    public HisOutpatientExpensesBill selectHisOutpatientExpensesBillById(Long id);

    /**
     * 查询待缴费单
     *
     * @param hisOutpatientExpensesBill 待缴费单
     * @return 待缴费单
     */
    public HisOutpatientExpensesBill selectHisOutpatientExpensesBill(HisOutpatientExpensesBill hisOutpatientExpensesBill);

    /**
     * 查询待缴费单列表
     *
     * @param hisOutpatientExpensesBill 待缴费单
     * @return 待缴费单集合
     */
    public List<HisOutpatientExpensesBill> selectHisOutpatientExpensesBillList(HisOutpatientExpensesBill hisOutpatientExpensesBill);

    /**
     * 新增待缴费单
     *
     * @param hisOutpatientExpensesBill 待缴费单
     * @return 结果
     */
    public int insertHisOutpatientExpensesBill(HisOutpatientExpensesBill hisOutpatientExpensesBill);

    /**
     * 批量新增待缴费单
     *
     * @param hisOutpatientExpensesBillList 待缴费单列表
     * @return 结果
     */
    public int insertHisOutpatientExpensesBillBatch(List<HisOutpatientExpensesBill> hisOutpatientExpensesBillList);

    /**
     * 修改待缴费单
     *
     * @param hisOutpatientExpensesBill 待缴费单
     * @return 结果
     */
    public int updateHisOutpatientExpensesBill(HisOutpatientExpensesBill hisOutpatientExpensesBill);

    /**
     * 批量删除待缴费单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisOutpatientExpensesBillByIds(String ids);

    /**
     * 删除待缴费单信息
     *
     * @param id 待缴费单ID
     * @return 结果
     */
    public int deleteHisOutpatientExpensesBillById(Long id);

    /**
     * 查询待缴费单详情
     *
     * @param hisOutpatientExpensesBillDetail 待缴费单详情
     * @return 列表
     */
    public List<HisOutpatientExpensesBillDetail> selectHisOutpatientExpensesBillDetailList(HisOutpatientExpensesBillDetail hisOutpatientExpensesBillDetail);

    /**
     * 批量更新
     *
     * @param hisOutpatientExpensesBillUpdateList 待缴费单详情
     */
    public void updateHisOutpatientExpensesBillBatch(List<HisOutpatientExpensesBill> hisOutpatientExpensesBillUpdateList);

    /**
     * 更新状态
     *
     * @param hisOutpatientExpensesBill 待缴费单
     */
    public void updateHisOutpatientExpensesBillStatus(HisOutpatientExpensesBill hisOutpatientExpensesBill);

    /**
     * 批量删除
     *
     * @param hisOutpatientExpensesBillList
     */
    public void deleteHisOutpatientExpensesBillBatch(List<HisOutpatientExpensesBill> hisOutpatientExpensesBillList);
}

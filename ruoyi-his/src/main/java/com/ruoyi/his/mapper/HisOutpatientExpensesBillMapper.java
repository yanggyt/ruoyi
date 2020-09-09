package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisOutpatientExpensesBill;
import com.ruoyi.his.domain.HisOutpatientExpensesBillDetail;

import java.util.List;

/**
 * 待缴费单Mapper接口
 * 
 * @author bend
 * @date 2020-07-09
 */
public interface HisOutpatientExpensesBillMapper extends RuoYiBaseMapper<HisOutpatientExpensesBill>
{

    /**
     * 查询待缴费单
     *
     * @param id 待缴费单ID
     * @return 待缴费单
     */
    public HisOutpatientExpensesBill selectHisOutpatientExpensesBillById(Long id);

    /**
     * 查询待缴费单列表
     * 
     * @param hisOutpatientExpensesBill 待缴费单
     * @return 待缴费单集合
     */
    public List<HisOutpatientExpensesBill> selectHisOutpatientExpensesBillList(HisOutpatientExpensesBill hisOutpatientExpensesBill);


    /**
     * 批量删除清单详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisOutpatientExpensesBillDetailByBillIds(String[] ids);

    /**
     * 批量新增清单详情
     *
     * @param hisOutpatientExpensesBillDetailList 清单详情列表
     * @return 结果
     */
    public int batchHisOutpatientExpensesBillDetail(List<HisOutpatientExpensesBillDetail> hisOutpatientExpensesBillDetailList);


    /**
     * 通过待缴费单ID删除清单详情信息
     *
     * @param id 角色ID
     * @return 结果
     */
    public int deleteHisOutpatientExpensesBillDetailByBillId(Long id);

}

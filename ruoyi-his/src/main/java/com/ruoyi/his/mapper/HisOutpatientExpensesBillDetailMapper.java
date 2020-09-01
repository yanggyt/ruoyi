package com.ruoyi.his.mapper;

import com.ruoyi.his.domain.HisOutpatientExpensesBillDetail;

import java.util.List;

/**
 * 清单详情Mapper接口
 * 
 * @author bend
 * @date 2020-07-09
 */
public interface HisOutpatientExpensesBillDetailMapper
{

    /**
     * 查询清单详情
     *
     * @param id 清单详情ID
     * @return 清单详情
     */
    public HisOutpatientExpensesBillDetail selectHisOutpatientExpensesBillDetailById(String id);

    /**
     * 查询清单详情列表
     * 
     * @param hisOutpatientExpensesBillDetail 清单详情
     * @return 清单详情集合
     */
    public List<HisOutpatientExpensesBillDetail> selectHisOutpatientExpensesBillDetailList(HisOutpatientExpensesBillDetail hisOutpatientExpensesBillDetail);


}

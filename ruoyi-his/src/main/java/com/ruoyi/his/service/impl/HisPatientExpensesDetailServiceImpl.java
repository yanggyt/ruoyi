package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisPatientExpensesDetailMapper;
import com.ruoyi.his.domain.HisPatientExpensesDetail;
import com.ruoyi.his.service.IHisPatientExpensesDetailService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 费用详情Service业务层处理
 * 
 * @author bend
 * @date 2020-07-09
 */
@Service
public class HisPatientExpensesDetailServiceImpl implements IHisPatientExpensesDetailService
{
    @Resource
    private HisPatientExpensesDetailMapper hisPatientExpensesDetailMapper;


    /**
     * 查询费用详情列表
     * 
     * @param hisPatientExpensesDetail 费用详情
     * @return 费用详情
     */
    @Override
    public List<HisPatientExpensesDetail> selectHisPatientExpensesDetailList(HisPatientExpensesDetail hisPatientExpensesDetail)
    {
        return hisPatientExpensesDetailMapper.selectHisPatientExpensesDetailList(hisPatientExpensesDetail);
    }

}

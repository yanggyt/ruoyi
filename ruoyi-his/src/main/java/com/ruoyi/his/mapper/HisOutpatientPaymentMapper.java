package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisOutpatientPayment;

import java.util.List;

/**
 * 门诊预交Mapper接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface HisOutpatientPaymentMapper extends RuoYiBaseMapper<HisOutpatientPayment>
{

    /**
     * 查询门诊预交
     *
     * @param id 门诊预交ID
     * @return 门诊预交
     */
    public HisOutpatientPayment selectHisOutpatientPaymentById(Long id);

    /**
     * 查询门诊预交列表
     * 
     * @param hisOutpatientPayment 门诊预交
     * @return 门诊预交集合
     */
    public List<HisOutpatientPayment> selectHisOutpatientPaymentList(HisOutpatientPayment hisOutpatientPayment);


}

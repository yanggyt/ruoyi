package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisInpatientPrepayment;

import java.util.List;

/**
 * 住院预交Mapper接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface HisInpatientPrepaymentMapper extends RuoYiBaseMapper<HisInpatientPrepayment>
{

    /**
     * 查询住院预交
     *
     * @param id 住院预交ID
     * @return 住院预交
     */
    public HisInpatientPrepayment selectHisInpatientPrepaymentById(Long id);

    /**
     * 查询住院预交列表
     * 
     * @param hisInpatientPrepayment 住院预交
     * @return 住院预交集合
     */
    public List<HisInpatientPrepayment> selectHisInpatientPrepaymentList(HisInpatientPrepayment hisInpatientPrepayment);


}

package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisInspectionApply;

import java.util.List;

/**
 * 申请单Mapper接口
 * 
 * @author bend
 * @date 2020-07-10
 */
public interface HisInspectionApplyMapper extends RuoYiBaseMapper<HisInspectionApply>
{

    /**
     * 查询申请单
     *
     * @param id 申请单ID
     * @return 申请单
     */
    public HisInspectionApply selectHisInspectionApplyById(Long id);

    /**
     * 查询申请单列表
     * 
     * @param hisInspectionApply 申请单
     * @return 申请单集合
     */
    public List<HisInspectionApply> selectHisInspectionApplyList(HisInspectionApply hisInspectionApply);


}

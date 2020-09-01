package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisOutpatient;

import java.util.List;

/**
 * 门诊病人Mapper接口
 * 
 * @author bend
 * @date 2020-07-08
 */
public interface HisOutpatientMapper extends RuoYiBaseMapper<HisOutpatient>
{

    /**
     * 查询门诊病人列表
     * 
     * @param hisOutpatient 门诊病人
     * @return 门诊病人集合
     */
    public List<HisOutpatient> selectHisOutpatientList(HisOutpatient hisOutpatient);

}

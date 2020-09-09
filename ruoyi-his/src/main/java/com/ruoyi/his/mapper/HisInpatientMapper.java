package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisInpatient;

import java.util.List;

/**
 * 住院病人Mapper接口
 * 
 * @author bend
 * @date 2020-07-08
 */
public interface HisInpatientMapper extends RuoYiBaseMapper<HisInpatient>
{

    /**
     * 查询住院病人列表
     * 
     * @param hisInpatient 住院病人
     * @return 住院病人集合
     */
    public List<HisInpatient> selectHisInpatientList(HisInpatient hisInpatient);

}

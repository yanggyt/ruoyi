package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisEhealthClient;

import java.util.List;

/**
 * 健康卡Mapper接口
 * 
 * @author bend
 * @date 2020-07-27
 */
public interface HisEhealthClientMapper extends RuoYiBaseMapper<HisEhealthClient>
{

    /**
     * 查询健康卡
     *
     * @param id 健康卡ID
     * @return 健康卡
     */
    public HisEhealthClient selectHisEhealthClientById(Long id);

    /**
     * 查询健康卡列表
     * 
     * @param hisEhealthClient 健康卡
     * @return 健康卡集合
     */
    public List<HisEhealthClient> selectHisEhealthClientList(HisEhealthClient hisEhealthClient);


}

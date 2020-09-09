package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisEhealthClientMapper;
import com.ruoyi.his.domain.HisEhealthClient;
import com.ruoyi.his.service.IHisEhealthClientService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 健康卡Service业务层处理
 * 
 * @author bend
 * @date 2020-07-27
 */
@Service
public class HisEhealthClientServiceImpl implements IHisEhealthClientService
{
    @Resource
    private HisEhealthClientMapper hisEhealthClientMapper;

    /**
     * 查询健康卡
     * 
     * @param id 健康卡ID
     * @return 健康卡
     */
    @Override
    public HisEhealthClient selectHisEhealthClientById(Long id)
    {
        return hisEhealthClientMapper.selectHisEhealthClientById(id);
    }

    /**
     * 查询健康卡
     *
     * @param hisEhealthClient 健康卡ID
     * @return 健康卡
     */
    @Override
    public HisEhealthClient selectHisEhealthClient(HisEhealthClient hisEhealthClient)
    {
        return hisEhealthClientMapper.selectOne(hisEhealthClient);
    }

    /**
     * 查询健康卡列表
     * 
     * @param hisEhealthClient 健康卡
     * @return 健康卡
     */
    @Override
    public List<HisEhealthClient> selectHisEhealthClientList(HisEhealthClient hisEhealthClient)
    {
        return hisEhealthClientMapper.selectHisEhealthClientList(hisEhealthClient);
    }

    /**
     * 新增健康卡
     * 
     * @param hisEhealthClient 健康卡
     * @return 结果
     */
    @Override
    public int insertHisEhealthClient(HisEhealthClient hisEhealthClient)
    {
        hisEhealthClient.setCreateTime(DateUtils.getNowDate());
        return hisEhealthClientMapper.insertSelective(hisEhealthClient);
    }

    /**
     * 批量新增健康卡
     *
     * @param hisEhealthClientList 健康卡列表
     * @return 结果
     */
    @Override
    public int insertHisEhealthClientBatch(List<HisEhealthClient> hisEhealthClientList)
    {
        return hisEhealthClientMapper.insertList(hisEhealthClientList);
    }

    /**
     * 修改健康卡
     * 
     * @param hisEhealthClient 健康卡
     * @return 结果
     */
    @Override
    public int updateHisEhealthClient(HisEhealthClient hisEhealthClient)
    {
        return hisEhealthClientMapper.updateByPrimaryKeySelective(hisEhealthClient);
    }

    /**
     * 删除健康卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisEhealthClientByIds(String ids)
    {
        return hisEhealthClientMapper.deleteByIds(ids);
    }

    /**
     * 删除健康卡信息
     * 
     * @param id 健康卡ID
     * @return 结果
     */
    @Override
    public int deleteHisEhealthClientById(Long id)
    {
        return hisEhealthClientMapper.deleteByPrimaryKey(id);
    }
}

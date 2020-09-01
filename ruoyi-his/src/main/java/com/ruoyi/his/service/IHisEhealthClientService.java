package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisEhealthClient;

import java.util.List;

/**
 * 健康卡Service接口
 * 
 * @author bend
 * @date 2020-07-27
 */
public interface IHisEhealthClientService
{
    /**
     * 查询健康卡
     * 
     * @param id 健康卡ID
     * @return 健康卡
     */
    public HisEhealthClient selectHisEhealthClientById(Long id);

    /**
     * 查询健康卡
     *
     * @param hisEhealthClient 健康卡
     * @return 健康卡
     */
    public HisEhealthClient selectHisEhealthClient(HisEhealthClient hisEhealthClient);

    /**
     * 查询健康卡列表
     * 
     * @param hisEhealthClient 健康卡
     * @return 健康卡集合
     */
    public List<HisEhealthClient> selectHisEhealthClientList(HisEhealthClient hisEhealthClient);

    /**
     * 新增健康卡
     * 
     * @param hisEhealthClient 健康卡
     * @return 结果
     */
    public int insertHisEhealthClient(HisEhealthClient hisEhealthClient);

    /**
     * 批量新增健康卡
     *
     * @param hisEhealthClientList 健康卡列表
     * @return 结果
     */
    public int insertHisEhealthClientBatch(List<HisEhealthClient> hisEhealthClientList);

    /**
     * 修改健康卡
     * 
     * @param hisEhealthClient 健康卡
     * @return 结果
     */
    public int updateHisEhealthClient(HisEhealthClient hisEhealthClient);

    /**
     * 批量删除健康卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisEhealthClientByIds(String ids);

    /**
     * 删除健康卡信息
     * 
     * @param id 健康卡ID
     * @return 结果
     */
    public int deleteHisEhealthClientById(Long id);
}

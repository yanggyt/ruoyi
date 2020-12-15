package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysBillNo;

/**
 * 单据号迭代信息Service接口
 * 
 * @author dalin
 * @date 2020-12-15
 */
public interface ISysBillNoService 
{
    /**
     * 查询单据号迭代信息
     * 
     * @param fperiod 单据号迭代信息ID
     * @return 单据号迭代信息
     */
    public SysBillNo selectSysBillNoById(String fperiod);

    /**
     * 查询单据号迭代信息列表
     * 
     * @param sysBillNo 单据号迭代信息
     * @return 单据号迭代信息集合
     */
    public List<SysBillNo> selectSysBillNoList(SysBillNo sysBillNo);

    /**
     * 新增单据号迭代信息
     * 
     * @param sysBillNo 单据号迭代信息
     * @return 结果
     */
    public int insertSysBillNo(SysBillNo sysBillNo);

    /**
     * 修改单据号迭代信息
     * 
     * @param sysBillNo 单据号迭代信息
     * @return 结果
     */
    public int updateSysBillNo(SysBillNo sysBillNo);

    /**
     * 批量删除单据号迭代信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBillNoByIds(String ids);

    /**
     * 删除单据号迭代信息信息
     * 
     * @param fperiod 单据号迭代信息ID
     * @return 结果
     */
    public int deleteSysBillNoById(String fperiod);
}

package com.ruoyi.province.platform.mapper;

import com.ruoyi.province.platform.domain.SysBillNo;

import java.util.List;

/**
 * 单据号迭代信息Mapper接口
 * 
 * @author dalin
 * @date 2020-12-19
 */
public interface SysBillNoMapper 
{
    /**
     * 查询单据号迭代信息
     * 
     * @param id 单据号迭代信息ID
     * @return 单据号迭代信息
     */
    public SysBillNo selectSysBillNoById(Long id);

    /**
     * 查询单据号迭代信息
     *
     * @param EntityId 单据号迭代信息ID
     * @return 单据号迭代信息
     */
    public SysBillNo selectMenusByEntityId(Long id);


    public SysBillNo selectSysBillNoByPeriod(SysBillNo sysBillNo);

    /**
     * 查询单据号迭代信息列表
     * 
     * @param sysBillNo 单据号迭代信息
     * @return 单据号迭代信息集合
     */
    public List<SysBillNo> selectSysBillNoList(SysBillNo sysBillNo);

    public int insertDuplicateByPeriod(SysBillNo sysBillNo);

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
     * 删除单据号迭代信息
     * 
     * @param id 单据号迭代信息ID
     * @return 结果
     */
    public int deleteSysBillNoById(Long id);

    /**
     * 批量删除单据号迭代信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBillNoByIds(String[] ids);
}

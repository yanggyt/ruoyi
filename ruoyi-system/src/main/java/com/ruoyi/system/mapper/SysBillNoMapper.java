package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysBillNo;

/**
 * 单据号迭代信息Mapper接口
 * 
 * @author dalin
 * @date 2020-12-15
 */
public interface SysBillNoMapper 
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
     * 删除单据号迭代信息
     * 
     * @param fperiod 单据号迭代信息ID
     * @return 结果
     */
    public int deleteSysBillNoById(String fperiod);

    /**
     * 批量删除单据号迭代信息
     * 
     * @param fperiods 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBillNoByIds(String[] fperiods);

    /**
     * 查询单据前缀是否重复
     * @param billPrefix
     * 			单据前缀
     * @return
     */
    public SysBillNo selectSysBillNoByBillPrefix(String billPrefix);
}

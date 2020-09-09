package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisInpatientPrepayment;

import java.util.List;

/**
 * 住院预交Service接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface IHisInpatientPrepaymentService
{
    /**
     * 查询住院预交
     * 
     * @param id 住院预交ID
     * @return 住院预交
     */
    public HisInpatientPrepayment selectHisInpatientPrepaymentById(Long id);

    /**
     * 查询住院预交
     *
     * @param hisInpatientPrepayment 住院预交
     * @return 住院预交
     */
    public HisInpatientPrepayment selectHisInpatientPrepayment(HisInpatientPrepayment hisInpatientPrepayment);

    /**
     * 查询住院预交列表
     * 
     * @param hisInpatientPrepayment 住院预交
     * @return 住院预交集合
     */
    public List<HisInpatientPrepayment> selectHisInpatientPrepaymentList(HisInpatientPrepayment hisInpatientPrepayment);

    /**
     * 新增住院预交
     * 
     * @param hisInpatientPrepayment 住院预交
     * @return 结果
     */
    public int insertHisInpatientPrepayment(HisInpatientPrepayment hisInpatientPrepayment);

    /**
     * 批量新增住院预交
     *
     * @param hisInpatientPrepaymentList 住院预交列表
     * @return 结果
     */
    public int insertHisInpatientPrepaymentBatch(List<HisInpatientPrepayment> hisInpatientPrepaymentList);

    /**
     * 修改住院预交
     * 
     * @param hisInpatientPrepayment 住院预交
     * @return 结果
     */
    public int updateHisInpatientPrepayment(HisInpatientPrepayment hisInpatientPrepayment);

    /**
     * 批量删除住院预交
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisInpatientPrepaymentByIds(String ids);

    /**
     * 删除住院预交信息
     * 
     * @param id 住院预交ID
     * @return 结果
     */
    public int deleteHisInpatientPrepaymentById(Long id);
}

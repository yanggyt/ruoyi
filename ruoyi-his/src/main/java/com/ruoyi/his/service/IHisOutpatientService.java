package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisOutpatient;

import java.util.List;

/**
 * 门诊病人Service接口
 * 
 * @author bend
 * @date 2020-07-08
 */
public interface IHisOutpatientService
{
    /**
     * 查询门诊病人
     * 
     * @param id 门诊病人ID
     * @return 门诊病人
     */
    public HisOutpatient selectHisOutpatientById(Long id);

    /**
     * 查询门诊病人
     *
     * @param hisOutpatient 门诊病人
     * @return 门诊病人
     */
    public HisOutpatient selectHisOutpatient(HisOutpatient hisOutpatient);

    /**
     * 查询门诊病人列表
     * 
     * @param hisOutpatient 门诊病人
     * @return 门诊病人集合
     */
    public List<HisOutpatient> selectHisOutpatientList(HisOutpatient hisOutpatient);

    /**
     * 新增门诊病人
     * 
     * @param hisOutpatient 门诊病人
     * @return 结果
     */
    public int insertHisOutpatient(HisOutpatient hisOutpatient);

    /**
     * 批量新增门诊病人
     *
     * @param hisOutpatientList 门诊病人列表
     * @return 结果
     */
    public int insertHisOutpatientBatch(List<HisOutpatient> hisOutpatientList);

    /**
     * 修改门诊病人
     * 
     * @param hisOutpatient 门诊病人
     * @return 结果
     */
    public int updateHisOutpatient(HisOutpatient hisOutpatient);

    /**
     * 批量删除门诊病人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisOutpatientByIds(String ids);

    /**
     * 删除门诊病人信息
     * 
     * @param id 门诊病人ID
     * @return 结果
     */
    public int deleteHisOutpatientById(Long id);

    /**
     * 批量更新
     * @param hisOutpatientList 病人列表
     */
    public void updateHisOutpatientBatch(List<HisOutpatient> hisOutpatientList);
}

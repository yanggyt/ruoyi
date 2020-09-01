package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisInpatient;

import java.util.List;

/**
 * 住院病人Service接口
 * 
 * @author bend
 * @date 2020-07-08
 */
public interface IHisInpatientService
{
    /**
     * 查询住院病人
     * 
     * @param id 住院病人ID
     * @return 住院病人
     */
    public HisInpatient selectHisInpatientById(Long id);

    /**
     * 查询住院病人
     *
     * @param hisInpatient 住院病人
     * @return 住院病人
     */
    public HisInpatient selectHisInpatient(HisInpatient hisInpatient);

    /**
     * 查询住院病人列表
     * 
     * @param hisInpatient 住院病人
     * @return 住院病人集合
     */
    public List<HisInpatient> selectHisInpatientList(HisInpatient hisInpatient);

    /**
     * 新增住院病人
     * 
     * @param hisInpatient 住院病人
     * @return 结果
     */
    public int insertHisInpatient(HisInpatient hisInpatient);

    /**
     * 批量新增住院病人
     *
     * @param hisInpatientList 住院病人
     * @return 结果
     */
    public int insertHisInpatientBatch(List<HisInpatient> hisInpatientList);

    /**
     * 修改住院病人
     * 
     * @param hisInpatient 住院病人
     * @return 结果
     */
    public int updateHisInpatient(HisInpatient hisInpatient);

    /**
     * 批量删除住院病人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisInpatientByIds(String ids);

    /**
     * 删除住院病人信息
     * 
     * @param id 住院病人ID
     * @return 结果
     */
    public int deleteHisInpatientById(Long id);

    /**
     *
      * @param hisInpatientList 住院病人列表
     */
    public void updateHisInpatientBatch(List<HisInpatient> hisInpatientList);
}

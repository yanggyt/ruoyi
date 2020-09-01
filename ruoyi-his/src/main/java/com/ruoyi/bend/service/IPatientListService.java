package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.PatientList;

/**
 * 就诊人列表Service接口
 * 
 * @author bend
 * @date 2020-09-01
 */
public interface IPatientListService 
{
    /**
     * 查询就诊人列表
     * 
     * @param id 就诊人列表ID
     * @return 就诊人列表
     */
    public PatientList selectPatientListById(Long id);

    /**
     * 查询就诊人列表列表
     * 
     * @param patientList 就诊人列表
     * @return 就诊人列表集合
     */
    public List<PatientList> selectPatientListList(PatientList patientList);

    /**
     * 新增就诊人列表
     * 
     * @param patientList 就诊人列表
     * @return 结果
     */
    public int insertPatientList(PatientList patientList);

    /**
     * 修改就诊人列表
     * 
     * @param patientList 就诊人列表
     * @return 结果
     */
    public int updatePatientList(PatientList patientList);

    /**
     * 批量删除就诊人列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePatientListByIds(String ids);

    /**
     * 删除就诊人列表信息
     * 
     * @param id 就诊人列表ID
     * @return 结果
     */
    public int deletePatientListById(Long id);
}

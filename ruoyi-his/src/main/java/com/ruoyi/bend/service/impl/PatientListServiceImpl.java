package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.PatientListMapper;
import com.ruoyi.bend.domain.PatientList;
import com.ruoyi.bend.service.IPatientListService;
import com.ruoyi.common.core.text.Convert;

/**
 * 就诊人列表Service业务层处理
 * 
 * @author bend
 * @date 2020-08-30
 */
@Service
public class PatientListServiceImpl implements IPatientListService 
{
    @Autowired
    private PatientListMapper patientListMapper;

    /**
     * 查询就诊人列表
     * 
     * @param id 就诊人列表ID
     * @return 就诊人列表
     */
    @Override
    public PatientList selectPatientListById(Long id)
    {
        return patientListMapper.selectPatientListById(id);
    }

    /**
     * 查询就诊人列表列表
     * 
     * @param patientList 就诊人列表
     * @return 就诊人列表
     */
    @Override
    public List<PatientList> selectPatientListList(PatientList patientList)
    {
        return patientListMapper.selectPatientListList(patientList);
    }

    /**
     * 新增就诊人列表
     * 
     * @param patientList 就诊人列表
     * @return 结果
     */
    @Override
    public int insertPatientList(PatientList patientList)
    {
        patientList.setCreateTime(DateUtils.getNowDate());
        return patientListMapper.insertPatientList(patientList);
    }

    /**
     * 修改就诊人列表
     * 
     * @param patientList 就诊人列表
     * @return 结果
     */
    @Override
    public int updatePatientList(PatientList patientList)
    {
        patientList.setUpdateTime(DateUtils.getNowDate());
        return patientListMapper.updatePatientList(patientList);
    }

    /**
     * 删除就诊人列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePatientListByIds(String ids)
    {
        return patientListMapper.deletePatientListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除就诊人列表信息
     * 
     * @param id 就诊人列表ID
     * @return 结果
     */
    @Override
    public int deletePatientListById(Long id)
    {
        return patientListMapper.deletePatientListById(id);
    }
}

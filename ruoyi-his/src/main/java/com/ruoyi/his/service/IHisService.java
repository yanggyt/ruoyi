package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisDoctor;
import com.ruoyi.his.domain.HisOutpatient;
import com.ruoyi.his.domain.HisRegistrationRecord;

import java.util.List;

public interface IHisService {


    /**
     * 同步机构挂号记录
     *
     * @param orgCode   机构编码
     * @param idCardNo  身份证号码,非必须参数！
     * @param beginTime 开始日期,格式:yyyy-MM-dd
     * @param endTime   结束日期,格式:yyyy-MM-dd
     * @return 如果提供idCardNo，则返回与之对应的列表，否则就返回该机构下所有挂号记录！
     */
    public List<HisRegistrationRecord> syncRegistrationRecord(String orgCode, String idCardNo, String beginTime, String endTime);


    /**
     * 实时同步门诊病人
     *
     * @param orgCode                机构编码
     * @param registrationRecordList 挂号记录
     * @param beginTime              开始时间,格式:yyyy-MM-dd HH:mm:ss
     * @param endTime                结束时间,格式:yyyy-MM-dd HH:mm:ss
     */
    public void syncOutpatient(String orgCode, List<HisRegistrationRecord> registrationRecordList, String beginTime, String endTime);

    /**
     * 实时同步门诊病人待缴费清单
     *
     * @param hisOutpatientList 门诊病人列表
     */
    public void syncPrePayOutpatientExpensesBill(List<HisOutpatient> hisOutpatientList);

    /**
     * 同步住院病人信息
     *
     * @param orgCode      机构编码
     * @param idCardNoList 身份证
     * @param beginTime    开始时间,格式:yyyy-MM-dd HH:mm:ss
     * @param endTime      结束时间,格式:yyyy-MM-dd HH:mm:ss
     */
    public void syncInpatient(String orgCode, List<String> idCardNoList, String beginTime, String endTime);

    /**
     * 同步门诊病人费用记录
     *
     * @param hisOutpatientList 门诊病人
     */
    public void syncOutpatientExpenses(List<HisOutpatient> hisOutpatientList);

    /**
     * 医生排班
     *
     * @param doctorList 医生列表
     */
    public void syncDoctorSchedule(List<HisDoctor> doctorList,String beginTime, String endTime);
}

package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.HisDoctorSchedule;

/**
 * 医生排班Service接口
 * 
 * @author bend
 * @date 2020-07-03
 */
public interface IHisDoctorScheduleService 
{
    /**
     * 查询医生排班
     * 
     * @param id 医生排班ID
     * @return 医生排班
     */
    public HisDoctorSchedule selectHisDoctorScheduleById(Long id);

    /**
     * 查询医生排班
     * @param hisDoctorSchedule 医生排班
     * @return one
     */
    public HisDoctorSchedule selectHisDoctorSchedule(HisDoctorSchedule hisDoctorSchedule);

    /**
     * 查询医生排班列表
     * 
     * @param hisDoctorSchedule 医生排班
     * @return 医生排班集合
     */
    public List<HisDoctorSchedule> selectHisDoctorScheduleList(HisDoctorSchedule hisDoctorSchedule);

    /**
     * 新增医生排班
     * 
     * @param hisDoctorSchedule 医生排班
     * @return 结果
     */
    public int insertHisDoctorSchedule(HisDoctorSchedule hisDoctorSchedule);

    /**
     * 新增医生排班
     * @param doctorScheduleList 医生排班列表
     * @return 结果
     */
    public int insertHisDoctorScheduleBatch(List<HisDoctorSchedule> doctorScheduleList);

    /**
     * 修改医生排班
     * 
     * @param hisDoctorSchedule 医生排班
     * @return 结果
     */
    public int updateHisDoctorSchedule(HisDoctorSchedule hisDoctorSchedule);

    /**
     * 批量删除医生排班
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisDoctorScheduleByIds(String ids);

    /**
     * 删除医生排班信息
     * 
     * @param id 医生排班ID
     * @return 结果
     */
    public int deleteHisDoctorScheduleById(Long id);

    /**
     *
     * @param hisDoctorScheduleList 排班列表
     */
    public void updateHisDoctorScheduleBatch(List<HisDoctorSchedule> hisDoctorScheduleList);
}

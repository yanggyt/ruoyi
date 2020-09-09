package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.func.Func;
import com.ruoyi.his.domain.HisDoctorSchedule;
import com.ruoyi.his.mapper.HisDoctorScheduleMapper;
import com.ruoyi.his.service.IHisDoctorScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医生排班Service业务层处理
 * 
 * @author bend
 * @date 2020-07-03
 */
@Service
public class HisDoctorScheduleServiceImpl implements IHisDoctorScheduleService 
{
    @Resource
    private HisDoctorScheduleMapper hisDoctorScheduleMapper;

    /**
     * 查询医生排班
     * 
     * @param id 医生排班ID
     * @return 医生排班
     */
    @Override
    public HisDoctorSchedule selectHisDoctorScheduleById(Long id)
    {
        return hisDoctorScheduleMapper.selectHisDoctorScheduleById(id);
    }

    @Override
    public HisDoctorSchedule selectHisDoctorSchedule(HisDoctorSchedule hisDoctorSchedule) {
        return hisDoctorScheduleMapper.selectHisDoctorSchedule(hisDoctorSchedule);
    }

    /**
     * 查询医生排班列表
     * 
     * @param hisDoctorSchedule 医生排班
     * @return 医生排班
     */
    @Override
    public List<HisDoctorSchedule> selectHisDoctorScheduleList(HisDoctorSchedule hisDoctorSchedule)
    {
        return hisDoctorScheduleMapper.selectHisDoctorScheduleList(hisDoctorSchedule);
    }

    /**
     * 新增医生排班
     * 
     * @param hisDoctorSchedule 医生排班
     * @return 结果
     */
    @Override
    public int insertHisDoctorSchedule(HisDoctorSchedule hisDoctorSchedule)
    {
        return hisDoctorScheduleMapper.insertHisDoctorSchedule(hisDoctorSchedule);
    }

    @Override
    public int insertHisDoctorScheduleBatch(List<HisDoctorSchedule> doctorScheduleList)
    {
        return hisDoctorScheduleMapper.insertHisDoctorScheduleBatch(doctorScheduleList);
    }

    /**
     * 修改医生排班
     * 
     * @param hisDoctorSchedule 医生排班
     * @return 结果
     */
    @Override
    public int updateHisDoctorSchedule(HisDoctorSchedule hisDoctorSchedule)
    {
        return hisDoctorScheduleMapper.updateHisDoctorSchedule(hisDoctorSchedule);
    }

    /**
     * 删除医生排班对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisDoctorScheduleByIds(String ids)
    {
        return hisDoctorScheduleMapper.deleteHisDoctorScheduleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除医生排班信息
     * 
     * @param id 医生排班ID
     * @return 结果
     */
    @Override
    public int deleteHisDoctorScheduleById(Long id)
    {
        return hisDoctorScheduleMapper.deleteHisDoctorScheduleById(id);
    }

    /**
     *
     * @param hisDoctorScheduleList 排班列表
     */
    @Override
    public void updateHisDoctorScheduleBatch(List<HisDoctorSchedule> hisDoctorScheduleList)
    {
        if (Func.isNotEmpty(hisDoctorScheduleList)) {
            hisDoctorScheduleList.forEach(hisDoctorSchedule -> {
                hisDoctorScheduleMapper.updateByPrimaryKeySelective(hisDoctorSchedule);
            });
        }
    }
}

package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.his.domain.HisDoctorDepartment;
import com.ruoyi.his.mapper.HisDoctorDepartmentMapper;
import com.ruoyi.his.service.IHisDoctorDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医生科室关系Service业务层处理
 * 
 * @author bend
 * @date 2020-07-01
 */
@Service
public class HisDoctorDepartmentServiceImpl implements IHisDoctorDepartmentService 
{
    @Resource
    private HisDoctorDepartmentMapper hisDoctorDepartmentMapper;

    /**
     * 查询医生科室关系
     * 
     * @param id 医生科室关系ID
     * @return 医生科室关系
     */
    @Override
    public HisDoctorDepartment selectHisDoctorDepartmentById(Long id)
    {
        return hisDoctorDepartmentMapper.selectHisDoctorDepartmentById(id);
    }

    /**
     * 查询医生科室关系列表
     * 
     * @param hisDoctorDepartment 医生科室关系
     * @return 医生科室关系
     */
    @Override
    public List<HisDoctorDepartment> selectHisDoctorDepartmentList(HisDoctorDepartment hisDoctorDepartment)
    {
        return hisDoctorDepartmentMapper.selectHisDoctorDepartmentList(hisDoctorDepartment);
    }

    /**
     * 新增医生科室关系
     * 
     * @param hisDoctorDepartment 医生科室关系
     * @return 结果
     */
    @Override
    public int insertHisDoctorDepartment(HisDoctorDepartment hisDoctorDepartment)
    {
        return hisDoctorDepartmentMapper.insertHisDoctorDepartment(hisDoctorDepartment);
    }

    /**
     * 修改医生科室关系
     * 
     * @param hisDoctorDepartment 医生科室关系
     * @return 结果
     */
    @Override
    public int updateHisDoctorDepartment(HisDoctorDepartment hisDoctorDepartment)
    {
        return hisDoctorDepartmentMapper.updateHisDoctorDepartment(hisDoctorDepartment);
    }

    /**
     * 修改医生科室关系
     *
     * @param hisDoctorDepartment 医生科室关系
     * @return 结果
     */
    @Override
    public int updateHisDoctorDepartmentByDeptId(HisDoctorDepartment hisDoctorDepartment)
    {
        return hisDoctorDepartmentMapper.updateHisDoctorDepartmentByDeptId(hisDoctorDepartment);
    }

    /**
     * 删除医生科室关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisDoctorDepartmentByIds(String ids)
    {
        return hisDoctorDepartmentMapper.deleteHisDoctorDepartmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除医生科室关系信息
     * 
     * @param id 医生科室关系ID
     * @return 结果
     */
    @Override
    public int deleteHisDoctorDepartmentById(Long id)
    {
        return hisDoctorDepartmentMapper.deleteHisDoctorDepartmentById(id);
    }
}

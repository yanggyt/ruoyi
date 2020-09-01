package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.HisDoctorDepartment;

/**
 * 医生科室关系Service接口
 * 
 * @author bend
 * @date 2020-07-01
 */
public interface IHisDoctorDepartmentService 
{
    /**
     * 查询医生科室关系
     * 
     * @param id 医生科室关系ID
     * @return 医生科室关系
     */
    public HisDoctorDepartment selectHisDoctorDepartmentById(Long id);

    /**
     * 查询医生科室关系列表
     * 
     * @param hisDoctorDepartment 医生科室关系
     * @return 医生科室关系集合
     */
    public List<HisDoctorDepartment> selectHisDoctorDepartmentList(HisDoctorDepartment hisDoctorDepartment);

    /**
     * 新增医生科室关系
     * 
     * @param hisDoctorDepartment 医生科室关系
     * @return 结果
     */
    public int insertHisDoctorDepartment(HisDoctorDepartment hisDoctorDepartment);

    /**
     * 修改医生科室关系
     * 
     * @param hisDoctorDepartment 医生科室关系
     * @return 结果
     */
    public int updateHisDoctorDepartment(HisDoctorDepartment hisDoctorDepartment);

    /**
     * 修改医生科室关系
     *
     * @param hisDoctorDepartment 医生科室关系
     * @return 结果
     */
    public int updateHisDoctorDepartmentByDeptId(HisDoctorDepartment hisDoctorDepartment);
    /**
     * 批量删除医生科室关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisDoctorDepartmentByIds(String ids);

    /**
     * 删除医生科室关系信息
     * 
     * @param id 医生科室关系ID
     * @return 结果
     */
    public int deleteHisDoctorDepartmentById(Long id);
}

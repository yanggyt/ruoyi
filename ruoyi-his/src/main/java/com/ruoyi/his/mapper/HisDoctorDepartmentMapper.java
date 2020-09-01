package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisDoctorDepartment;

import java.util.List;

/**
 * 医生科室关系Mapper接口
 * 
 * @author bend
 * @date 2020-07-01
 */
public interface HisDoctorDepartmentMapper extends RuoYiBaseMapper<HisDoctorDepartment>
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

    public int updateHisDoctorDepartmentByDeptId(HisDoctorDepartment hisDoctorDepartment);

    /**
     * 删除医生科室关系
     * 
     * @param id 医生科室关系ID
     * @return 结果
     */
    public int deleteHisDoctorDepartmentById(Long id);

    /**
     * 批量删除医生科室关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisDoctorDepartmentByIds(String[] ids);
}

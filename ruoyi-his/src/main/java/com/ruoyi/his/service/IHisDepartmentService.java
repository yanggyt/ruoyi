package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.HisDepartment;

/**
 * 科室Service接口
 * 
 * @author bend
 * @date 2020-07-01
 */
public interface IHisDepartmentService 
{
    /**
     * 查询科室
     * 
     * @param id 科室ID
     * @return 科室
     */
    public HisDepartment selectHisDepartmentById(Long id);

    /**
     * 查询科室
     *
     * @param deptId 科室ID
     * @return 科室
     */
    public HisDepartment selectHisDepartmentByDeptId(String deptId);

    /**
     * 查询科室列表
     * 
     * @param hisDepartment 科室
     * @return 科室集合
     */
    public List<HisDepartment> selectHisDepartmentList(HisDepartment hisDepartment);

    /**
     * 新增科室
     * 
     * @param hisDepartment 科室
     * @return 结果
     */
    public int insertHisDepartment(HisDepartment hisDepartment);

    /**
     * 修改科室
     * 
     * @param hisDepartment 科室
     * @return 结果
     */
    public int updateHisDepartment(HisDepartment hisDepartment);

    /**
     * 批量删除科室
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisDepartmentByIds(String ids);

    /**
     * 删除科室信息
     * 
     * @param id 科室ID
     * @return 结果
     */
    public int deleteHisDepartmentById(Long id);
}

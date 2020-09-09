package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.his.domain.HisDepartment;
import com.ruoyi.his.mapper.HisDepartmentMapper;
import com.ruoyi.his.service.IHisDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 科室Service业务层处理
 * 
 * @author bend
 * @date 2020-07-01
 */
@Service
public class HisDepartmentServiceImpl implements IHisDepartmentService 
{
    @Resource
    private HisDepartmentMapper hisDepartmentMapper;

    /**
     * 查询科室
     * 
     * @param id 科室ID
     * @return 科室
     */
    @Override
    public HisDepartment selectHisDepartmentById(Long id)
    {
        return hisDepartmentMapper.selectHisDepartmentById(id);
    }

    /**
     * 查询科室
     *
     * @param deptId 科室ID
     * @return 科室
     */
    @Override
    public HisDepartment selectHisDepartmentByDeptId(String deptId)
    {
        return hisDepartmentMapper.selectHisDepartmentByDeptId(deptId);
    }

    /**
     * 查询科室列表
     * 
     * @param hisDepartment 科室
     * @return 科室
     */
    @Override
    public List<HisDepartment> selectHisDepartmentList(HisDepartment hisDepartment)
    {
        return hisDepartmentMapper.selectHisDepartmentList(hisDepartment);
    }

    /**
     * 新增科室
     * 
     * @param hisDepartment 科室
     * @return 结果
     */
    @Override
    public int insertHisDepartment(HisDepartment hisDepartment)
    {
        return hisDepartmentMapper.insertHisDepartment(hisDepartment);
    }

    /**
     * 修改科室
     * 
     * @param hisDepartment 科室
     * @return 结果
     */
    @Override
    public int updateHisDepartment(HisDepartment hisDepartment)
    {
        return hisDepartmentMapper.updateHisDepartment(hisDepartment);
    }

    /**
     * 删除科室对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisDepartmentByIds(String ids)
    {
        return hisDepartmentMapper.deleteHisDepartmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除科室信息
     * 
     * @param id 科室ID
     * @return 结果
     */
    @Override
    public int deleteHisDepartmentById(Long id)
    {
        return hisDepartmentMapper.deleteHisDepartmentById(id);
    }
}

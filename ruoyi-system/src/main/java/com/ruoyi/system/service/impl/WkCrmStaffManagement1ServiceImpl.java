package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmStaffManagement1Mapper;
import com.ruoyi.system.domain.WkCrmStaffManagement1;
import com.ruoyi.system.service.IWkCrmStaffManagement1Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 员工管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmStaffManagement1ServiceImpl implements IWkCrmStaffManagement1Service 
{
    @Autowired
    private WkCrmStaffManagement1Mapper wkCrmStaffManagement1Mapper;

    /**
     * 查询员工管理
     * 
     * @param id 员工管理ID
     * @return 员工管理
     */
    @Override
    public WkCrmStaffManagement1 selectWkCrmStaffManagement1ById(Long id)
    {
        return wkCrmStaffManagement1Mapper.selectWkCrmStaffManagement1ById(id);
    }

    /**
     * 查询员工管理列表
     * 
     * @param wkCrmStaffManagement1 员工管理
     * @return 员工管理
     */
    @Override
    public List<WkCrmStaffManagement1> selectWkCrmStaffManagement1List(WkCrmStaffManagement1 wkCrmStaffManagement1)
    {
        return wkCrmStaffManagement1Mapper.selectWkCrmStaffManagement1List(wkCrmStaffManagement1);
    }

    /**
     * 新增员工管理
     * 
     * @param wkCrmStaffManagement1 员工管理
     * @return 结果
     */
    @Override
    public int insertWkCrmStaffManagement1(WkCrmStaffManagement1 wkCrmStaffManagement1)
    {
        return wkCrmStaffManagement1Mapper.insertWkCrmStaffManagement1(wkCrmStaffManagement1);
    }

    /**
     * 修改员工管理
     * 
     * @param wkCrmStaffManagement1 员工管理
     * @return 结果
     */
    @Override
    public int updateWkCrmStaffManagement1(WkCrmStaffManagement1 wkCrmStaffManagement1)
    {
        return wkCrmStaffManagement1Mapper.updateWkCrmStaffManagement1(wkCrmStaffManagement1);
    }

    /**
     * 删除员工管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmStaffManagement1ByIds(String ids)
    {
        return wkCrmStaffManagement1Mapper.deleteWkCrmStaffManagement1ByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除员工管理信息
     * 
     * @param id 员工管理ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmStaffManagement1ById(Long id)
    {
        return wkCrmStaffManagement1Mapper.deleteWkCrmStaffManagement1ById(id);
    }
}

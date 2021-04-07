package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WkCrmStaffManagement1;

/**
 * 员工管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public interface WkCrmStaffManagement1Mapper 
{
    /**
     * 查询员工管理
     * 
     * @param id 员工管理ID
     * @return 员工管理
     */
    public WkCrmStaffManagement1 selectWkCrmStaffManagement1ById(Long id);

    /**
     * 查询员工管理列表
     * 
     * @param wkCrmStaffManagement1 员工管理
     * @return 员工管理集合
     */
    public List<WkCrmStaffManagement1> selectWkCrmStaffManagement1List(WkCrmStaffManagement1 wkCrmStaffManagement1);

    /**
     * 新增员工管理
     * 
     * @param wkCrmStaffManagement1 员工管理
     * @return 结果
     */
    public int insertWkCrmStaffManagement1(WkCrmStaffManagement1 wkCrmStaffManagement1);

    /**
     * 修改员工管理
     * 
     * @param wkCrmStaffManagement1 员工管理
     * @return 结果
     */
    public int updateWkCrmStaffManagement1(WkCrmStaffManagement1 wkCrmStaffManagement1);

    /**
     * 删除员工管理
     * 
     * @param id 员工管理ID
     * @return 结果
     */
    public int deleteWkCrmStaffManagement1ById(Long id);

    /**
     * 批量删除员工管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWkCrmStaffManagement1ByIds(String[] ids);
}

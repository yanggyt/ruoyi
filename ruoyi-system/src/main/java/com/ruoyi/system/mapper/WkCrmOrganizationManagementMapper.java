package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WkCrmOrganizationManagement;

/**
 * 组织管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public interface WkCrmOrganizationManagementMapper 
{
    /**
     * 查询组织管理
     * 
     * @param id 组织管理ID
     * @return 组织管理
     */
    public WkCrmOrganizationManagement selectWkCrmOrganizationManagementById(Long id);

    /**
     * 查询组织管理列表
     * 
     * @param wkCrmOrganizationManagement 组织管理
     * @return 组织管理集合
     */
    public List<WkCrmOrganizationManagement> selectWkCrmOrganizationManagementList(WkCrmOrganizationManagement wkCrmOrganizationManagement);

    /**
     * 新增组织管理
     * 
     * @param wkCrmOrganizationManagement 组织管理
     * @return 结果
     */
    public int insertWkCrmOrganizationManagement(WkCrmOrganizationManagement wkCrmOrganizationManagement);

    /**
     * 修改组织管理
     * 
     * @param wkCrmOrganizationManagement 组织管理
     * @return 结果
     */
    public int updateWkCrmOrganizationManagement(WkCrmOrganizationManagement wkCrmOrganizationManagement);

    /**
     * 删除组织管理
     * 
     * @param id 组织管理ID
     * @return 结果
     */
    public int deleteWkCrmOrganizationManagementById(Long id);

    /**
     * 批量删除组织管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWkCrmOrganizationManagementByIds(String[] ids);
}

package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.ServiceOrganization;

/**
 * 服务组织Service接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface IServiceOrganizationService 
{
    /**
     * 查询服务组织
     * 
     * @param id 服务组织ID
     * @return 服务组织
     */
    public ServiceOrganization selectServiceOrganizationById(Long id);

    /**
     * 查询服务组织列表
     * 
     * @param serviceOrganization 服务组织
     * @return 服务组织集合
     */
    public List<ServiceOrganization> selectServiceOrganizationList(ServiceOrganization serviceOrganization);

    /**
     * 新增服务组织
     * 
     * @param serviceOrganization 服务组织
     * @return 结果
     */
    public int insertServiceOrganization(ServiceOrganization serviceOrganization);

    /**
     * 修改服务组织
     * 
     * @param serviceOrganization 服务组织
     * @return 结果
     */
    public int updateServiceOrganization(ServiceOrganization serviceOrganization);

    /**
     * 批量删除服务组织
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteServiceOrganizationByIds(String ids);

    /**
     * 删除服务组织信息
     * 
     * @param id 服务组织ID
     * @return 结果
     */
    public int deleteServiceOrganizationById(Long id);

    /**
     * 审核服务组织对象
     * @param ids 服务组织IDs
     * @param auditStatus 审核状态
     * @param remark 审核备注
     * @return
     */
    public int audit(String ids, String auditStatus, String remark);

    /**
     * 停用或者启用服务组织对象
     * @param ids 服务组织IDs
     * @param status 状态
     * @return
     */
    public int updateStatus(String ids, String status);
}

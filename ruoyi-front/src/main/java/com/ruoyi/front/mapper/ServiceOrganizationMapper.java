package com.ruoyi.front.mapper;

import java.util.List;
import com.ruoyi.front.domain.ServiceOrganization;
import org.apache.ibatis.annotations.Param;

/**
 * 服务组织Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface ServiceOrganizationMapper 
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
     * 删除服务组织
     * 
     * @param id 服务组织ID
     * @return 结果
     */
    public int deleteServiceOrganizationById(Long id);

    /**
     * 批量删除服务组织
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteServiceOrganizationByIds(String[] ids);

    /**
     * 审核组织
     * @param ids
     * @param auditStatus
     * @param remark
     * @return
     */
    public int auditServiceOrganization(@Param("ids") String[] ids, @Param("auditStatus")String auditStatus, @Param("remark")String remark, @Param("checkBy")String checkBy);
}

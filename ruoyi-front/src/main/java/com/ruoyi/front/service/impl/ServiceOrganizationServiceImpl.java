package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.ServiceOrganizationMapper;
import com.ruoyi.front.domain.ServiceOrganization;
import com.ruoyi.front.service.IServiceOrganizationService;
import com.ruoyi.common.core.text.Convert;

/**
 * 服务组织Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class ServiceOrganizationServiceImpl implements IServiceOrganizationService 
{
    @Autowired
    private ServiceOrganizationMapper serviceOrganizationMapper;

    /**
     * 查询服务组织
     * 
     * @param id 服务组织ID
     * @return 服务组织
     */
    @Override
    public ServiceOrganization selectServiceOrganizationById(Long id)
    {
        return serviceOrganizationMapper.selectServiceOrganizationById(id);
    }

    /**
     * 查询服务组织列表
     * 
     * @param serviceOrganization 服务组织
     * @return 服务组织
     */
    @Override
    public List<ServiceOrganization> selectServiceOrganizationList(ServiceOrganization serviceOrganization)
    {
        return serviceOrganizationMapper.selectServiceOrganizationList(serviceOrganization);
    }

    /**
     * 新增服务组织
     * 
     * @param serviceOrganization 服务组织
     * @return 结果
     */
    @Override
    public int insertServiceOrganization(ServiceOrganization serviceOrganization)
    {
        serviceOrganization.setCreateTime(DateUtils.getNowDate());
        return serviceOrganizationMapper.insertServiceOrganization(serviceOrganization);
    }

    /**
     * 修改服务组织
     * 
     * @param serviceOrganization 服务组织
     * @return 结果
     */
    @Override
    public int updateServiceOrganization(ServiceOrganization serviceOrganization)
    {
        serviceOrganization.setUpdateTime(DateUtils.getNowDate());
        return serviceOrganizationMapper.updateServiceOrganization(serviceOrganization);
    }

    /**
     * 删除服务组织对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteServiceOrganizationByIds(String ids)
    {
        return serviceOrganizationMapper.deleteServiceOrganizationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除服务组织信息
     * 
     * @param id 服务组织ID
     * @return 结果
     */
    @Override
    public int deleteServiceOrganizationById(Long id)
    {
        return serviceOrganizationMapper.deleteServiceOrganizationById(id);
    }

    /**
     * 审核服务组织对象
     * @param ids 服务组织IDs
     * @param auditStatus 审核状态
     * @param remark 审核备注
     * @return
     */
    @Override
    public int audit(String ids, String auditStatus, String remark)
    {
        SysUser user = ShiroUtils.getSysUser();
        return serviceOrganizationMapper.auditServiceOrganization(Convert.toStrArray(ids), auditStatus, remark, user.getUserId().toString());
    }
}

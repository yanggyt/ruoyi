package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmOrganizationManagementMapper;
import com.ruoyi.system.domain.WkCrmOrganizationManagement;
import com.ruoyi.system.service.IWkCrmOrganizationManagementService;
import com.ruoyi.common.core.text.Convert;

/**
 * 组织管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmOrganizationManagementServiceImpl implements IWkCrmOrganizationManagementService 
{
    @Autowired
    private WkCrmOrganizationManagementMapper wkCrmOrganizationManagementMapper;

    /**
     * 查询组织管理
     * 
     * @param id 组织管理ID
     * @return 组织管理
     */
    @Override
    public WkCrmOrganizationManagement selectWkCrmOrganizationManagementById(Long id)
    {
        return wkCrmOrganizationManagementMapper.selectWkCrmOrganizationManagementById(id);
    }

    /**
     * 查询组织管理列表
     * 
     * @param wkCrmOrganizationManagement 组织管理
     * @return 组织管理
     */
    @Override
    public List<WkCrmOrganizationManagement> selectWkCrmOrganizationManagementList(WkCrmOrganizationManagement wkCrmOrganizationManagement)
    {
        return wkCrmOrganizationManagementMapper.selectWkCrmOrganizationManagementList(wkCrmOrganizationManagement);
    }

    /**
     * 新增组织管理
     * 
     * @param wkCrmOrganizationManagement 组织管理
     * @return 结果
     */
    @Override
    public int insertWkCrmOrganizationManagement(WkCrmOrganizationManagement wkCrmOrganizationManagement)
    {
        return wkCrmOrganizationManagementMapper.insertWkCrmOrganizationManagement(wkCrmOrganizationManagement);
    }

    /**
     * 修改组织管理
     * 
     * @param wkCrmOrganizationManagement 组织管理
     * @return 结果
     */
    @Override
    public int updateWkCrmOrganizationManagement(WkCrmOrganizationManagement wkCrmOrganizationManagement)
    {
        return wkCrmOrganizationManagementMapper.updateWkCrmOrganizationManagement(wkCrmOrganizationManagement);
    }

    /**
     * 删除组织管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmOrganizationManagementByIds(String ids)
    {
        return wkCrmOrganizationManagementMapper.deleteWkCrmOrganizationManagementByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除组织管理信息
     * 
     * @param id 组织管理ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmOrganizationManagementById(Long id)
    {
        return wkCrmOrganizationManagementMapper.deleteWkCrmOrganizationManagementById(id);
    }
}

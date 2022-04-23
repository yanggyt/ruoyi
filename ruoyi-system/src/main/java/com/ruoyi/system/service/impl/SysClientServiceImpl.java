package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysClientMapper;
import com.ruoyi.system.domain.SysClient;
import com.ruoyi.system.service.ISysClientService;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-23
 */
@Service
public class SysClientServiceImpl implements ISysClientService 
{
    @Autowired
    private SysClientMapper sysClientMapper;

    /**
     * 查询客户信息
     * 
     * @param clientId 客户信息主键
     * @return 客户信息
     */
    @Override
    public SysClient selectSysClientByClientId(Long clientId)
    {
        return sysClientMapper.selectSysClientByClientId(clientId);
    }

    /**
     * 查询客户信息列表
     * 
     * @param sysClient 客户信息
     * @return 客户信息
     */
    @Override
    public List<SysClient> selectSysClientList(SysClient sysClient)
    {
        return sysClientMapper.selectSysClientList(sysClient);
    }

    /**
     * 新增客户信息
     * 
     * @param sysClient 客户信息
     * @return 结果
     */
    @Override
    public int insertSysClient(SysClient sysClient)
    {
        sysClient.setCreateTime(DateUtils.getNowDate());
        return sysClientMapper.insertSysClient(sysClient);
    }

    /**
     * 修改客户信息
     * 
     * @param sysClient 客户信息
     * @return 结果
     */
    @Override
    public int updateSysClient(SysClient sysClient)
    {
        sysClient.setUpdateTime(DateUtils.getNowDate());
        return sysClientMapper.updateSysClient(sysClient);
    }

    /**
     * 批量删除客户信息
     * 
     * @param clientIds 需要删除的客户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysClientByClientIds(String clientIds)
    {
        return sysClientMapper.deleteSysClientByClientIds(Convert.toStrArray(clientIds));
    }

    /**
     * 删除客户信息信息
     * 
     * @param clientId 客户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysClientByClientId(Long clientId)
    {
        return sysClientMapper.deleteSysClientByClientId(clientId);
    }
}

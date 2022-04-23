package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysClient;

/**
 * 客户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-23
 */
public interface SysClientMapper 
{
    /**
     * 查询客户信息
     * 
     * @param clientId 客户信息主键
     * @return 客户信息
     */
    public SysClient selectSysClientByClientId(Long clientId);

    /**
     * 查询客户信息列表
     * 
     * @param sysClient 客户信息
     * @return 客户信息集合
     */
    public List<SysClient> selectSysClientList(SysClient sysClient);

    /**
     * 新增客户信息
     * 
     * @param sysClient 客户信息
     * @return 结果
     */
    public int insertSysClient(SysClient sysClient);

    /**
     * 修改客户信息
     * 
     * @param sysClient 客户信息
     * @return 结果
     */
    public int updateSysClient(SysClient sysClient);

    /**
     * 删除客户信息
     * 
     * @param clientId 客户信息主键
     * @return 结果
     */
    public int deleteSysClientByClientId(Long clientId);

    /**
     * 批量删除客户信息
     * 
     * @param clientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysClientByClientIds(String[] clientIds);
}

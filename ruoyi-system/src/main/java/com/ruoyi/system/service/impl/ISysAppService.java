package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysApp;

import java.util.List;

/**
 * 编码申请Service接口
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
public interface ISysAppService 
{
    /**
     * 查询编码申请
     * 
     * @param appId 编码申请主键
     * @return 编码申请
     */
    public SysApp selectSysAppByAppId(Long appId);

    /**
     * 查询编码申请列表
     * 
     * @param sysApp 编码申请
     * @return 编码申请集合
     */
    public List<SysApp> selectSysAppList(SysApp sysApp);

    /**
     * 新增编码申请
     * 
     * @param sysApp 编码申请
     * @return 结果
     */
    public int insertSysApp(SysApp sysApp);

    /**
     * 修改编码申请
     * 
     * @param sysApp 编码申请
     * @return 结果
     */
    public int updateSysApp(SysApp sysApp);

    /**
     * 批量删除编码申请
     * 
     * @param appIds 需要删除的编码申请主键集合
     * @return 结果
     */
    public int deleteSysAppByAppIds(String appIds);

    /**
     * 删除编码申请信息
     * 
     * @param appId 编码申请主键
     * @return 结果
     */
    public int deleteSysAppByAppId(Long appId);
    /**
     * 根据所有申请单
     *
     * @return 申请单集合信息
     */
    public List<SysApp> selectAppAll();
}

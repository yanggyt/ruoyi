package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysApp;

import java.util.List;

/**
 * 编码申请Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
public interface SysAppMapper 
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
     * 删除编码申请
     * 
     * @param appId 编码申请主键
     * @return 结果
     */
    public int deleteSysAppByAppId(Long appId);

    /**
     * 批量删除编码申请
     * 
     * @param appIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAppByAppIds(String[] appIds);
    /**
     * 查询所有申请单
     *
     * @return 结果
     */
    List<SysApp> selectAppAll();
}

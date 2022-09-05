package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysApp;
import com.ruoyi.system.mapper.SysAppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 编码申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
@Service
public class SysAppServiceImpl implements ISysAppService 
{
    @Autowired
    private SysAppMapper sysAppMapper;

    /**
     * 查询编码申请
     * 
     * @param appId 编码申请主键
     * @return 编码申请
     */
    @Override
    public SysApp selectSysAppByAppId(Long appId)
    {
        return sysAppMapper.selectSysAppByAppId(appId);
    }

    /**
     * 查询编码申请列表
     * 
     * @param sysApp 编码申请
     * @return 编码申请
     */
    @Override
    public List<SysApp> selectSysAppList(SysApp sysApp)
    {
        return sysAppMapper.selectSysAppList(sysApp);
    }

    /**
     * 新增编码申请
     * 
     * @param sysApp 编码申请
     * @return 结果
     */
    @Override
    public int insertSysApp(SysApp sysApp)
    {
        sysApp.setCreateTime(DateUtils.getNowDate());
        return sysAppMapper.insertSysApp(sysApp);
    }

    /**
     * 修改编码申请
     * 
     * @param sysApp 编码申请
     * @return 结果
     */
    @Override
    public int updateSysApp(SysApp sysApp)
    {
        sysApp.setUpdateTime(DateUtils.getNowDate());
        return sysAppMapper.updateSysApp(sysApp);
    }

    /**
     * 批量删除编码申请
     * 
     * @param appIds 需要删除的编码申请主键
     * @return 结果
     */
    @Override
    public int deleteSysAppByAppIds(String appIds)
    {
        return sysAppMapper.deleteSysAppByAppIds(Convert.toStrArray(appIds));
    }

    /**
     * 删除编码申请信息
     * 
     * @param appId 编码申请主键
     * @return 结果
     */
    @Override
    public int deleteSysAppByAppId(Long appId)
    {
        return sysAppMapper.deleteSysAppByAppId(appId);
    }

    @Override
    public List<SysApp> selectAppAll() {
        return sysAppMapper.selectAppAll();
    }

}

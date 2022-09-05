package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysAppData;
import com.ruoyi.system.mapper.SysAppDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 编码申请数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
@Service
public class SysAppDataServiceImpl implements ISysAppDataService 
{
    @Autowired
    private SysAppDataMapper sysAppDataMapper;

    /**
     * 查询编码申请数据
     * 
     * @param appDataId 编码申请数据主键
     * @return 编码申请数据
     */
    @Override
    public SysAppData selectSysAppDataByAppDataId(Long appDataId)
    {
        return sysAppDataMapper.selectSysAppDataByAppDataId(appDataId);
    }

    /**
     * 查询编码申请数据列表
     * 
     * @param sysAppData 编码申请数据
     * @return 编码申请数据
     */
    @Override
    public List<SysAppData> selectSysAppDataList(SysAppData sysAppData)
    {
        return sysAppDataMapper.selectSysAppDataList(sysAppData);
    }

    /**
     * 新增编码申请数据
     * 
     * @param sysAppData 编码申请数据
     * @return 结果
     */
    @Override
    public int insertSysAppData(SysAppData sysAppData)
    {
        sysAppData.setCreateTime(DateUtils.getNowDate());
        return sysAppDataMapper.insertSysAppData(sysAppData);
    }

    /**
     * 修改编码申请数据
     * 
     * @param sysAppData 编码申请数据
     * @return 结果
     */
    @Override
    public int updateSysAppData(SysAppData sysAppData)
    {
        sysAppData.setUpdateTime(DateUtils.getNowDate());
        return sysAppDataMapper.updateSysAppData(sysAppData);
    }

    /**
     * 批量删除编码申请数据
     * 
     * @param appDataIds 需要删除的编码申请数据主键
     * @return 结果
     */
    @Override
    public int deleteSysAppDataByAppDataIds(String appDataIds)
    {
        return sysAppDataMapper.deleteSysAppDataByAppDataIds(Convert.toStrArray(appDataIds));
    }

    /**
     * 删除编码申请数据信息
     * 
     * @param appDataId 编码申请数据主键
     * @return 结果
     */
    @Override
    public int deleteSysAppDataByAppDataId(Long appDataId)
    {
        return sysAppDataMapper.deleteSysAppDataByAppDataId(appDataId);
    }
}

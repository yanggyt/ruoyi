package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysAppData;

import java.util.List;

/**
 * 编码申请数据Service接口
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
public interface ISysAppDataService 
{
    /**
     * 查询编码申请数据
     * 
     * @param appDataId 编码申请数据主键
     * @return 编码申请数据
     */
    public SysAppData selectSysAppDataByAppDataId(Long appDataId);

    /**
     * 查询编码申请数据列表
     * 
     * @param sysAppData 编码申请数据
     * @return 编码申请数据集合
     */
    public List<SysAppData> selectSysAppDataList(SysAppData sysAppData);

    /**
     * 新增编码申请数据
     * 
     * @param sysAppData 编码申请数据
     * @return 结果
     */
    public int insertSysAppData(SysAppData sysAppData);

    /**
     * 修改编码申请数据
     * 
     * @param sysAppData 编码申请数据
     * @return 结果
     */
    public int updateSysAppData(SysAppData sysAppData);

    /**
     * 批量删除编码申请数据
     * 
     * @param appDataIds 需要删除的编码申请数据主键集合
     * @return 结果
     */
    public int deleteSysAppDataByAppDataIds(String appDataIds);

    /**
     * 删除编码申请数据信息
     * 
     * @param appDataId 编码申请数据主键
     * @return 结果
     */
    public int deleteSysAppDataByAppDataId(Long appDataId);

   
}

package com.ruoyi.test.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.test.domain.SysFileInfo;
import com.ruoyi.test.mapper.SysFileInfoMapper;
import com.ruoyi.test.service.ISysFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件信息Service业务层处理
 * 
 * @author box
 * @date 2021-05-06
 */
@Service
public class SysFileInfoServiceImpl implements ISysFileInfoService
{
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;

    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息ID
     * @return 文件信息
     */
    @Override
    public SysFileInfo selectSysFileInfoById(Long fileId)
    {
        return sysFileInfoMapper.selectSysFileInfoById(fileId);
    }

    /**
     * 查询文件信息列表
     * 
     * @param sysFileInfo 文件信息
     * @return 文件信息
     */
    @Override
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.selectSysFileInfoList(sysFileInfo);
    }

    /**
     * 新增文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int insertSysFileInfo(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
    }

    /**
     * 修改文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int updateSysFileInfo(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.updateSysFileInfo(sysFileInfo);
    }

    /**
     * 删除文件信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByIds(String ids)
    {
        return sysFileInfoMapper.deleteSysFileInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息ID
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoById(Long fileId)
    {
        return sysFileInfoMapper.deleteSysFileInfoById(fileId);
    }
}

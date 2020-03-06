package cn.com.infosouth.arj21.service.impl;

import java.util.List;

import cn.com.infosouth.arj21.domain.InfoImexportLog;
import cn.com.infosouth.arj21.mapper.InfoImexportLogMapper;
import cn.com.infosouth.arj21.service.IInfoImexportLogService;
import cn.com.infosouth.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 导入导出日志Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Service
public class InfoImexportLogServiceImpl implements IInfoImexportLogService
{
    @Autowired
    private InfoImexportLogMapper infoImexportLogMapper;

    /**
     * 查询导入导出日志
     * 
     * @param id 导入导出日志ID
     * @return 导入导出日志
     */
    @Override
    public InfoImexportLog selectInfoImexportLogById(String id)
    {
        return infoImexportLogMapper.selectInfoImexportLogById(id);
    }

    /**
     * 查询导入导出日志列表
     * 
     * @param infoImexportLog 导入导出日志
     * @return 导入导出日志
     */
    @Override
    public List<InfoImexportLog> selectInfoImexportLogList(InfoImexportLog infoImexportLog)
    {
        return infoImexportLogMapper.selectInfoImexportLogList(infoImexportLog);
    }

    /**
     * 新增导入导出日志
     * 
     * @param infoImexportLog 导入导出日志
     * @return 结果
     */
    @Override
    public int insertInfoImexportLog(InfoImexportLog infoImexportLog)
    {
        return infoImexportLogMapper.insertInfoImexportLog(infoImexportLog);
    }

    /**
     * 修改导入导出日志
     * 
     * @param infoImexportLog 导入导出日志
     * @return 结果
     */
    @Override
    public int updateInfoImexportLog(InfoImexportLog infoImexportLog)
    {
        return infoImexportLogMapper.updateInfoImexportLog(infoImexportLog);
    }

    /**
     * 删除导入导出日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoImexportLogByIds(String ids)
    {
        return infoImexportLogMapper.deleteInfoImexportLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除导入导出日志信息
     * 
     * @param id 导入导出日志ID
     * @return 结果
     */
    @Override
    public int deleteInfoImexportLogById(String id)
    {
        return infoImexportLogMapper.deleteInfoImexportLogById(id);
    }
}

package cn.com.infosouth.arj21.mapper;

import cn.com.infosouth.arj21.domain.InfoImexportLog;

import java.util.List;

/**
 * 导入导出日志Mapper接口
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public interface InfoImexportLogMapper 
{
    /**
     * 查询导入导出日志
     * 
     * @param id 导入导出日志ID
     * @return 导入导出日志
     */
    public InfoImexportLog selectInfoImexportLogById(String id);

    /**
     * 查询导入导出日志列表
     * 
     * @param infoImexportLog 导入导出日志
     * @return 导入导出日志集合
     */
    public List<InfoImexportLog> selectInfoImexportLogList(InfoImexportLog infoImexportLog);

    /**
     * 新增导入导出日志
     * 
     * @param infoImexportLog 导入导出日志
     * @return 结果
     */
    public int insertInfoImexportLog(InfoImexportLog infoImexportLog);

    /**
     * 修改导入导出日志
     * 
     * @param infoImexportLog 导入导出日志
     * @return 结果
     */
    public int updateInfoImexportLog(InfoImexportLog infoImexportLog);

    /**
     * 删除导入导出日志
     * 
     * @param id 导入导出日志ID
     * @return 结果
     */
    public int deleteInfoImexportLogById(String id);

    /**
     * 批量删除导入导出日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoImexportLogByIds(String[] ids);
}

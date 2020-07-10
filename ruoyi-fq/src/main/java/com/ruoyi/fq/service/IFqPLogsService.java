package com.ruoyi.fq.service;

import java.util.List;
import com.ruoyi.fq.domain.FqPLogs;

/**
 * 封铅袋出入库记录Service接口
 *
 * @author mario
 * @date 2020-07-09
 */
public interface IFqPLogsService
{
    /**
     * 查询封铅袋出入库记录
     *
     * @param id 封铅袋出入库记录ID
     * @return 封铅袋出入库记录
     */
    public FqPLogs selectFqPLogsById(String id);

    /**
     * 查询封铅袋出入库记录列表
     *
     * @param fqPLogs 封铅袋出入库记录
     * @return 封铅袋出入库记录集合
     */
    public List<FqPLogs> selectFqPLogsList(FqPLogs fqPLogs);

    /**
     * 新增封铅袋出入库记录
     *
     * @param fqPLogs 封铅袋出入库记录
     * @return 结果
     */
    public int insertFqPLogs(FqPLogs fqPLogs);

    /**
     * 修改封铅袋出入库记录
     *
     * @param fqPLogs 封铅袋出入库记录
     * @return 结果
     */
    public int updateFqPLogs(FqPLogs fqPLogs);

    /**
     * 批量删除封铅袋出入库记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFqPLogsByIds(String ids);

    /**
     * 删除封铅袋出入库记录信息
     *
     * @param id 封铅袋出入库记录ID
     * @return 结果
     */
    public int deleteFqPLogsById(String id);
    
    /**
     * 导入数据
     *
     * @param list 数据列表列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importData(List<FqPLogs> list, Boolean isUpdateSupport, String operName);
}

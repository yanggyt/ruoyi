package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiSubTask;

/**
 * 产品子任务Mapper接口
 * 
 * @author WangCL
 * @date 2022-01-10
 */
public interface BusiSubTaskMapper 
{
    /**
     * 查询产品子任务
     * 
     * @param id 产品子任务主键
     * @return 产品子任务
     */
    public BusiSubTask selectBusiSubTaskById(String id);

    /**
     * 查询产品子任务列表
     * 
     * @param busiSubTask 产品子任务
     * @return 产品子任务集合
     */
    public List<BusiSubTask> selectBusiSubTaskList(BusiSubTask busiSubTask);

    /**
     * 新增产品子任务
     * 
     * @param busiSubTask 产品子任务
     * @return 结果
     */
    public int insertBusiSubTask(BusiSubTask busiSubTask);

    /**
     * 修改产品子任务
     * 
     * @param busiSubTask 产品子任务
     * @return 结果
     */
    public int updateBusiSubTask(BusiSubTask busiSubTask);

    /**
     * 删除产品子任务
     * 
     * @param id 产品子任务主键
     * @return 结果
     */
    public int deleteBusiSubTaskById(String id);

    /**
     * 批量删除产品子任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiSubTaskByIds(String[] ids);
}

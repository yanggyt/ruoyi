package com.ruoyi.busi.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.busi.domain.BusiTask;

/**
 * 生产任务Service接口
 * 
 * @author WangCL
 * @date 2021-12-30
 */
public interface IBusiTaskService 
{
    /**
     * 查询生产任务
     * 
     * @param id 生产任务主键
     * @return 生产任务
     */
    public BusiTask selectBusiTaskById(String id);

    /**
     * 查询生产任务列表
     * 
     * @param busiTask 生产任务
     * @return 生产任务集合
     */
    public List<BusiTask> selectBusiTaskList(BusiTask busiTask);

    /**
     * 新增生产任务
     * 
     * @param busiTask 生产任务
     * @return 结果
     */
    public int insertBusiTask(BusiTask busiTask);

    /**
     * 修改生产任务
     * 
     * @param busiTask 生产任务
     * @return 结果
     */
    public int updateBusiTask(BusiTask busiTask);

    /**
     * 批量删除生产任务
     * 
     * @param ids 需要删除的生产任务主键集合
     * @return 结果
     */
    public int deleteBusiTaskByIds(String ids);

    /**
     * 删除生产任务信息
     * 
     * @param id 生产任务主键
     * @return 结果
     */
    public int deleteBusiTaskById(String id);


    public List<Map> selectProductRequire(String orderId);
}

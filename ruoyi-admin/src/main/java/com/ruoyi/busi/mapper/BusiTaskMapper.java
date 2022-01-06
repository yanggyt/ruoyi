package com.ruoyi.busi.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.busi.domain.BusiTask;
import com.ruoyi.busi.domain.BusiSubTask;

/**
 * 生产任务Mapper接口
 * 
 * @author WangCL
 * @date 2021-12-30
 */
public interface BusiTaskMapper 
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
     * 删除生产任务
     * 
     * @param id 生产任务主键
     * @return 结果
     */
    public int deleteBusiTaskById(String id);

    /**
     * 批量删除生产任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiTaskByIds(String[] ids);

    /**
     * 批量删除产品子任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiSubTaskByTaskIds(String[] ids);
    
    /**
     * 批量新增产品子任务
     * 
     * @param busiSubTaskList 产品子任务列表
     * @return 结果
     */
    public int batchBusiSubTask(List<BusiSubTask> busiSubTaskList);
    

    /**
     * 通过生产任务主键删除产品子任务信息
     * 
     * @param id 生产任务ID
     * @return 结果
     */
    public int deleteBusiSubTaskByTaskId(String id);


    /**
     * 查询产品的需求和已分配数量信息
     * @param orderId
     * @return
     */
    public List<Map> selectProductRequire(String orderId);

}

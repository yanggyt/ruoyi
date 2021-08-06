package com.ruoyi.kettle.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.kettle.domain.KettleJob;

/**
 * 作业调度Service接口
 * 
 * @author kone
 * @date 2021-07-22
 */
public interface IKettleJobService 
{
    /**
     * 查询作业调度
     * 
     * @param id 作业调度ID
     * @return 作业调度
     */
    public KettleJob selectKettleJobById(Long id);

    /**
     * 查询作业调度列表
     * 
     * @param kettleJob 作业调度
     * @return 作业调度集合
     */
    public List<KettleJob> selectKettleJobList(KettleJob kettleJob);

    /**
     * 新增作业调度
     * 
     * @param kettleJob 作业调度
     * @return 结果
     */
    public AjaxResult insertKettleJob(KettleJob kettleJob);

    /**
     * 修改作业调度
     * 
     * @param kettleJob 作业调度
     * @return 结果
     */
    public int updateKettleJob(KettleJob kettleJob);

    /**
     * 批量删除作业调度
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKettleJobByIds(String ids);

    /**
     * 删除作业调度信息
     * 
     * @param id 作业调度ID
     * @return 结果
     */
    public int deleteKettleJobById(Long id);

    AjaxResult run(KettleJob job);

    List<String> queryJobLog(KettleJob kettleJob);

    Long checkQuartzExist(String checkStr);

    public AjaxResult runJobQuartz(String id, String jobName);

    void runJobRightNow(Long valueOf);
}

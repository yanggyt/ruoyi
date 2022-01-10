package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiSubTaskMapper;
import com.ruoyi.busi.domain.BusiSubTask;
import com.ruoyi.busi.service.IBusiSubTaskService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品子任务Service业务层处理
 * 
 * @author WangCL
 * @date 2022-01-10
 */
@Service
public class BusiSubTaskServiceImpl implements IBusiSubTaskService 
{
    @Autowired
    private BusiSubTaskMapper busiSubTaskMapper;

    /**
     * 查询产品子任务
     * 
     * @param id 产品子任务主键
     * @return 产品子任务
     */
    @Override
    public BusiSubTask selectBusiSubTaskById(String id)
    {
        return busiSubTaskMapper.selectBusiSubTaskById(id);
    }

    /**
     * 查询产品子任务列表
     * 
     * @param busiSubTask 产品子任务
     * @return 产品子任务
     */
    @Override
    public List<BusiSubTask> selectBusiSubTaskList(BusiSubTask busiSubTask)
    {
        return busiSubTaskMapper.selectBusiSubTaskList(busiSubTask);
    }

    /**
     * 新增产品子任务
     * 
     * @param busiSubTask 产品子任务
     * @return 结果
     */
    @Override
    public int insertBusiSubTask(BusiSubTask busiSubTask)
    {
        busiSubTask.setCreateTime(DateUtils.getNowDate());
        return busiSubTaskMapper.insertBusiSubTask(busiSubTask);
    }

    /**
     * 修改产品子任务
     * 
     * @param busiSubTask 产品子任务
     * @return 结果
     */
    @Override
    public int updateBusiSubTask(BusiSubTask busiSubTask)
    {
        return busiSubTaskMapper.updateBusiSubTask(busiSubTask);
    }

    /**
     * 批量删除产品子任务
     * 
     * @param ids 需要删除的产品子任务主键
     * @return 结果
     */
    @Override
    public int deleteBusiSubTaskByIds(String ids)
    {
        return busiSubTaskMapper.deleteBusiSubTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品子任务信息
     * 
     * @param id 产品子任务主键
     * @return 结果
     */
    @Override
    public int deleteBusiSubTaskById(String id)
    {
        return busiSubTaskMapper.deleteBusiSubTaskById(id);
    }
}

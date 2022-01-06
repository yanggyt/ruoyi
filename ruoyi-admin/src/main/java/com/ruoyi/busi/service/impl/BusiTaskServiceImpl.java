package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.busi.domain.BusiSubTask;
import com.ruoyi.busi.mapper.BusiTaskMapper;
import com.ruoyi.busi.domain.BusiTask;
import com.ruoyi.busi.service.IBusiTaskService;
import com.ruoyi.common.core.text.Convert;

/**
 * 生产任务Service业务层处理
 * 
 * @author WangCL
 * @date 2021-12-30
 */
@Service
public class BusiTaskServiceImpl implements IBusiTaskService 
{
    @Autowired
    private BusiTaskMapper busiTaskMapper;

    /**
     * 查询生产任务
     * 
     * @param id 生产任务主键
     * @return 生产任务
     */
    @Override
    public BusiTask selectBusiTaskById(String id)
    {
        return busiTaskMapper.selectBusiTaskById(id);
    }

    /**
     * 查询生产任务列表
     * 
     * @param busiTask 生产任务
     * @return 生产任务
     */
    @Override
    public List<BusiTask> selectBusiTaskList(BusiTask busiTask)
    {
        return busiTaskMapper.selectBusiTaskList(busiTask);
    }

    /**
     * 新增生产任务
     * 
     * @param busiTask 生产任务
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBusiTask(BusiTask busiTask)
    {
        busiTask.setCreateTime(DateUtils.getNowDate());
        int rows = busiTaskMapper.insertBusiTask(busiTask);
        insertBusiSubTask(busiTask);
        return rows;
    }

    /**
     * 修改生产任务
     * 
     * @param busiTask 生产任务
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBusiTask(BusiTask busiTask)
    {
        busiTask.setUpdateTime(DateUtils.getNowDate());
        busiTaskMapper.deleteBusiSubTaskByTaskId(busiTask.getId());
        insertBusiSubTask(busiTask);
        return busiTaskMapper.updateBusiTask(busiTask);
    }

    /**
     * 批量删除生产任务
     * 
     * @param ids 需要删除的生产任务主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBusiTaskByIds(String ids)
    {
        busiTaskMapper.deleteBusiSubTaskByTaskIds(Convert.toStrArray(ids));
        return busiTaskMapper.deleteBusiTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产任务信息
     * 
     * @param id 生产任务主键
     * @return 结果
     */
    @Override
    public int deleteBusiTaskById(String id)
    {
        busiTaskMapper.deleteBusiSubTaskByTaskId(id);
        return busiTaskMapper.deleteBusiTaskById(id);
    }

    @Override
    public List<Map> selectProductRequire(String orderId) {
        return busiTaskMapper.selectProductRequire(orderId);
    }

    /**
     * 新增产品子任务信息
     * 
     * @param busiTask 生产任务对象
     */
    public void insertBusiSubTask(BusiTask busiTask)
    {
        List<BusiSubTask> busiSubTaskList = busiTask.getBusiSubTaskList();
        String id = busiTask.getId();
        if (StringUtils.isNotNull(busiSubTaskList))
        {
            List<BusiSubTask> list = new ArrayList<BusiSubTask>();
            for (BusiSubTask busiSubTask : busiSubTaskList)
            {
                busiSubTask.setTaskId(id);
                busiSubTask.setStatus("1");
                busiSubTask.setCompletedAmount(0l);
                list.add(busiSubTask);
            }
            if (list.size() > 0)
            {
                busiTaskMapper.batchBusiSubTask(list);
            }
        }
    }
}

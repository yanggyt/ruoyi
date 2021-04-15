package com.sinosoft.activity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.DrawTaskConsumeMapper;
import com.sinosoft.activity.domain.DrawTaskConsume;
import com.sinosoft.activity.service.IDrawTaskConsumeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 抽奖次数消费信息Service业务层处理
 * 
 * @author dy
 * @date 2021-03-26
 */
@Service
public class DrawTaskConsumeServiceImpl implements IDrawTaskConsumeService 
{
    @Autowired
    private DrawTaskConsumeMapper drawTaskConsumeMapper;

    /**
     * 查询抽奖次数消费信息
     * 
     * @param TASKCONSUMEID 抽奖次数消费信息ID
     * @return 抽奖次数消费信息
     */
    @Override
    public DrawTaskConsume selectDrawTaskConsumeById(String TASKCONSUMEID)
    {
        return drawTaskConsumeMapper.selectDrawTaskConsumeById(TASKCONSUMEID);
    }

    /**
     * 查询抽奖次数消费信息列表
     * 
     * @param drawTaskConsume 抽奖次数消费信息
     * @return 抽奖次数消费信息
     */
    @Override
    public List<DrawTaskConsume> selectDrawTaskConsumeList(DrawTaskConsume drawTaskConsume)
    {
        return drawTaskConsumeMapper.selectDrawTaskConsumeList(drawTaskConsume);
    }

    /**
     * 新增抽奖次数消费信息
     * 
     * @param drawTaskConsume 抽奖次数消费信息
     * @return 结果
     */
    @Override
    public int insertDrawTaskConsume(DrawTaskConsume drawTaskConsume)
    {
        return drawTaskConsumeMapper.insertDrawTaskConsume(drawTaskConsume);
    }

    /**
     * 修改抽奖次数消费信息
     * 
     * @param drawTaskConsume 抽奖次数消费信息
     * @return 结果
     */
    @Override
    public int updateDrawTaskConsume(DrawTaskConsume drawTaskConsume)
    {
        return drawTaskConsumeMapper.updateDrawTaskConsume(drawTaskConsume);
    }
    @Override
    public int updateDrawTaskConsumeState(DrawTaskConsume drawTaskConsume)
    {
        return drawTaskConsumeMapper.updateDrawTaskConsumeState(drawTaskConsume);
    }

    /**
     * 删除抽奖次数消费信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawTaskConsumeByIds(String ids)
    {
        return drawTaskConsumeMapper.deleteDrawTaskConsumeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除抽奖次数消费信息信息
     * 
     * @param TASKCONSUMEID 抽奖次数消费信息ID
     * @return 结果
     */
    @Override
    public int deleteDrawTaskConsumeById(String TASKCONSUMEID)
    {
        return drawTaskConsumeMapper.deleteDrawTaskConsumeById(TASKCONSUMEID);
    }
}

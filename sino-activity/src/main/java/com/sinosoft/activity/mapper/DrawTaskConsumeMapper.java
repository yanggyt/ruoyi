package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.DrawTaskConsume;

/**
 * 抽奖次数消费信息Mapper接口
 * 
 * @author dy
 * @date 2021-03-26
 */
public interface DrawTaskConsumeMapper 
{
    /**
     * 查询抽奖次数消费信息
     * 
     * @param TASKCONSUMEID 抽奖次数消费信息ID
     * @return 抽奖次数消费信息
     */
    public DrawTaskConsume selectDrawTaskConsumeById(String TASKCONSUMEID);

    /**
     * 查询抽奖次数消费信息列表
     * 
     * @param drawTaskConsume 抽奖次数消费信息
     * @return 抽奖次数消费信息集合
     */
    public List<DrawTaskConsume> selectDrawTaskConsumeList(DrawTaskConsume drawTaskConsume);

    /**
     * 新增抽奖次数消费信息
     * 
     * @param drawTaskConsume 抽奖次数消费信息
     * @return 结果
     */
    public int insertDrawTaskConsume(DrawTaskConsume drawTaskConsume);

    /**
     * 修改抽奖次数消费信息
     * 
     * @param drawTaskConsume 抽奖次数消费信息
     * @return 结果
     */
    public int updateDrawTaskConsume(DrawTaskConsume drawTaskConsume);
    public int updateDrawTaskConsumeState(DrawTaskConsume drawTaskConsume);

    /**
     * 删除抽奖次数消费信息
     * 
     * @param TASKCONSUMEID 抽奖次数消费信息ID
     * @return 结果
     */
    public int deleteDrawTaskConsumeById(String TASKCONSUMEID);

    /**
     * 批量删除抽奖次数消费信息
     * 
     * @param TASKCONSUMEIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawTaskConsumeByIds(String[] TASKCONSUMEIDs);
}

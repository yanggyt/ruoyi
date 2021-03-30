package com.sinosoft.activity.mapper;


import com.sinosoft.activity.domain.DrawInfo;

import java.util.List;

/**
 * 抽奖活动管理对象Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
public interface DrawInfoMapper 
{
    /**
     * 查询抽奖活动管理对象
     * 
     * @param DRAWID 抽奖活动管理对象ID
     * @return 抽奖活动管理对象
     */
    public DrawInfo selectDrawInfoById(String DRAWID);

    /**
     * 查询抽奖活动管理对象列表
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 抽奖活动管理对象集合
     */
    public List<DrawInfo> selectDrawInfoList(DrawInfo drawInfo);

    /**
     * 新增抽奖活动管理对象
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 结果
     */
    public int insertDrawInfo(DrawInfo drawInfo);

    /**
     * 修改抽奖活动管理对象
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 结果
     */
    public int updateDrawInfo(DrawInfo drawInfo);

    /**
     * 删除抽奖活动管理对象
     * 
     * @param DRAWID 抽奖活动管理对象ID
     * @return 结果
     */
    public int deleteDrawInfoById(String DRAWID);

    /**
     * 批量删除抽奖活动管理对象
     * 
     * @param DRAWIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawInfoByIds(String[] DRAWIDs);
}

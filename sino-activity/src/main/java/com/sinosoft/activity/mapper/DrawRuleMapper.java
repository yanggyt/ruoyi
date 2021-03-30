package com.sinosoft.activity.mapper;

import java.util.List;

import com.sinosoft.activity.domain.DrawRule;

/**
 * 查询抽奖活动管理对象Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
public interface DrawRuleMapper 
{
    /**
     * 查询查询抽奖活动管理对象
     * 
     * @param DRAWRULEID 查询抽奖活动管理对象ID
     * @return 查询抽奖活动管理对象
     */
    public DrawRule selectDrawRuleById(String DRAWRULEID);

    /**
     * 查询查询抽奖活动管理对象列表
     * 
     * @param drawRule 查询抽奖活动管理对象
     * @return 查询抽奖活动管理对象集合
     */
    public List<DrawRule> selectDrawRuleList(DrawRule drawRule);

    /**
     * 新增查询抽奖活动管理对象
     * 
     * @param drawRule 查询抽奖活动管理对象
     * @return 结果
     */
    public int insertDrawRule(DrawRule drawRule);

    /**
     * 修改查询抽奖活动管理对象
     * 
     * @param drawRule 查询抽奖活动管理对象
     * @return 结果
     */
    public int updateDrawRule(DrawRule drawRule);

    /**
     * 删除查询抽奖活动管理对象
     * 
     * @param DRAWRULEID 查询抽奖活动管理对象ID
     * @return 结果
     */
    public int deleteDrawRuleById(String DRAWRULEID);

    /**
     * 批量删除查询抽奖活动管理对象
     * 
     * @param DRAWRULEIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawRuleByIds(String[] DRAWRULEIDs);
}

package com.sinosoft.activity.service.impl;

import java.util.List;

import com.sinosoft.activity.domain.DrawRule;
import com.sinosoft.activity.mapper.DrawRuleMapper;
import com.sinosoft.activity.service.IDrawRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 抽奖活动管理对象Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
@Service
public class DrawRuleServiceImpl implements IDrawRuleService
{
    @Autowired
    private DrawRuleMapper drawRuleMapper;

    /**
     * 查询抽奖活动管理对象
     * 
     * @param DRAWRULEID 抽奖活动管理对象ID
     * @return 抽奖活动管理对象
     */
    @Override
    public DrawRule selectDrawRuleById(String DRAWRULEID)
    {
        return drawRuleMapper.selectDrawRuleById(DRAWRULEID);
    }

    /**
     * 查询抽奖活动管理对象列表
     * 
     * @param drawRule 抽奖活动管理对象
     * @return 抽奖活动管理对象
     */
    @Override
    public List<DrawRule> selectDrawRuleList(DrawRule drawRule)
    {
        return drawRuleMapper.selectDrawRuleList(drawRule);
    }

    /**
     * 新增抽奖活动管理对象
     * 
     * @param drawRule 抽奖活动管理对象
     * @return 结果
     */
    @Override
    public int insertDrawRule(DrawRule drawRule)
    {
        return drawRuleMapper.insertDrawRule(drawRule);
    }

    /**
     * 修改抽奖活动管理对象
     * 
     * @param drawRule 抽奖活动管理对象
     * @return 结果
     */
    @Override
    public int updateDrawRule(DrawRule drawRule)
    {
        return drawRuleMapper.updateDrawRule(drawRule);
    }

    /**
     * 删除抽奖活动管理对象对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawRuleByIds(String ids)
    {
        return drawRuleMapper.deleteDrawRuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除抽奖活动管理对象信息
     * 
     * @param DRAWRULEID 抽奖活动管理对象ID
     * @return 结果
     */
    @Override
    public int deleteDrawRuleById(String DRAWRULEID)
    {
        return drawRuleMapper.deleteDrawRuleById(DRAWRULEID);
    }
}

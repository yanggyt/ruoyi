package com.sinosoft.activity.service;


import com.sinosoft.activity.domain.DrawRule;

import java.util.List;

/**
 * 存储抽奖特殊规则对象Service接口
 * 
 * @author xlh
 * @date 2021-03-25
 */
public interface IDrawRuleService 
{
    /**
     * 查询存储抽奖特殊规则对象
     * 
     * @param DRAWRULEID 存储抽奖特殊规则对象ID
     * @return 存储抽奖特殊规则对象
     */
    public DrawRule selectDrawRuleById(String DRAWRULEID);

    /**
     * 查询存储抽奖特殊规则对象列表
     * 
     * @param drawRule 存储抽奖特殊规则对象
     * @return 存储抽奖特殊规则对象集合
     */
    public List<DrawRule> selectDrawRuleList(DrawRule drawRule);

    /**
     * 新增存储抽奖特殊规则对象
     * 
     * @param drawRule 存储抽奖特殊规则对象
     * @return 结果
     */
    public int insertDrawRule(DrawRule drawRule);

    /**
     * 修改存储抽奖特殊规则对象
     * 
     * @param drawRule 存储抽奖特殊规则对象
     * @return 结果
     */
    public int updateDrawRule(DrawRule drawRule);

    /**
     * 批量删除存储抽奖特殊规则对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawRuleByIds(String ids);

    /**
     * 删除存储抽奖特殊规则对象信息
     * 
     * @param DRAWRULEID 存储抽奖特殊规则对象ID
     * @return 结果
     */
    public int deleteDrawRuleById(String DRAWRULEID);

    /**
     * 根据活动编码查询
     * @param drawCode
     * @return
     */
    public DrawRule selectDrawRuleByCode(String drawCode);
}

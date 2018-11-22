package com.cronie.mengyu.service;

import com.cronie.mengyu.domain.YearPlan;
import java.util.List;

/**
 * 年度计划 服务层
 * 
 * @author cronie
 * @date 2018-11-15
 */
public interface IYearPlanService 
{
	/**
     * 查询年度计划信息
     * 
     * @param year 年度计划ID
     * @return 年度计划信息
     */
	public YearPlan selectYearPlanById(String year);
	
	/**
     * 查询年度计划列表
     * 
     * @param yearPlan 年度计划信息
     * @return 年度计划集合
     */
	public List<YearPlan> selectYearPlanList(YearPlan yearPlan);
	
	/**
     * 新增年度计划
     * 
     * @param yearPlan 年度计划信息
     * @return 结果
     */
	public int insertYearPlan(YearPlan yearPlan);
	
	/**
     * 修改年度计划
     * 
     * @param yearPlan 年度计划信息
     * @return 结果
     */
	public int updateYearPlan(YearPlan yearPlan);
		
	/**
     * 删除年度计划信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteYearPlanByIds(String ids);
	
}

package com.cronie.mengyu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cronie.mengyu.mapper.YearPlanMapper;
import com.cronie.mengyu.domain.YearPlan;
import com.cronie.mengyu.service.IYearPlanService;
import com.ruoyi.common.support.Convert;

/**
 * 年度计划 服务层实现
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Service
public class YearPlanServiceImpl implements IYearPlanService 
{
	@Autowired
	private YearPlanMapper yearPlanMapper;

	/**
     * 查询年度计划信息
     * 
     * @param year 年度计划ID
     * @return 年度计划信息
     */
    @Override
	public YearPlan selectYearPlanById(String year)
	{
	    return yearPlanMapper.selectYearPlanById(year);
	}
	
	/**
     * 查询年度计划列表
     * 
     * @param yearPlan 年度计划信息
     * @return 年度计划集合
     */
	@Override
	public List<YearPlan> selectYearPlanList(YearPlan yearPlan)
	{
	    return yearPlanMapper.selectYearPlanList(yearPlan);
	}
	
    /**
     * 新增年度计划
     * 
     * @param yearPlan 年度计划信息
     * @return 结果
     */
	@Override
	public int insertYearPlan(YearPlan yearPlan)
	{
	    return yearPlanMapper.insertYearPlan(yearPlan);
	}
	
	/**
     * 修改年度计划
     * 
     * @param yearPlan 年度计划信息
     * @return 结果
     */
	@Override
	public int updateYearPlan(YearPlan yearPlan)
	{
	    return yearPlanMapper.updateYearPlan(yearPlan);
	}

	/**
     * 删除年度计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteYearPlanByIds(String ids)
	{
		return yearPlanMapper.deleteYearPlanByIds(Convert.toStrArray(ids));
	}
	
}

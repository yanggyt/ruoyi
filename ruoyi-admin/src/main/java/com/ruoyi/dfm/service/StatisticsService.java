package com.ruoyi.dfm.service;

import com.ruoyi.dfm.dao.StatisticsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 统计服务
 */
@Component
public class StatisticsService {
	@Autowired
	private StatisticsDAO statisticsDAO;

	public void setStatisticsDAO(StatisticsDAO statisticsDAO) {
		this.statisticsDAO = statisticsDAO;
	}

	/**
	 * 统计系统总利用率
	 */
	public Map<String, Object> usage() {
		return statisticsDAO.usage();
	}

    /**
     * 获取根据部门分组的时间利用率
     * @return
     */
	public List<Map<String, Object>> usageGroupByDepartment(){
	    return statisticsDAO.usageGroupByDepartment();
    }

	/**
	 * 获取根据部门和年份分组的时间利用率
	 * @return
	 */
	public List<Map<String, Object>> usageGroupByDepartmentAndYear(){
		return statisticsDAO.usageGroupByDepartmentAndYear();
	}

    /**
     * 统计完成数量
     * 过滤大于20小时的不合法运行时间
     * 月份：f_month
     * 完成的项目数量：f_count
     * @return
     */
    public List<Map<String, Object>> countByMonth(String year){
        return statisticsDAO.countByMonth(year);
    }

    /**
     * 获取所有年份
     * @return
     */
    public List<Map<String, Object>> getAllYear(){
        return statisticsDAO.getAllYear();
    }
}

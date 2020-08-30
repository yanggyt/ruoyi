package com.ruoyi.dfm.dao;


import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 统计功能DAO
 */
@Component
public class StatisticsDAO extends JdbcBaseDao {

	private final static int groupByYear = 4;

	/**
	 * 获取总利用率
	 * 过滤大于20小时的不合法运行时间
	 * 总时间：f_total_time
	 * 总运行时间：f_total_run_time
	 * @return
	 */
	public Map<String, Object> usage(){
		String sql = "SELECT " +
				"TIMESTAMPDIFF(MINUTE, min(str_to_date(F_SUBMIT_TIME, '%Y%m%d%H%i%s')), max(str_to_date(f_end_time, '%Y%m%d%H%i%s'))) as f_total_time, " +
				"sum(F_RUN_TIME) as f_total_run_time " +
				"FROM t_project " +
				"where F_STATE in ('完成') and f_run_time < 1200;";
		log.info("获取利用率sql=" + sql);
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
		return list.get(0);
	}

	/**
	 * 获取根据部门分组的时间利用率
	 * 过滤大于20小时的不合法运行时间
	 * 部门：F_DEPARTMENT
	 * 总时间：f_total_time
	 * 总运行时间：f_total_run_time
	 * @return
	 */
	public List<Map<String, Object>> usageGroupByDepartment(){
		String sql = "SELECT u.F_DEPARTMENT, " +
				//"TIMESTAMPDIFF (MINUTE, min(str_to_date(p.F_SUBMIT_TIME, '%Y%m%d%H%i%s')), max(str_to_date(p.f_end_time, '%Y%m%d%H%i%s'))) as f_total_time, " +
				"sum(p.F_RUN_TIME) as f_total_run_time " +
				"FROM t_project p " +
				"LEFT JOIN t_user u on p.F_SUBMIT_USER = u.F_ID " +
				"WHERE p.F_STATE in ('完成') and p.f_run_time < 1200 " +
				"group by (u.F_DEPARTMENT);";
		log.info("获取利用率sql=" + sql);
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
		return list;
	}

	/**
	 * 获取根据部门和年份分组的时间利用率
	 * 过滤大于20小时的不合法运行时间
	 * 部门：F_DEPARTMENT
	 * 年份：F_YEAR
	 * 总时间：f_total_time
	 * 总运行时间：f_total_run_time
	 * @return
	 */
	public List<Map<String, Object>> usageGroupByDepartmentAndYear(){
		String sql = "SELECT u.F_DEPARTMENT, left(p.F_SUBMIT_TIME,"+groupByYear+") as F_YEAR ," +
				//"TIMESTAMPDIFF (MINUTE, min(str_to_date(p.F_SUBMIT_TIME, '%Y%m%d%H%i%s')), max(str_to_date(p.f_end_time, '%Y%m%d%H%i%s'))) as f_total_time, " +
				"sum(p.F_RUN_TIME) as f_total_run_time " +
				"FROM t_project p " +
				"LEFT JOIN t_user u on p.F_SUBMIT_USER = u.F_ID " +
				"WHERE p.F_STATE in ('完成') and p.f_run_time < 1200 " +
				"group by u.F_DEPARTMENT, left(p.F_SUBMIT_TIME,"+groupByYear+") " +
				"order by left(p.F_SUBMIT_TIME,"+groupByYear+") asc";
		log.info("根据部门和年份分组的时间利用率sql=" + sql);
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
		return list;
	}

    /**
     * 统计完成数量
     * 过滤大于20小时的不合法运行时间
     * 月份：f_month
     * 完成的项目数量：f_count
     * @return
     */
    public List<Map<String, Object>> countByMonth(String year){
        String sql = "SELECT left(p.f_end_time,6) as f_month , count(1) as f_count" +
                " FROM t_project p " +
                " WHERE p.F_STATE in ('完成') and left(p.f_end_time,4) = ?" +
                " group by left(p.f_end_time,6) " +
                " order by left(p.f_end_time,6) asc";
        log.info("统计完成数量sql=" + sql);
        List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql, new Object[] {year});
        return list;
    }

    /**
     * 获取所有年份
     * @return
     */
    public List<Map<String, Object>> getAllYear(){
        String sql = "select distinct left(p.f_end_time,4) as f_year" +
                " FROM t_project p " +
                " where p.f_end_time != ''" +
                " order by left(p.f_end_time,4) desc";
        log.info("获取所有年份sql=" + sql);
        List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
        return list;
    }

}

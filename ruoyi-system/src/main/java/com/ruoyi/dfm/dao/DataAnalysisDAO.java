package com.ruoyi.dfm.dao;

import com.ruoyi.dfm.pojo.DataAnalysisBean;
import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.dfm.util.TimeUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DataAnalysisDAO extends JdbcBaseDao {

	public List<DataAnalysisBean> analysisByProject(String projectName, String startSubmitTime,
													String endSubmitTime) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t.F_PROJECT_NAME, t.F_VERSION , t.F_ISSUE_DESCRIPTION, SUM(t.F_ISSUE_QTY) F_ISSUE_QTY ");
		sb.append("FROM t_dataays t ");
		sb.append("where t.F_PROJECT_NAME = ? AND T.F_SUBMIT_TIME >= ? AND T.F_SUBMIT_TIME <= ? ");
		sb.append("group by t.F_PROJECT_NAME, t.F_VERSION , t.F_ISSUE_DESCRIPTION ");
		sb.append("order by SUM(t.F_ISSUE_QTY) desc, t.F_VERSION ASC");
		//先根据问题数量倒叙排列，再根据版本升序排列
		
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sb.toString(), new Object[] {
				projectName,
				TimeUtil.getDateStrByFormat(startSubmitTime, "yyyy-MM-dd", "yyyyMMdd") + "000000",
				TimeUtil.getDateStrByFormat(endSubmitTime, "yyyy-MM-dd", "yyyyMMdd") + "235959"
		});
		
		if(null == list || list.size() <= 0) {
			return null;
		}
		List<DataAnalysisBean> result = new ArrayList<DataAnalysisBean>(list.size());
		for(Map<String, Object> map : list) {
			DataAnalysisBean b = new DataAnalysisBean();
			b.setProjectName((String)map.get("F_PROJECT_NAME"));
			b.setVersion(null == map.get("F_VERSION") ? 0 : Integer.parseInt(map.get("F_VERSION").toString()));
			b.setIssueDescription((String)map.get("F_ISSUE_DESCRIPTION"));
			b.setIssueQty(null == map.get("F_ISSUE_QTY") ? 0 : Integer.parseInt(map.get("F_ISSUE_QTY").toString()));
			result.add(b);
		}
		return result;
	}

	public List<DataAnalysisBean> analysisByUser(String userName, String startSubmitTime, String endSubmitTime) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t.F_SUBMIT_USERNAME, t.F_ISSUE_DESCRIPTION, SUM(t.F_ISSUE_QTY) F_ISSUE_QTY ");
		sb.append("FROM t_dataays t ");
		sb.append("where t.F_SUBMIT_USERNAME = ? AND T.F_SUBMIT_TIME >= ? AND T.F_SUBMIT_TIME <= ? ");
		sb.append("group by t.F_SUBMIT_USERNAME, t.F_ISSUE_DESCRIPTION ");
		sb.append("order by SUM(t.F_ISSUE_QTY) DESC");
		
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sb.toString(), new Object[] {
				userName,
				TimeUtil.getDateStrByFormat(startSubmitTime, "yyyy-MM-dd", "yyyyMMdd") + "000000",
				TimeUtil.getDateStrByFormat(endSubmitTime, "yyyy-MM-dd", "yyyyMMdd") + "235959"
		});
		
		if(null == list || list.size() <= 0) {
			return null;
		}
		List<DataAnalysisBean> result = new ArrayList<DataAnalysisBean>(list.size());
		for(Map<String, Object> map : list) {
			DataAnalysisBean b = new DataAnalysisBean();
			b.setSubmitUserName((String)map.get("F_SUBMIT_USERNAME"));
			b.setIssueDescription((String)map.get("F_ISSUE_DESCRIPTION"));
			b.setIssueQty(null == map.get("F_ISSUE_QTY") ? 0 : Integer.parseInt(map.get("F_ISSUE_QTY").toString()));
			result.add(b);
		}
		return result;
	}
	
	public List<DataAnalysisBean> analysisByAllUser(String startSubmitTime, String endSubmitTime, List<UserInfo> submitUsers) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t.F_SUBMIT_USERNAME, SUM(t.F_ISSUE_QTY) F_ISSUE_QTY ");
		sb.append("FROM t_dataays t ");
		sb.append("where T.F_SUBMIT_TIME >= ? AND T.F_SUBMIT_TIME <= ? ");
		sb.append("AND T.F_SUBMIT_USER IN (-1");
		for (int i = 0; i < submitUsers.size(); ++i)
		{
			sb.append( "," + submitUsers.get(i).getId());
		}
		sb.append(")");
		sb.append("group by t.F_SUBMIT_USERNAME ");
		sb.append("order by SUM(t.F_ISSUE_QTY) DESC");
		
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sb.toString(), new Object[] {
				TimeUtil.getDateStrByFormat(startSubmitTime, "yyyy-MM-dd", "yyyyMMdd") + "000000",
				TimeUtil.getDateStrByFormat(endSubmitTime, "yyyy-MM-dd", "yyyyMMdd") + "235959"
		});
		
		if(null == list || list.size() <= 0) {
			return null;
		}
		List<DataAnalysisBean> result = new ArrayList<DataAnalysisBean>(list.size());
		for(Map<String, Object> map : list) {
			DataAnalysisBean b = new DataAnalysisBean();
			b.setSubmitUserName((String)map.get("F_SUBMIT_USERNAME"));
			//b.setIssueDescription((String)map.get("F_ISSUE_DESCRIPTION"));
			b.setIssueQty(null == map.get("F_ISSUE_QTY") ? 0 : Integer.parseInt(map.get("F_ISSUE_QTY").toString()));
			result.add(b);
		}
		return result;
	}

	public List<DataAnalysisBean> analysisByAllIssue(String startSubmitTime, String endSubmitTime, List<UserInfo> submitUsers) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t.F_ISSUE_DESCRIPTION, SUM(t.F_ISSUE_QTY) F_ISSUE_QTY ");
		sb.append("FROM t_dataays t ");
		sb.append("where T.F_SUBMIT_TIME >= ? AND T.F_SUBMIT_TIME <= ? ");
		sb.append("AND T.F_SUBMIT_USER IN (-1");
		for (int i = 0; i < submitUsers.size(); ++i)
		{
			sb.append( "," + submitUsers.get(i).getId());
		}
		sb.append(")");
		sb.append("group by t.F_ISSUE_DESCRIPTION ");
		sb.append("order by SUM(t.F_ISSUE_QTY) DESC");
		
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sb.toString(), new Object[] {
				TimeUtil.getDateStrByFormat(startSubmitTime, "yyyy-MM-dd", "yyyyMMdd") + "000000",
				TimeUtil.getDateStrByFormat(endSubmitTime, "yyyy-MM-dd", "yyyyMMdd") + "235959"
		});
		
		if(null == list || list.size() <= 0) {
			return null;
		}
		List<DataAnalysisBean> result = new ArrayList<DataAnalysisBean>(list.size());
		for(Map<String, Object> map : list) {
			DataAnalysisBean b = new DataAnalysisBean();
			b.setIssueDescription((String)map.get("F_ISSUE_DESCRIPTION"));
			b.setIssueQty(null == map.get("F_ISSUE_QTY") ? 0 : Integer.parseInt(map.get("F_ISSUE_QTY").toString()));
			result.add(b);
		}
		return result;
	}
	
}

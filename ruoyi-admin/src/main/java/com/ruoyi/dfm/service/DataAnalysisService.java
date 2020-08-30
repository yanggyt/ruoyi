package com.ruoyi.dfm.service;

import com.ruoyi.dfm.dao.DataAnalysisDAO;
import com.ruoyi.dfm.pojo.DataAnalysisBean;
import com.ruoyi.dfm.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataAnalysisService {
	@Autowired
	private DataAnalysisDAO dataAnalysisDAO;

	public List<DataAnalysisBean> analysisByProject(String projectName, String startSubmitTime, String endSubmitTime) {
		return dataAnalysisDAO.analysisByProject(projectName, startSubmitTime, endSubmitTime) ;
		
	}

	public List<DataAnalysisBean> analysisByUser(String userName, String startSubmitTime, String endSubmitTime) {
		return dataAnalysisDAO.analysisByUser(userName, startSubmitTime, endSubmitTime) ;
	}
	
	public List<DataAnalysisBean> analysisByAllUser(String startSubmitTime, String endSubmitTime, List<UserInfo> submitUsers) {
		return dataAnalysisDAO.analysisByAllUser(startSubmitTime, endSubmitTime, submitUsers) ;
	}
	
	public List<DataAnalysisBean> analysisByAllIssue(String startSubmitTime, String endSubmitTime, List<UserInfo> submitUsers) {
		return dataAnalysisDAO.analysisByAllIssue(startSubmitTime, endSubmitTime, submitUsers) ;
	}
	
}

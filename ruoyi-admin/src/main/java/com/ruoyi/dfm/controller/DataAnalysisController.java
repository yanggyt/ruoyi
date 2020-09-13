package com.ruoyi.dfm.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.dfm.constant.UserConstants;
import com.ruoyi.dfm.pojo.DataAnalysisBean;
import com.ruoyi.dfm.pojo.Result;
import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.dfm.service.DataAnalysisService;
import com.ruoyi.dfm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 数据分析控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dataAnalysis.do")
public class DataAnalysisController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(DataAnalysisController.class);
	
	private static final String ANALYSIS_TYPE_USER = "submitUser";
	private static final String ANALYSIS_TYPE_PROJECT = "submitProject";
	
	private static final String ANALYSIS_TYPE_ALL_USER = "allUser";
	private static final String ANALYSIS_TYPE_ALL_ISSUE = "allIssue";
	
	//数据分析服务
	@Autowired
	private DataAnalysisService dataAnalysisService;
	//用户服务
	@Autowired
	private UserService userService;

	/**
	 * 用户管理控制器，默认打开个人资料方法
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/")
	public ModelAndView defaultHandle(HttpServletRequest req,
                                      HttpServletResponse res) throws Exception {
		req.setAttribute("users", getSubmitUsers(req));
		return new ModelAndView("dfm/dataAnalysis");
	}

	/**
	 * 根据当前登陆用户，获取查询条件的上传人
	 * @param req
	 * @return
	 */
	private List<UserInfo> getSubmitUsers(HttpServletRequest req){
		UserInfo user = getUserInfo(req);
		//获取上传人下拉列表
		List<UserInfo> users = null;
		if (UserConstants.USER_LEVEL_ADMIN == user.getGroupId())
		{
			users = this.userService.getAllUser();
		}
		else if (UserConstants.USER_LEVEL_NORMAL == user.getGroupId())
		{
			users = Arrays.asList(user);
		}
		//如果是部门管理员，则可以查看部门所有的项目
		else if (UserConstants.USER_LEVEL_DEP_ADMIN == user.getGroupId())
		{
			//根据部门查询部门所有用户
			String department = user.getDepartment();
			users = userService.getByDepartment(department);
		}
		return users;
	}


	/**
	 * 打开数据分析页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataAnalysis")
	public ModelAndView dataAnalysis(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setAttribute("users", getSubmitUsers(req));
		return new ModelAndView("dataAnalysis");
	}


	/**
	 * 检查当前需要分析的projectName是否属于当前登陆用户的可见范围
	 *
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkProjectNameByLoginUser")
	public Result checkProjectNameByLoginUser(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String projectName = req.getParameter("projectName");
		UserInfo user = getUserInfo(req);
		boolean checkResult = false;
		Result result = new Result();

		if (UserConstants.USER_LEVEL_ADMIN == user.getGroupId())
		{
			result.setSuccess(true);
			result.setMessage("分析成功");
		}
		else if (UserConstants.USER_LEVEL_NORMAL == user.getGroupId())
		{
			List<UserInfo> submitUsers = getSubmitUsers(req);
			checkResult = userService.checkProjectAndUsers(projectName, submitUsers);
			if(checkResult) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setMessage("当前项目不属于当前用户");
			}
		}
		//如果是部门管理员，则可以查看部门所有的项目
		else if (UserConstants.USER_LEVEL_DEP_ADMIN == user.getGroupId())
		{
			List<UserInfo> submitUsers = getSubmitUsers(req);
			checkResult = userService.checkProjectAndUsers(projectName, submitUsers);
			if(checkResult) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setMessage("当前项目不属于当前部门管理员");
			}
		}
		return result;
	}


	/**
	 * 分析数据
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/analysis")
	@ResponseBody
	public Result analysis(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String projectName = req.getParameter("projectName");
		String submitUser = req.getParameter("submitUser");
		
		String startSubmitTime = req.getParameter("startSubmitTime");
		String endSubmitTime = req.getParameter("endSubmitTime");
		
		String analysisType = req.getParameter("analysisType");
		
		Map<String, Object> data = new TreeMap<>();

		//获取当前登陆用户的用户组
		//分析原则：系统管理员分析所有的，部门管理员只能分析本部门的，普通用户只能分析自己的

		if(ANALYSIS_TYPE_PROJECT.equals(analysisType)) {
			//校验projectName是否是当前用户可以看到的
			Result checkResult = checkProjectNameByLoginUser(req, res);
			if(null == checkResult || !checkResult.isSuccess()) {
//				outputJson(res, JSON.toJSONString(checkResult));
				return checkResult;
			}

			List<DataAnalysisBean> result = dataAnalysisService.analysisByProject(projectName, startSubmitTime, endSubmitTime);
			//data.put("result", result);
			
			if(null != result && result.size() > 0) {
				//构造版本集合
				TreeSet<Integer> versions = new TreeSet<>();
				
				for(DataAnalysisBean b : result) {
					versions.add(b.getVersion());
				}
				data.put("versions", versions);
				
				//按照问题种类对结果集进行分组
				Map<String, List<DataAnalysisBean>> issues = new HashMap<>();
				
				for(DataAnalysisBean b : result) {
					String key = b.getIssueDescription();
					if(!issues.containsKey(key)) {
						List<DataAnalysisBean> list = new ArrayList<>();
						issues.put(key, list);
					}
					issues.get(key).add(b);
				}
				
				//按照版本进行填充
				Integer [] versionArr = versions.toArray(new Integer[] {});
				Iterator<String> it = issues.keySet().iterator();
				while(it.hasNext()) {
					String key = it.next();
					List<DataAnalysisBean> list = issues.get(key);
					Collections.sort(list, new Comparator<DataAnalysisBean>() {
						@Override
						public int compare(DataAnalysisBean o1, DataAnalysisBean o2) {
							return o1.getVersion() - o2.getVersion();
						}
					});
					for(int i = 0;i<list.size();i++) {
						if(list.get(i).getVersion() != versionArr[i].intValue()) {
							//如果没有版本的，补全版本
							DataAnalysisBean b = new DataAnalysisBean();
							b.setVersion(versionArr[i].intValue());
							b.setIssueQty(0);
							list.add(i, b);
						}
					}
					if(list.size() < versionArr.length) {
						for(int i=list.size();i<versionArr.length;i++) {
							//如果没有版本的，补全版本
							DataAnalysisBean b = new DataAnalysisBean();
							b.setVersion(versionArr[i].intValue());
							b.setIssueQty(0);
							list.add(b);
						}
					}
				}
				data.put("result", issues);
			}
			
		}
		
		else if(ANALYSIS_TYPE_USER.equals(analysisType)) {
			List<DataAnalysisBean> result = dataAnalysisService.analysisByUser(submitUser, startSubmitTime, endSubmitTime);
			data.put("result", result);
		}
		else {

			List<UserInfo> submitUsers = getSubmitUsers(req);
			if(ANALYSIS_TYPE_ALL_USER.equals(analysisType)) {
				List<DataAnalysisBean> result = dataAnalysisService.analysisByAllUser(startSubmitTime, endSubmitTime, submitUsers);
				data.put("result", result);
			}
			else if(ANALYSIS_TYPE_ALL_ISSUE.equals(analysisType)) {
				List<DataAnalysisBean> result = dataAnalysisService.analysisByAllIssue(startSubmitTime, endSubmitTime, submitUsers);
				data.put("result", result);
			}
			else {
				Result result = new Result();
				result.setSuccess(false);
				result.setMessage("分析失败，未选择分析类型！");
//				outputJson(res, JSON.toJSONString(result));
				return result;
			}
		}
		
		logger.info("分析数据");
		Result result = new Result();
		result.setSuccess(true);
		result.setMessage("分析成功");
		result.setData(data);
//		outputJson(res, JSON.toJSONString(result));
		return result;
	}

}

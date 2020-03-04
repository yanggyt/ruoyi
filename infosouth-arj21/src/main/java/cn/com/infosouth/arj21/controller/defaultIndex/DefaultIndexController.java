package cn.com.infosouth.arj21.controller.defaultIndex;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.infosouth.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.amas.job.HttpReqUriUtils;
import cn.com.infosouth.amas.modules.analysis.entity.InfoDutySchedule;
import cn.com.infosouth.amas.modules.analysis.service.InfoDutyScheduleService;
import cn.com.infosouth.amas.modules.csvmanager.service.InfoFlightService;
import cn.com.infosouth.amas.modules.parameter.service.computed.InfoParameterComputedService;
import cn.com.infosouth.amas.modules.virtual.entity.InfoVersion;
import cn.com.infosouth.amas.modules.virtual.service.InfoAcTypeService;
import cn.com.infosouth.amas.modules.virtual.service.InfoVersionService;
import org.slf4j.Logger;

/**   
 * @ClassName:  DefaultIndexController   
 * @Description:TODO(默认首页)   
 * @author: zy
 * @date:   2018年3月14日 下午6:24:24   
 *     
 * @Copyright: 2018 Inc. All rights reserved. 
 * 注意：本内容仅限深圳科信南方技术有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Controller
@RequestMapping(value = "${adminPath}/defaultIndex/defaultIndex")
public class DefaultIndexController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private InfoDutyScheduleService infoDutyScheduleService;
	@Autowired
	private InfoVersionService infoVersionService;

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private InfoFlightService infoFlightService;
	
	@Autowired
	private InfoAcTypeService infoAcTypeService;
	
	@Autowired
	private InfoParameterComputedService infoParameterComputedService;
	
	private static String  static_acType_C900 = "AC700";
	private static String  static_acType_B737 = "AC737";
	private static String  static_acType_A320 = "AC320";
	private static String  static_acType_B777 = "AC777";
	private static String  static_acType_A330 = "AC330";
	
	
	/**   
	 * @Title: initAcType   
	 * @Description: TODO(初始化机型)   
	 * @param: @param acTypeListByAcTypeNo      
	 * @return: void      
	 * @throws   
	 */
	public void initAcType(List<Map<String,String>> acTypeListByAcTypeNo){
		for (int i = 0; i < acTypeListByAcTypeNo.size(); i++) {
			Map<String, String> map = acTypeListByAcTypeNo.get(i);
			if("1".equals(map.get("ac_type_no"))){
				static_acType_C900 = map.get("ac_tpye");
			}
			if("2".equals(map.get("ac_type_no"))){
				static_acType_B737 = map.get("ac_tpye");
			}
			if("3".equals(map.get("ac_type_no"))){
				static_acType_A320 = map.get("ac_tpye");
			}
			if("4".equals(map.get("ac_type_no"))){
				static_acType_B777 = map.get("ac_tpye");
			}
			if("5".equals(map.get("ac_type_no"))){
				static_acType_A330 = map.get("ac_tpye");
			}
		}
	}

	@RequiresPermissions("defaultIndex:defaultIndex:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		//机型列表
		List<Map<String,String>> acTypeListByAcTypeNo = infoAcTypeService.findacTpyeMapList();
		//初始化机型
		initAcType(acTypeListByAcTypeNo);
		
		List<Map<String, String>> infoDutyScheduleList_CRJ900 = infoDutyScheduleService.findInfoDutyScheduleMapList(static_acType_C900);
		List<Map<String, String>> infoDutyScheduleList_B737 = infoDutyScheduleService.findInfoDutyScheduleMapList(static_acType_B737);
		List<Map<String, String>> infoDutyScheduleList_A320 = infoDutyScheduleService.findInfoDutyScheduleMapList(static_acType_A320);
		List<Map<String, String>> infoDutyScheduleList_B777 = infoDutyScheduleService.findInfoDutyScheduleMapList(static_acType_B777);
		List<Map<String, String>> infoDutyScheduleList_A330 = infoDutyScheduleService.findInfoDutyScheduleMapList(static_acType_A330);
		
		
		
		InfoVersion infoVersion = new InfoVersion();
		List<InfoVersion> versionList = infoVersionService.findList(infoVersion);
		
		model.addAttribute("infoDutyScheduleList_CRJ900", infoDutyScheduleList_CRJ900);
		model.addAttribute("infoDutyScheduleList_B737", infoDutyScheduleList_B737);
		model.addAttribute("infoDutyScheduleList_A320", infoDutyScheduleList_A320);
		model.addAttribute("infoDutyScheduleList_B777", infoDutyScheduleList_B777);
		model.addAttribute("infoDutyScheduleList_A330", infoDutyScheduleList_A330);
		
		
		//qar数量
		String qarCount = infoFlightService.findFlightCount();
		model.addAttribute("qarCount", qarCount);
		
		//qar数量
		String modelCount = infoParameterComputedService.findModelCount();
		model.addAttribute("modelCount", modelCount);
		
		//任务数量
		String jobCount = infoDutyScheduleService.findJobCount();
		model.addAttribute("jobCount", jobCount);
		
		model.addAttribute("versionList", versionList);
		
		//登录管理员名
		String login_admin = UserUtils.getUser().getLoginName();
		User currLoginUser = systemService.getUserByLoginName(login_admin);
		//是否显示首页图表“设置”选项权限
		String isShowAdminSettingFlag = "0";
		if("1".equals(currLoginUser.getUserType())){
			isShowAdminSettingFlag = "1";
		}	
		model.addAttribute("isShowAdminSettingFlag", isShowAdminSettingFlag);
		
		//查询第一页的任务，top10 
		List<Map<String, String>> infoDutyScheduleMapListTop10 = infoDutyScheduleService.findInfoDutyScheduleMapListTop10();
		JSONArray infoDutyScheduleMapListTop10JsonArr = JSONArray.parseArray(JSONObject.toJSONString(infoDutyScheduleMapListTop10));
		model.addAttribute("jobsTop10JsonArr", infoDutyScheduleMapListTop10JsonArr);
				
		//任务列表url
		PropertiesLoader propertiesLoader = new PropertiesLoader("/defaultIndexConfig.properties");
		String marquee_scroll_text_url = propertiesLoader.getProperty("marquee_scroll_text_url");
		model.addAttribute("marquee_scroll_text_url", marquee_scroll_text_url);
		
		PropertiesLoader config = new PropertiesLoader("numberone.properties");
		String config_debug = config.getProperty("local.debug");
		if(config_debug.trim().equals("1"))
		{
			model.addAttribute("local_debug", "1");
		}
		else {
			model.addAttribute("local_debug", "0");
		}
		
		//model.addAttribute("acTypeListByAcTypeNo", acTypeListByAcTypeNo);
		String acTypeListByAcTypeNoStr = "";
		acTypeListByAcTypeNoStr = static_acType_C900+","+static_acType_B737+","+static_acType_A320+","+static_acType_B777+","+static_acType_A330;
		
		model.addAttribute("acType_row1", static_acType_C900);
		model.addAttribute("acType_row2", static_acType_B737);
		model.addAttribute("acType_row3", static_acType_A320);
		model.addAttribute("acType_row4", static_acType_B777);
		model.addAttribute("acType_row5", static_acType_A330);
		
		model.addAttribute("acTypeListByAcTypeNoStr", acTypeListByAcTypeNoStr);
		return "modules/defaultIndex/defaultIndexList";
	}
	

	@RequiresPermissions("defaultIndex:defaultIndex:view")
	@RequestMapping(value = {"infoDutyScheduleList", ""})
	public String infoDutyScheduleList(InfoDutySchedule infoDutySchedule, HttpServletRequest request, HttpServletResponse response, Model model) {
		String userName = UserUtils.getUser().getLoginName();
		//按创建人排序,当前用户优先
		String orderBy =  "(case when a.create_by='" + userName + "' then 1 ELSE IFNULL(a.create_by,2) END),a.update_date DESC";
		Page<InfoDutySchedule> page = new Page<InfoDutySchedule>(request, response);
		page.setOrderBy(orderBy);
		page = infoDutyScheduleService.findPage(page, infoDutySchedule); 
		InfoVersion infoVersion = new InfoVersion();
		List<InfoVersion> versionList = infoVersionService.findList(infoVersion);
		model.addAttribute("versionList", versionList);
		model.addAttribute("page", page);
		HttpReqUriUtils httpReqUriUtils = new HttpReqUriUtils();
		httpReqUriUtils.setGetJobs_Status_Uri();
		model.addAttribute("getJobs_Status_Uri", httpReqUriUtils.getGetJobs_Status_Uri());
		return "modules/defaultIndex/defaultIndex_infoDutyScheduleList";
	}
	
	/*public List<InfoAcType> getAcTypeByAcTypeNo(){
		
	}*/
	
}

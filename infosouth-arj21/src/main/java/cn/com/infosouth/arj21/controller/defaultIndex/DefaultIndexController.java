package cn.com.infosouth.arj21.controller.defaultIndex;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.infosouth.arj21.domain.InfoVersion;
import cn.com.infosouth.arj21.service.IInfoAcTypeService;
import cn.com.infosouth.arj21.service.IInfoDutyScheduleService;
import cn.com.infosouth.arj21.service.IInfoFlightService;
import cn.com.infosouth.arj21.service.IInfoVersionService;
import cn.com.infosouth.common.core.controller.BaseController;

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
@RequestMapping(value = "/defaultIndex/defaultIndex")
public class DefaultIndexController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IInfoDutyScheduleService infoDutyScheduleService;
	@Autowired
	private IInfoVersionService infoVersionService;

	@Autowired
	private IInfoFlightService infoFlightService;
	
	@Autowired
	private IInfoAcTypeService infoAcTypeService;
	
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
		
		//model数量
//		String modelCount = infoParameterComputedService.findModelCount();
		model.addAttribute("modelCount", "0");
		
		//任务数量
		String jobCount = infoDutyScheduleService.findJobCount();
		model.addAttribute("jobCount", jobCount);
		
		model.addAttribute("versionList", versionList);
		
		 
		
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
	
	 
	
}

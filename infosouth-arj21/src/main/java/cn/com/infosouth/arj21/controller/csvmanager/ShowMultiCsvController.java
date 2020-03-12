/**
 * Copyright &copy; 2013-2017 <a href="https://www.infosouth.com.cn/">Infosouth</a> All rights reserved.
 */
package cn.com.infosouth.arj21.controller.csvmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.infosouth.arj21.domain.InfoFlight;
import cn.com.infosouth.arj21.domain.InfoVersion;
import cn.com.infosouth.arj21.domain.ShowMultiCsv;
import cn.com.infosouth.arj21.service.IInfoAcTypeService;
import cn.com.infosouth.arj21.service.IInfoChoosedMulticsvParamsTemplateService;
import cn.com.infosouth.arj21.service.IInfoVersionService;
import cn.com.infosouth.arj21.service.IShowMultiCsvService;
import cn.com.infosouth.arj21.utils_oneselef.Page;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.json.JSONObject;
import cn.com.infosouth.framework.util.ShiroUtils;
import cn.com.infosouth.system.domain.SysUser;

/**
 * 译码工程值查看-多个csv图形展示Controller
 * 
 * @author zy
 * @version 2018-03-26
 */
@Controller
@RequestMapping(value = "/csvmanager/showMultiCsv")
public class ShowMultiCsvController extends BaseController {

	private String prefix = "arj21/csvmanager/showcsv";

	@Resource
	private IInfoChoosedMulticsvParamsTemplateService choosedMulticsvParamsTemplateService;
	@Resource
	private IInfoVersionService versionService;
	@Resource
	private IInfoAcTypeService infoAcTypeService;
	@Resource
	private IShowMultiCsvService showMultiCsvService;
	
	@RequiresPermissions("csvmanager:showMultiCsv:view")
	@RequestMapping("showMultiCsvIndex")
	public String _import(InfoFlight infoFlight, Model model) {
		return prefix + "/showMultiCsvIndex3";
	}

	@RequiresPermissions("csvmanager:showMultiCsv:view")
	@RequestMapping("showMultiCsvList")
	public String showMultiCsvList(ShowMultiCsv showMultiCsv, HttpServletRequest request, HttpServletResponse response,
			Model model, String choosed_versionId_by_choosed_versionId) {
		
		/*
		 * //左边参数页面手动设置的构型id String pod = request.getParameter("queryPod"); if(pod!=null
		 * && !pod.trim().isEmpty()){ showMultiCsv.setPod(pod.trim()); }
		 * 
		 * String poa = request.getParameter("queryPoa"); if(poa!=null &&
		 * !poa.trim().isEmpty()){ showMultiCsv.setPoa(poa.trim()); }
		 * 
		 * 
		 * String versionId_handled = request.getParameter("versionId_handled");
		 * 
		 * List<Map<String, Object>> versionIdList = new ArrayList<Map<String,
		 * Object>>(); try { versionIdList = versionService.findInfoVersionMapList();
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); logger.error("showMultiCsvList方法：查询构型失败"); }
		 * //默认显示参数版本（构型） Integer defaultShowVersionId = 0; Map<String, Object>
		 * defaultVersionIdMap = versionIdList.get(0); defaultShowVersionId =
		 * (Integer)defaultVersionIdMap.get("id");
		 * //如果choosed_versionId_by_choosed_versionId不为空，表示手动切换构型，反之，表示 默认加载页面
		 * if(!"".equals(choosed_versionId_by_choosed_versionId)&&
		 * choosed_versionId_by_choosed_versionId!=null&&!"undefined".equals(
		 * choosed_versionId_by_choosed_versionId)){
		 * showMultiCsv.setVersionId(choosed_versionId_by_choosed_versionId);
		 * showMultiCsv.setArn(null); }else{
		 * if(!"".equals(versionId_handled)&&versionId_handled!=null&&!"undefined".
		 * equals(versionId_handled)){ showMultiCsv.setVersionId(versionId_handled);
		 * }else{ showMultiCsv.setVersionId(String.valueOf(defaultShowVersionId)); }
		 * 
		 * }
		 * 
		 * 
		 * //机型列表 List<String> acTypeList =
		 * infoAcTypeService.findacTpyeList2(showMultiCsv.getVersionId());
		 * //根据构型（版本id）查询的机型 StringBuffer acTypeB = new StringBuffer(); for(String
		 * acTypeEach : acTypeList){ acTypeB.append(acTypeEach+","); } //机型查询条件是否为空
		 * String acTypeConditionEmptyFlag = "0";
		 * 
		 * //如果机型下拉框没有勾选，则使用从构型查出来的机型
		 * if("".equals(showMultiCsv.getAcType())||showMultiCsv.getAcType()==null){
		 * showMultiCsv.setAcType(acTypeB.toString()); //如果该参数版本没有查询到对应的机型，则机型置空
		 * if("".equals(acTypeB.toString())||acTypeB.toString()==null){
		 * showMultiCsv.setAcType("null"); } acTypeConditionEmptyFlag = "1"; }else{
		 * //如果机型不为空，如果choosed_versionId_by_choosed_versionId 有合理的值，则强制选择该构型下面的机型
		 * if(!"".equals(choosed_versionId_by_choosed_versionId)&&
		 * choosed_versionId_by_choosed_versionId!=null&&!"undefined".equals(
		 * choosed_versionId_by_choosed_versionId)){
		 * showMultiCsv.setAcType(acTypeB.toString()); //如果该参数版本没有查询到对应的机型，则机型置空
		 * if("".equals(acTypeB.toString())||acTypeB.toString()==null){
		 * showMultiCsv.setAcType("null"); } showMultiCsv.setArn(null);
		 * acTypeConditionEmptyFlag = "1"; } }
		 * 
		 * JSONObject queryConditionsStrJson = new JSONObject();
		 * queryConditionsStrJson.put("acTypeConditionEmptyFlag",
		 * acTypeConditionEmptyFlag); queryConditionsStrJson.put("queryAcTypeStr",
		 * showMultiCsv.getAcType()); queryConditionsStrJson.put("queryAcRegStr",
		 * showMultiCsv.getArn()); queryConditionsStrJson.put("queryVersionIdStr",
		 * showMultiCsv.getVersionId()); queryConditionsStrJson.put("queryPod",
		 * showMultiCsv.getPod()); queryConditionsStrJson.put("queryPoa",
		 * showMultiCsv.getPoa());
		 * 
		 * //---------------------------------------------------------------------------
		 * ------ SysUser sysUser = ShiroUtils.getSysUser();
		 * 
		 * 
		 * Page<ShowMultiCsv> page = showMultiCsvService.findPage(10,1,showMultiCsv);
		 * model.addAttribute("versionIdList", versionIdList);
		 * model.addAttribute("page", page); model.addAttribute("acTypeList",
		 * acTypeList); model.addAttribute("queryConditionsStrJson",
		 * queryConditionsStrJson);
		 */
		return prefix + "/showMultiCsvList3";
	}
	
	@RequiresPermissions("csvmanager:showMultiCsv:view")
	@RequestMapping("showMultiColumnsNameList")
	public String showMultiColumnsNameList(Model model) {
		String loginName = ShiroUtils.getLoginName();//获得登录名
		List<InfoVersion> versionList = versionService.findList(new InfoVersion());
		//获得登录者已经设置过的参数的模板
//		InfoChoosedMulticsvParamsTemplate paramsTlateCondition = new InfoChoosedMulticsvParamsTemplate();
//		paramsTlateCondition.setLoginAdmin(loginName);
//		List<InfoChoosedMulticsvParamsTemplate> paramTemplateList = choosedMulticsvParamsTemplateService.findList(paramsTlateCondition);
		//默认显示参数版本（构型）
		int defaultShowVersionId = 0;
		if(versionList != null && versionList.size() > 0) {
			defaultShowVersionId = versionList.get(0).getId().intValue();
		}
		model.addAttribute("versionList", versionList);
		model.addAttribute("defaultShowVersionId", defaultShowVersionId);
//		model.addAttribute("paramTemplateList", paramTemplateList);
		return prefix + "/showMultiColumnsName3";
	}
	
	/**   
	 * @Title: getVersionIdList   
	 * @Description: TODO(获取构型（versionId）)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @param model
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>      
	 * @throws   
	 */
	@RequestMapping(value = { "getVersionIdList" })
	@ResponseBody
	public Map<String, Object> getVersionIdList(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", "1001");
		returnMap.put("data", "");
		
		List<Map<String, Object>> versionIdList = new ArrayList<Map<String, Object>>();
		try {
			versionIdList = versionService.findInfoVersionMapList();
			returnMap.put("data", versionIdList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMap.put("status", "1002");
			returnMap.put("data", "查询机型异常，请稍后再试！");
		}
		return returnMap;
	}
	
	
	
	
	
	
}

/**
 * Copyright &copy; 2013-2017 <a href="https://www.infosouth.com.cn/">Infosouth</a> All rights reserved.
 */
package cn.com.infosouth.arj21.controller.parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.infosouth.arj21.common.CommonResult;
import cn.com.infosouth.arj21.domain.InfoChoosedMulticsvParamsTemplate;
import cn.com.infosouth.arj21.service.IInfoChoosedMulticsvParamsTemplateService;
import cn.com.infosouth.arj21.service.IInfoFlightService;
import cn.com.infosouth.arj21.utils_oneselef.IdGeneratorUtils;
import cn.com.infosouth.arj21.utils_oneselef.VeryFieldEmptyUtils;
import cn.com.infosouth.common.config.Global;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.framework.util.ShiroUtils;

/**
 * 多航班查看-勾选参数模板Controller
 * @author zy
 * @version 2018-03-29
 */
@Controller
@RequestMapping(value = "/arj21/infoChoosedMulticsvParamsTemplate")
public class InfoChoosedMulticsvParamsTemplateController extends BaseController{

	@Autowired
	private IInfoChoosedMulticsvParamsTemplateService infoChoosedMulticsvParamsTemplateService;
	
	@Autowired
	private IInfoFlightService infoFlightService;
	
	
	/**   
	 * @Title: saveParamsTemplate   
	 * @Description: TODO(保存勾选过的参数模板信息)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @param model
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>      
	 * @throws   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "saveParamsTemplate" })
	@ResponseBody
	public Map<String, Object> saveParamsTemplate(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", "1001");
		returnMap.put("data", "");
		String choosed_versionId = request.getParameter("choosed_versionId");
		String choosed_templateId = request.getParameter("choosed_templateId");
		String choosedParams = request.getParameter("choosedParams");
		//登录管理员名
		String login_admin = ShiroUtils.getLoginName();
		String templateName = request.getParameter("templateName");

		InfoChoosedMulticsvParamsTemplate infoChoosedParamsTemplate = new InfoChoosedMulticsvParamsTemplate();		
		infoChoosedParamsTemplate.setLoginAdmin(login_admin);
		infoChoosedParamsTemplate.setTemplatename(templateName);
		infoChoosedParamsTemplate.setId(choosed_templateId);
		infoChoosedParamsTemplate.setVersionid(choosed_versionId);
		
		
		List<InfoChoosedMulticsvParamsTemplate> templatesList = new ArrayList<InfoChoosedMulticsvParamsTemplate>();
		try {
			templatesList = infoChoosedMulticsvParamsTemplateService.findList(infoChoosedParamsTemplate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("保存参数模板异常，请稍后再试！");
			returnMap.put("status", "1002");
			returnMap.put("data", "保存参数模板异常，请稍后再试！");
			return returnMap;
		}
		if (templatesList.size() == 1) {
			if("".equals(choosed_templateId)||choosed_templateId==null||"null".equals(choosed_templateId)||"undefined".equals(choosed_templateId)){
				returnMap.put("status", "1003");
				returnMap.put("data", "已有同名模板，请重新命名");
				return returnMap;
			}
			// 如果infoDefaultindexParamset中包含id，则更新；反之，添加
			InfoChoosedMulticsvParamsTemplate infoChoosedParamsTemplateOld = templatesList.get(0);
			//
			infoChoosedParamsTemplate.setId(infoChoosedParamsTemplateOld.getId());		
		}else{
			infoChoosedParamsTemplate.setId(IdGeneratorUtils.uuid());
		}
		
		infoChoosedParamsTemplate.setChoosedParamsstr(choosedParams);

		infoChoosedMulticsvParamsTemplateService.save(infoChoosedParamsTemplate);
		returnMap.put("return_choosed_templateId", infoChoosedParamsTemplate.getId());
		return returnMap;
	}
	
	
	/*
	 * @RequiresPermissions("csvmanager:infoChoosedMulticsvParamsTemplate:edit")
	 * 
	 * @RequestMapping(value = "delete")
	 * 
	 * @ResponseBody public String delete(InfoChoosedMulticsvParamsTemplate
	 * infoChoosedMulticsvParamsTemplate, RedirectAttributes redirectAttributes) {
	 * infoChoosedMulticsvParamsTemplateService.delete(
	 * infoChoosedMulticsvParamsTemplate); addMessage(redirectAttributes,
	 * "删除多航班查看-勾选参数模板成功"); return "redirect:"+Global.getAdminPath()+
	 * "/csvmanager/infoChoosedMulticsvParamsTemplate/?repage"; }
	 */
	
	
	
	
	/**   
	 * @Title: getParamsTemplateByCondition   
	 * @Description: TODO(根据条件查询设置过的选中参数模板)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @param model
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>      
	 * @throws   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "getParamsTemplateByCondition" })
	@ResponseBody
	public Map<String, Object> getParamsTemplateByCondition(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", "1001");
		returnMap.put("data", "");

		String choosed_versionId = request.getParameter("choosed_versionId");
		String return_choosed_templateId = request.getParameter("return_choosed_templateId");

		//登录管理员名
		String login_admin = ShiroUtils.getLoginName();

		InfoChoosedMulticsvParamsTemplate infoChoosedParamsTemplate = new InfoChoosedMulticsvParamsTemplate();		
		infoChoosedParamsTemplate.setLoginAdmin(login_admin);
		if(choosed_versionId!=null&&!"".equals(choosed_versionId)&&!"undefined".equals(choosed_versionId))
			infoChoosedParamsTemplate.setVersionid(choosed_versionId);
		if(return_choosed_templateId!=null&&!"".equals(return_choosed_templateId)&&!"undefined".equals(return_choosed_templateId))
			infoChoosedParamsTemplate.setId(return_choosed_templateId);
		
		List<Map<String, String>> infoChoosedParamsTemplateList = new ArrayList<Map<String, String>>();
		try {
			infoChoosedParamsTemplateList = infoChoosedMulticsvParamsTemplateService.findChoosedTemplateMapListByCondition(infoChoosedParamsTemplate);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("status", "1002");
			returnMap.put("data", "保存参数模板异常，请稍后再试！");
			return returnMap;
		}
		JSONArray infoChoosedParamsTemplateJsonArr = JSONArray.parseArray(JSONObject.toJSONString(infoChoosedParamsTemplateList));
		returnMap.put("data", infoChoosedParamsTemplateJsonArr);
		return returnMap;
	}
	
	/**   
	 * @Title: getParamsTemplateById   
	 * @Description: TODO(通过模板id，获取参数模板信息)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @param model
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>      
	 * @throws   
	 */
	@RequestMapping(value = { "getParamsTemplateById" })
	@ResponseBody
	public Map<String, Object> getParamsTemplateById(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", "1001");
		returnMap.put("data", "");

		String choosed_templateId = request.getParameter("choosed_templateId");
		try {
			InfoChoosedMulticsvParamsTemplate infoChoosedParamsTemplate = infoChoosedMulticsvParamsTemplateService.selectById(choosed_templateId);
			JSONObject infoChoosedParamsTemplateJson = new JSONObject();
			infoChoosedParamsTemplateJson.put("choosed_templateId", choosed_templateId);
			infoChoosedParamsTemplateJson.put("templateName", infoChoosedParamsTemplate.getTemplatename());
			infoChoosedParamsTemplateJson.put("arn", infoChoosedParamsTemplate.getArn());
			infoChoosedParamsTemplateJson.put("acType", infoChoosedParamsTemplate.getActype());
			infoChoosedParamsTemplateJson.put("choosed_paramsStr", infoChoosedParamsTemplate.getChoosedParamsstr());
			infoChoosedParamsTemplateJson.put("loginAdmin", infoChoosedParamsTemplate.getLoginAdmin());
			infoChoosedParamsTemplateJson.put("versionidByTemplate", infoChoosedParamsTemplate.getVersionid());
			returnMap.put("data", infoChoosedParamsTemplateJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMap.put("status", "1002");
			returnMap.put("data", "获取参数模板信息异常，请稍后再试");
		}
		return returnMap;
	}
	
	/**   
	 * @Title: checkMatchParamsTemplate   
	 * @Description: TODO(根据条件，查询匹配的模板)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @param model
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>      
	 * @throws   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "checkMatchParamsTemplate" })
	@ResponseBody
	public Map<String, Object> checkMatchParamsTemplate(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", "1001");
		returnMap.put("data", "");
		String loginAdminByTemplate = request.getParameter("loginAdminByTemplate");
		
		//登录管理员名
		String login_admin = ShiroUtils.getLoginName();
		try {
			boolean matchFlag = true;			
			if(!loginAdminByTemplate.equals(login_admin)){
				matchFlag = false;
			}
			if(!matchFlag){
				returnMap.put("status", "1002");	
				returnMap.put("data", "该模板不匹配选择的csv文件，请另选模板");
			}
					
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("没有查询到航班csv信息");
			returnMap.put("status", "1003");
			returnMap.put("data", "没有查询到航班csv信息");
			return returnMap;
		}
	
	}
	

	
	
}
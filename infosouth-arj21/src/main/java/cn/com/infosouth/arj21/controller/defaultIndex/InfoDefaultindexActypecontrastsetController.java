/**
 * Copyright &copy; 2013-2017 <a href="https://www.infosouth.com.cn/">Infosouth</a> All rights reserved.
 */
package cn.com.infosouth.arj21.controller.defaultIndex;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.infosouth.arj21.service.IInfoDefaultindexActypecontrastsetService;
import cn.com.infosouth.common.core.controller.BaseController;
 

/**
 * 默认首页-机型数据对比设置Controller
 * @author zy
 * @version 2018-03-20
 */
@Controller
@RequestMapping(value = "/arj21/defaultIndex/infoDefaultindexActypecontrastset")
public class InfoDefaultindexActypecontrastsetController extends BaseController{

	@Autowired
	private IInfoDefaultindexActypecontrastsetService infoDefaultindexActypecontrastsetService;
	
	/**   
	 * @Title: getParamsStatisticsResult   
	 * @Description: TODO(获取参数统计结果，用于首页显示和打开模态框显示)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @param model
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws   
	 */
	@RequiresPermissions("defaultIndex:defaultIndex:view")
	@RequestMapping(value = {"getParamsStatisticsResult", ""})
	@ResponseBody
	public Map<String, Object> getParamsStatisticsResult(HttpServletRequest request, HttpServletResponse response, Model model){

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", "1001");
		returnMap.put("data", "");

		// 查询数据库是否有相同设置
		List<Map<String, String>> paramsetList = new ArrayList<Map<String, String>>();
		JSONArray paramsetListJsonArr = new JSONArray();
		try {
			paramsetList = infoDefaultindexActypecontrastsetService.findActypecontrastSetData();
			paramsetListJsonArr = JSONArray.parseArray(JSONObject.toJSONString(paramsetList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("机型数据对比-getParamsStatisticsResult方法：查询异常，请稍后再试");
			returnMap.put("status", "1002");
			returnMap.put("data", "表格查询数据异常，请稍后再试");
			return returnMap;
		}
		// 如果数据库中有相同的设置，则更新数据库中原有的
		//-------------------
		returnMap.put("data", paramsetListJsonArr);
		return returnMap;
	}
	
	
	
	
	
	 
}
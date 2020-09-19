package com.ruoyi.dfm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.dfm.dao.DataAnalysisDAO;
import com.ruoyi.dfm.pojo.DataAnalysisBean;
import com.ruoyi.dfm.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Component
public class DoeAnalysisService {
	private static final Logger log = LoggerFactory.getLogger(DoeAnalysisService.class);
	private static final Integer SUCCESS_CODE = new Integer(20);

	@Value("${api.root.url}")
	protected String apiRootUrl;

	@Value("${api.doe.GetDataByKeyParam}")
	private String getDataByKeyParamUrl;

	@Value("${api.doe.GetDataByRework}")
	private String getDataByReworkUrl;

	@Value("${api.doe.Calculate}")
	private String calculateUrl;

	@Value("${api.doe.Save}")
	private String saveUrl;

	public JSONArray listByKeyParam(String productname, String version){
		String apiUrl = apiRootUrl + getDataByKeyParamUrl;
		String param = "productname=" + productname +"&version=" + version;
		log.info("request remote api, url={}, param={}", apiUrl, param);
		String result = HttpUtils.sendGet(apiUrl, param);
		log.info("response remote api, url={}, param={}, result={}", apiUrl, param, result);
		JSONObject resultObj = JSON.parseObject(result);
		if(null != resultObj && SUCCESS_CODE.equals(resultObj.getInteger("code"))) {
			JSONArray data = resultObj.getJSONArray("data");
			if(CollectionUtils.isEmpty(data)) {
				return data;
			}
			JSONArray newJSONArray = new JSONArray(data.size());
			for(Object obj : data) {
				JSONObject jsonObj = ((JSONObject)obj);
				String defecttypes = jsonObj.getString("defecttype");
				if(StringUtils.isEmpty(defecttypes)) {
					newJSONArray.add(obj);
				} else {
					String[] defecttypeArr = defecttypes.split(",");
					for (String defecttype : defecttypeArr) {
						JSONObject newObj = new JSONObject(jsonObj);
						newObj.put("defecttype", defecttype);
						newJSONArray.add(newObj);
					}
				}
			}
			return newJSONArray;
		} else {
			log.error("reponse result failed. url={}, param={}, result={}", apiUrl, param, result);
		}
		return new JSONArray();
	}

	public JSONArray listByRework(String productname, String version){
		String apiUrl = apiRootUrl + getDataByReworkUrl;
		String param = "productname=" + productname +"&version=" + version;
		log.info("request remote api, url={}, param={}", apiUrl, param);
		String result = HttpUtils.sendGet(apiUrl, param);
		log.info("response remote api, url={}, param={}, result={}", apiUrl, param, result);
		JSONObject resultObj = JSON.parseObject(result);
		if(null != resultObj && SUCCESS_CODE.equals(resultObj.getInteger("code"))) {
			JSONArray data = resultObj.getJSONArray("data");
			return data;
		} else {
			log.error("reponse result failed. url={}, param={}, result={}", apiUrl, param, result);
		}
		return new JSONArray();
	}
	
}

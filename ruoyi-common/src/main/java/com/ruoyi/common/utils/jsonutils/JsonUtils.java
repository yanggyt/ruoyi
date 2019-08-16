package com.ruoyi.common.utils.jsonutils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 金操作工具类
 *
 * @author solo
 * @date 2019/08/16.
 **/
public class JsonUtils {


    /**
     * 判断是不是ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    /**
     * 判断一个字符串是不是json格式
     *
     * @param jsonString
     * @return
     */
    public static JSONObject isGoodJson(String jsonString) {
        try {
            if (StringUtils.isEmpty(jsonString)) {
                return null;
            }
            return (JSONObject) JSONObject.parse(jsonString);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 判断一个字符串是不是json格式
     *
     * @param jsonString
     * @return
     */
    public static boolean isJson(String jsonString) {
        try {
            if (StringUtils.isEmpty(jsonString)) {
                return false;
            }
            JSONObject.parse(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

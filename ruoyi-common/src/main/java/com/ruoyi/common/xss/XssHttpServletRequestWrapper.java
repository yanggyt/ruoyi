package com.ruoyi.common.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * XSS过滤处理
 * 
 * @author ruoyi
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    /**
     * @param request
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name)
    {
        String[] values = super.getParameterValues(name);
        if (values != null)
        {
            int length = values.length;
            String[] escapseValues = new String[length];
            for (int i = 0; i < length; i++)
            {
                // 防xss攻击和过滤前后空格
                escapseValues[i] = Jsoup.clean(values[i], Whitelist.relaxed()).trim();
                //zjj-自定义规则及js脚本注入过滤
                escapseValues[i] = filterXSS(escapseValues[i]);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }

    /**
     *@描述  过滤getParameter中xss攻击
     *@参数  [name]
     *@返回值  java.lang.String[]
     *@创建人  zhangjingjing
     *@创建时间  2019/3/22
     *@修改人和其它信息
     */
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        String result = Jsoup.clean(value, Whitelist.relaxed()).trim();
        //zjj-自定义规则及js脚本注入过滤
        result = filterXSS(result);
        return result;

    }
    /**
     *@描述  过滤getParameterMap中xss攻击
     *@参数  [name]
     *@返回值  java.lang.String[]
     *@创建人  zhangjingjing
     *@创建时间  2019/3/22
     *@修改人和其它信息
     */
    public Map<String, String[]> getParameterMap() {
        Map properties = super.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name;
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                returnMap.put(name, "");
            }else if(valueObj instanceof String[]){
                String[] values = (String[]) valueObj;
                int length = values.length;
                String[] escapseValues = new String[length];
                for (int i = 0; i < length; i++)
                {
                    // 防xss攻击和过滤前后空格
                    String pValue = values[i];
                    //System.out.println("当前map-xss过滤前参数值:"+pValue);
                    //zjj-jsoup过滤html标签
                    escapseValues[i] = Jsoup.clean(pValue, Whitelist.relaxed()).trim();
                    //zjj-自定义规则及js脚本注入过滤
                    escapseValues[i] = filterXSS(escapseValues[i]);
                    //System.out.println("当前map-xss过滤后参数值:"+escapseValues[i]);
                }
                returnMap.put(name, escapseValues);
            }else{
                String value = valueObj.toString();
                if (value == null) {
                    return null;
                }
                String result = Jsoup.clean(value, Whitelist.relaxed()).trim();
                //zjj-自定义规则及js脚本注入过滤
                result = filterXSS(result);
                returnMap.put(name, result);
            }
        }
        return returnMap;
    }


    private String filterXSS(String value) {
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

}
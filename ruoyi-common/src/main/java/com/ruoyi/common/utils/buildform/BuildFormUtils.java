package com.ruoyi.common.utils.buildform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 构建一个form表单提交
 */
public class BuildFormUtils {

    /**
     * 构造提交表单HTML数据
     *
     * @param sParaTemp     请求参数数组
     * @param gateway       网关地址
     * @param strMethod     提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildForm(Map<String, String> sParaTemp, String gateway, String strMethod,
                                   String strButtonName) {
        // 待请求参数数组
        List<String> keys = new ArrayList<String>(sParaTemp.keySet());
        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>正在请求处理中</title>" +
                "<script>function myformSub(){mysubmit.submit();} </script></head><body onload=\"myformSub();\">");
        sbHtml.append("<form id=\"mysubmit\" name=\"mysubmit\" action=\"" + gateway + "\" method=\""
                + strMethod + "\">");
        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sParaTemp.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        return sbHtml.toString();
    }
}

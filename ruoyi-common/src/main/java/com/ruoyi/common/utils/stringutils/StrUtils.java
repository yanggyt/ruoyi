package com.ruoyi.common.utils.stringutils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 */
public class StrUtils {
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符



    public static void main(String[] args) throws Exception {
        String strhours = "23232323";
        System.out.println(hideStr(strhours, 2,3));
    }


    /**
     * @param htmlStr
     * @return
     *  删除script标签
     */
    public static String delScriptTag(String htmlStr) {
        if(StringUtils.isEmpty(htmlStr)){
            return "";
        }
        String regEx_script0 = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regEx_script1 = "<script[^>]*?>[\\s\\S]*?"; // 定义script的正则表达式
        String regEx_script2 = "<[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        Pattern p_script0 = Pattern.compile(regEx_script0, Pattern.CASE_INSENSITIVE);
        Matcher m_script0 = p_script0.matcher(htmlStr);
        htmlStr = m_script0.replaceAll(""); // 过滤script标签

        Pattern p_script1 = Pattern.compile(regEx_script1, Pattern.CASE_INSENSITIVE);
        Matcher m_script1 = p_script1.matcher(htmlStr);
        htmlStr = m_script1.replaceAll(""); // 过滤script标签

        Pattern p_script2 = Pattern.compile(regEx_script2, Pattern.CASE_INSENSITIVE);
        Matcher m_script2 = p_script2.matcher(htmlStr);
        htmlStr = m_script2.replaceAll(""); // 过滤script标签
        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * @param htmlStr
     * @return
     *  删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        if(StringUtils.isEmpty(htmlStr)){
            return "";
        }
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 从字符串末尾截取字符串
     *
     * @param str
     * @param length
     * @return
     */
    public static String subStringByEnd(String str, Integer length) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() < length) {
            return str;
        }
        return str.substring(str.length() - length, str.length());

    }

    /**
     * 448      * 替换字符串
     * 449      * @param args
     * 450      * @throws IOException
     * 451
     */
    public static String strReplaceAll(String name,String str1,String str2) {
        if (name != null && !"".equals(name)) {
            if(StringUtils.isBlank(str2)){
                str2 = "";
            }
            return name.replaceAll(str1, str2);
        } else {
            return name;
        }
    }


    /**
     * 安全性隐藏，字符串指定位置中间的字符通过*代替
     * @param str
     * @param front
     * @param end
     * @return
     */
    public static String hideStr(String str, int front, int end) {
        //字符串不能为空
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        //需要截取的长度不能大于身份证号长度
        if ((front + end) > str.length()) {
            return str;
        }
        //需要截取的不能小于0
        if (front < 0 || end < 0) {
            return str;
        }
        //计算*的数量
        int asteriskCount = str.length() - (front + end);
        StringBuilder asteriskStr = new StringBuilder();
        for (int i = 0; i < asteriskCount; i++) {
            asteriskStr.append("*");
        }
        String regex = "(\\w{" + String.valueOf(front) + "})(\\w+)(\\w{" + String.valueOf(end) + "})";
        return str.replaceAll(regex, "$1" + asteriskStr + "$3");
    }

}

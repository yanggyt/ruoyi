package com.ruoyi.common.utils.regexutil;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author solo
 * @date 2019/08/16
 */
public class RegexUtil {
    public static final String SAFE_PASS = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";

    /**
     * 判断一个字符串是不是在另一个字符串中
     *
     * @param
     * @return
     */
    public static boolean isStrindexOf(String str, String strof) {
        if (str.indexOf(strof) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为数字类型
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        String pattern = "^[0-9]*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }


    /**
     * 判断金额格式
     * @param str
     * @return
     */
    public static boolean isMoney(String str){
        String reg = "^[0-9]+([.]{1}[0-9]{1,2})?$";
        if(str.matches(reg)){
            if(Double.valueOf(str)>0){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 是否有特殊字符
     * @param str
     * @return
     */
    public static boolean isSpecialString(String str){
        if(StringUtils.isEmpty(str)){
            return true;
        }
        String reg = "^[\\u4E00-\\u9FA5a-zA-Z0-9]*$";
        Pattern r = Pattern.compile(reg);
        Matcher m = r.matcher(str);
        return !m.matches();
    }


    /**
     * 是否有特殊字符
     * @param str
     * @return
     */
    public static boolean isSpecialString1(String str){
        String reg = "^[\\u4E00-\\u9FA5a-zA-Z0-9_【】]*$";
        Pattern r = Pattern.compile(reg);
        Matcher m = r.matcher(str);
        return m.matches();
    }


    /**
     * 是否有特殊字符
     * @param str
     * @return
     */
    public static boolean isSpecialAccount(String str){
        if(StringUtils.isEmpty(str)){
            return true;
        }
        String reg = "^[\\u4E00-\\u9FA5a-zA-Z0-9@._-]*$";
        Pattern r = Pattern.compile(reg);
        Matcher m = r.matcher(str);
        return !m.matches();
    }


    /**
     * 只能输入英文数字组合 长度在5-20之间
     * @param str
     * @return
     */
    public static boolean isEN(String str){
        if(StringUtils.isEmpty(str)){
            return false;
        }
        String reg = "^(?!([a-zA-Z]+|\\d+)$)[a-zA-Z\\d]{5,20}$";
        Pattern r = Pattern.compile(reg);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 只能输入英文和数字 长度在2-32之间
     * @param str
     * @return
     */
    public static boolean isEnNumber(String str){
        if(StringUtils.isEmpty(str)){
            return false;
        }
        String reg = "^[a-zA-Z0-9]{2,32}$";
        Pattern r = Pattern.compile(reg);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 只能输入英文和数字 长度在6-32之间  主要针对提现密码
     * @param str
     * @return
     */
    public static boolean isEnNumberForPassword(String str){
        if(StringUtils.isEmpty(str)){
            return false;
        }
        String reg = "^[a-zA-Z0-9]{6,32}$";
        Pattern r = Pattern.compile(reg);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 微信号格式数字字母下划线减号@和.
     * @param str
     * @return
     */
    public static boolean checkWeChatNOIsOk(String str){
        if(StringUtils.isBlank(str)){
            return false;
        }
        String reg = "^[a-zA-Z0-9_\\-@.]{5,20}$";
        Pattern r = Pattern.compile(reg);
        Matcher m = r.matcher(str);
        return m.matches();
    }



    public static void main(String[] args) {
        System.out.print(isEnNumberForPassword("23456"));
    }
}

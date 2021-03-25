package com.ruoyi.content.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class ToolUtil {
    /**
     * get filePath,ecs_setting key value
     *
     * @param file_path
     * @param key
     * @return value
     */
    final public static String getFilePathProperties(String file_path, String key) {
        Properties prop = new Properties();
        try {
            // 查找配置文件存放l路径
            String settingFilePath = ToolUtil.getProperties(file_path, "path");
            // 加载配置文件
            File f = new File(settingFilePath);
            InputStream is = new FileInputStream(f);
            prop.load(is);
            // 获取key值
            Enumeration enu = prop.keys();
            // 取出所有值放入map中
            while (enu.hasMoreElements()) {
                String tempkey = (String) enu.nextElement();
                if (tempkey.equals(key)) {
                    String value = new String(prop.getProperty(key).getBytes("ISO-8859-1"), "UTF-8");

                    return value;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取资源文件值
     *
     * @param file_path
     * @param key
     * @return
     */
    final public static String getProperties(String file_path, String key) {
        Properties prop = new Properties();
        try {
            prop.load(ToolUtil.class.getResourceAsStream(file_path));
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

//    /**
//     * 获取当前项目语言
//     *
//     * @return en/zh(不包含地区)
//     */
//    public static String getCurLanguage(HttpServletRequest request) {
//        Locale local = request.getLocale();
//        String language = local.getLanguage();
//        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
//        if (locale != null) {
//            language = locale.getLanguage().toString();
//        }
//        return language;
//    }

//    /**
//     * 获取当前项目语言
//     *
//     * @param value
//     * @return zh_CN(包含地区)
//     */
//    public static String getCurLocale(HttpServletRequest request) {
//        Locale local = request.getLocale();
//        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
//        if (locale != null) {
//            local = locale;
//        }
//
//        return local.toString();
//    }

    public static boolean isNotNull(String value) {
        return value != null && !"".equals(value) && !"null".equals(value.toLowerCase());
    }

//    /**
//     * 获取当前项目支持的语言
//     *
//     * @param request
//     * @return
//     */
//    public static String getSupportLocale(HttpServletRequest request) {
//        Locale local = request.getLocale();
//        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
//        if (locale != null) {
//            local = locale;
//        }
//
//        return local.toString();
//    }

    final public static String NullToBlank(String str) {
        if (null == str) {
            return "";
        }
        return str;
    }

    /**
     * 获取Excel下标值
     *
     * @param columnTitle
     * @return
     */
    final public static int getColumnIndex(String columnTitle) {
        if (columnTitle == null || columnTitle.length() < 1) {
            return 0;
        }
        columnTitle = columnTitle.toUpperCase();
        char[] chars = null;
        try {
            chars = columnTitle.toCharArray();
            if (chars == null || chars.length < 1) {
                return 0;
            }
            if (chars.length == 1) {
                return convertCharToIndex(chars[0]);
            }
            if (chars.length == 2) {
                int one = convertCharToIndex(chars[0]);
                one = one * 26;
                int two = convertCharToIndex(chars[1]);
                return one + two;
            } else {
                System.out.println(columnTitle + " 过长，转换列下标失败！");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取Excel下标值
     *
     * @param c
     * @return
     */
    final public static int convertCharToIndex(char c) {
        int index = 0;
        switch (c) {
            case 'A':
                index = 1;
                break;
            case 'B':
                index = 2;
                break;
            case 'C':
                index = 3;
                break;
            case 'D':
                index = 4;
                break;
            case 'E':
                index = 5;
                break;
            case 'F':
                index = 6;
                break;
            case 'G':
                index = 7;
                break;
            case 'H':
                index = 8;
                break;
            case 'I':
                index = 9;
                break;
            case 'J':
                index = 10;
                break;
            case 'K':
                index = 11;
                break;
            case 'L':
                index = 12;
                break;
            case 'M':
                index = 13;
                break;
            case 'N':
                index = 14;
                break;
            case 'O':
                index = 15;
                break;
            case 'P':
                index = 16;
                break;
            case 'Q':
                index = 17;
                break;
            case 'R':
                index = 18;
                break;
            case 'S':
                index = 19;
                break;
            case 'T':
                index = 20;
                break;
            case 'U':
                index = 21;
                break;
            case 'V':
                index = 22;
                break;
            case 'W':
                index = 23;
                break;
            case 'X':
                index = 24;
                break;
            case 'Y':
                index = 25;
                break;
            case 'Z':
                index = 26;
                break;
            default:
                index = 0;
                break;
        }
        return index;
    }

    /**
     * 数组转list
     *
     * @param array
     * @return
     */
    public static List transformArrayToList(Object[] array) {
        List<Object> list = new ArrayList<Object>();
        if (array == null || array.length <= 0) {
            return null;
        }
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * 补全空格占位 使用例子：TXT每行必须为500字符，不足500就空格占位
     *
     * @param str     字符串
     * @param lineNum 需要补充到几位
     * @return
     */
    public static String spaceToString(String str, int lineNum) {
        StringBuilder strb = new StringBuilder();
        strb.append(str);
        int num = str.length();
        if (lineNum > num) {
            for (int i = 0; i < (lineNum - num); i++) {
                strb.append(" ");
            }
        }
        return strb.toString();
    }

}

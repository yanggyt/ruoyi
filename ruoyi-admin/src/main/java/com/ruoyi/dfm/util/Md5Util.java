package com.ruoyi.dfm.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Md5加密工具类
 */
public class Md5Util {
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }
}

package com.ruoyi.common.utils.security;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Md5加密方法
 * 
 * @author solo
 */
public class Md5Utils
{
    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

    private static byte[] md5(String s)
    {
        MessageDigest algorithm;
        try
        {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        }
        catch (Exception e)
        {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[])
    {
        if (hash == null)
        {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++)
        {
            if ((hash[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s)
    {
        try
        {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        }
        catch (Exception e)
        {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    public static final int HASH_ITERATIONS = 3;

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public final static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ '1');
        }
        String s = new String(a);
        return s;
    }

    /**
     * MD532加密
     *
     * @param readyEncryptStr
     * @param saltKey
     * @return
     */
    public static String MD532(String readyEncryptStr, String saltKey) {
        if (StringUtils.isNotEmpty(readyEncryptStr) && saltKey != null) {
            readyEncryptStr = readyEncryptStr + saltKey;
            //Get MD5 digest algorithm's MessageDigest's instance.
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
                //Use specified byte update digest.
                md.update(readyEncryptStr.getBytes());
                //Get cipher text
                byte[] b = md.digest();
                //The cipher text converted to hexadecimal string
                StringBuilder su = new StringBuilder();
                //byte array switch hexadecimal number.
                for (byte aB : b) {
                    String haxHex = Integer.toHexString(aB & 0xFF);
                    if (haxHex.length() < 2) {
                        su.append("0");
                    }
                    su.append(haxHex);
                }
                return su.toString();
            } catch (NoSuchAlgorithmException e) {
                return "";
            }
        } else {
            return "";
        }
    }


    /**
     * shiro MD5 加密
     * @param password
     * @param userToken
     * @return
     */
    public static String ShiroMD5(String password, String userToken) {
        SimpleHash hash = new SimpleHash("md5", password,
                userToken, HASH_ITERATIONS);
        return hash.toHex();
    }

    public static String createToken(){
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        return secureRandomNumberGenerator.nextBytes().toHex();
    }

    public static void main(String[] args) {
        log.debug(new BigDecimal(832232).setScale(2, BigDecimal.ROUND_HALF_UP)+"");
    }
}

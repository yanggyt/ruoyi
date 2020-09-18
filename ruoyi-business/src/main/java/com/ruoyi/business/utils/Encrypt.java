package com.ruoyi.business.utils;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.Md5Utils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;

/* DES加密工具 */
public class Encrypt {
    //秘钥
    private static final String KEY = "ZF_desencrypt_2020";

    //加密解密工具
    private static Cipher enCipher = null;
    private static Cipher deCipher = null;

    //初始化加密工具
    static {
        try {
            int keyLen = 8;
            String key8 = Md5Utils.hash(KEY).substring(0, keyLen).toUpperCase();
            //C# MD5 ResultString Fix
            StringBuffer keys = new StringBuffer();
            for (int i = 0; i < keyLen; i += 2) {
                keys.append(key8.substring(i, i + 2)).append("-");
            }
            byte[] KeyStr = keys.substring(0, keyLen).getBytes("utf-8");
            DESKeySpec keySpec = new DESKeySpec(KeyStr);// 设置密钥参数
            IvParameterSpec iv = new IvParameterSpec(KeyStr);// 设置向量
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
            SecretKey key = keyFactory.generateSecret(keySpec);
            enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
            deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            deCipher.init(Cipher.DECRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //加密
    public static String encrypt(String text)
    {
        if (StringUtils.isEmpty(text)) return null;

        byte[] pasByte = null;
        try {
            pasByte = enCipher.doFinal(text.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new String(byte2hex(pasByte));
    }

    //解密
    public static String decrypt(String crypt)
    {
        if (StringUtils.isEmpty(crypt)) return null;
        byte[] pasByte = null;
        try {
            pasByte = deCipher.doFinal(hex2byte(crypt.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new String(pasByte);
    }

    public static void main(String args[]) {
        //System.out.println(encrypt("x123456"));
        System.out.println(decrypt("248E135E28C103B4"));
    }

    private static final String HEX_CHAR = "0123456789ABCDEF";
    private static final byte[] HEX_STRING_BYTE = HEX_CHAR.getBytes();

    //10进制转16进制
    private static byte[] byte2hex(byte[] b) {
        int length = b.length;
        byte[] b2 = new byte[length << 1];
        int pos;
        for(int i=0; i<length; i++) {
            pos = 2*i;
            b2[pos] = HEX_STRING_BYTE[(b[i] & 0xf0) >> 4];
            b2[pos+1] = HEX_STRING_BYTE[b[i] & 0x0f];
        }
        return b2;
    }

    //16进制转10进制
    public static byte[] hex2byte(byte[] b) {
        if(b.length%2 != 0) {
            throw new IllegalArgumentException("byte array length is not even!");
        }

        int length = b.length >> 1;
        byte[] b2 = new byte[length];
        int pos;
        for(int i=0; i<length; i++) {
            pos = i << 1;
            b2[i] = (byte) (HEX_CHAR.indexOf( b[pos] ) << 4 | HEX_CHAR.indexOf( b[pos+1] ) );
        }
        return b2;
    }

}

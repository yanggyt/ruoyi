package com.ruoyi.common.utils.random;

import java.util.Random;
import java.util.UUID;

/**
 * 邀请码生成器，算法原理：<br/>
 * 1) 获取id: 1127738 <br/>
 * 2) 使用自定义进制转为：gpm6 <br/>
 * 3) 转为字符串，并在后面加'o'字符：gpm6o <br/>
 * 4）在后面随机产生若干个随机数字字符：gpm6o7 <br/>
 * 转为自定义进制后就不会出现o这个字符，然后在后面加个'o'，这样就能确定唯一性。最后在后面产生一些随机字符进行补全。<br/>
 *
 * @author jiayu.qiu
 */
public class ShareCodeUtil {

    /**
     * 自定义进制
     */
    private static final char[] DIGITS = new char[]{'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h'};

    /**
     * 进制长度
     */
    private static final int DIGITS_LENGTH = DIGITS.length;

    /**
     * 序列最小长度
     */
    private static final int MIN_LENGTH = 6;


    public static String toSerialCode(String uuid) {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : uuid.getBytes()) {
            sb.append(b);
        }

        Random random = new Random();
        int mod = random.nextInt(19);
        mod = mod < 8 ? 8 : mod;

        Long sum = 0L;
        int l = sb.length() / mod;
        for (int i = 0; i < l; i++) {
            int start = i * mod;
            int end = (i + 1) * mod;
            sum += Long.parseLong(sb.substring(start, end));
        }
        return toSerialCode(sum);
    }

    /**
     * 根据ID生成六位随机码
     *
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(long id) {
        char[] buf = new char[32];
        int charPos = 32;

        while ((id / DIGITS_LENGTH) > 0) {
            int ind = (int) (id % DIGITS_LENGTH);
            buf[--charPos] = DIGITS[ind];
            id /= DIGITS_LENGTH;
        }
        buf[--charPos] = DIGITS[(int) (id % DIGITS_LENGTH)];
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if (str.length() < MIN_LENGTH) {
            StringBuilder sb = new StringBuilder();
            sb.append('o');
            Random rnd = new Random();
            for (int i = 1; i < MIN_LENGTH - str.length(); i++) {
                sb.append(DIGITS[rnd.nextInt(DIGITS_LENGTH)]);
            }
            str += sb.toString();
        }
        return str.substring(0, MIN_LENGTH).toUpperCase();
    }


    /**
     * 生成指定长度的随机数，全数字
     * @param codeLen
     * @return
     */
    public static String getNewRandomCode(int codeLen) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<codeLen;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getNewRandomCode(4));
    }

}
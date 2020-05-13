package com.ruoyi.common.utils.random;

/**
 * 生成随机数
 *
 * @author solo
 * @date 2019/08/16.
 */
public class RandomUtil {
    /**
     * 随机生成6位数的验证码
     * @param min
     * @param max
     * @return
     */
    public static String getRandNum(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return String.valueOf(randNum);
    }
}

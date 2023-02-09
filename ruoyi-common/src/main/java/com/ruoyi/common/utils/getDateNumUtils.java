package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 */

public class getDateNumUtils
{

    private static int length = 3;

    /**
     * YYYYMM+3位自增长码(9位)
     */
    public static synchronized String getLocalTrmSeqNum(int sequence) {
        sequence = sequence >= 999 ? 1 : sequence + 1;
        String datetime = new SimpleDateFormat("yyyyMM")
                .format(new Date());
        String s = Integer.toString(sequence);
        return datetime +addLeftZero(s, length);
    }

    /**
     * 左填0
     */
    public static String addLeftZero(String s, int length) {
        int old = s.length();
        if (length > old) {
            char[] c = new char[length];
            char[] x = s.toCharArray();
            if (x.length > length) {
                throw new IllegalArgumentException(
                        "Numeric value is larger than intended length: " + s
                                + " LEN " + length);
            }
            int lim = c.length - x.length;
            for (int i = 0; i < lim; i++) {
                c[i] = '0';
            }
            System.arraycopy(x, 0, c, lim, x.length);
            return new String(c);
        }
        return s.substring(0, length);

    }
}

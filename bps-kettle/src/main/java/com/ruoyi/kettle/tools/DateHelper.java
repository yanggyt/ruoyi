package com.ruoyi.kettle.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public DateHelper() {
    }

    public static String format(Date date) {
        String strDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.s");
        strDate = formatter.format(date);
        return strDate;
    }
}

package com.hao.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatUtil {


    public static String format(Long time) {
        Date date = new Date(time);
        return simpleDateFormat.format(date);

    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

}

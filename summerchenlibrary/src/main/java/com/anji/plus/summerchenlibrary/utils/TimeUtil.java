package com.anji.plus.summerchenlibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/25.
 * timetype="yyyy-MM-dd HH:mm:ss"
 */

public class TimeUtil {
    /*
   * 将时间转换为时间戳
   */
    public static Long dateToTimestamp(String s, String timetype) throws ParseException {
        Long res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timetype);
        Date date = simpleDateFormat.parse(s);
        res = date.getTime();
        return res;
    }

    /*
    * 将时间戳转换为时间
    */
    public static String timeStampToDate(Long s, String timetype) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timetype);
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }
}

package com.colorone.common.utils.data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/4
 * @备注：时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间增天数
     *
     * @param date
     * @param count
     * @return
     */
    public static Date addDay(Date date, int count) {
        long d = (long) count * 24 * 60 * 60;
        return Date.from(date.toInstant().plusSeconds(d));
    }


    /**
     * 时间增小时数
     *
     * @param date
     * @param count
     * @return
     */
    public static Date addHour(Date date, int count) {
        long d = (long) count * 60 * 60;
        return Date.from(date.toInstant().plusSeconds(d));
    }

    /**
     * 获取当前Date型日期
     *
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 时间增分钟数
     *
     * @param date
     * @param count
     * @return
     */
    public static Date addMin(Date date, int count) {
        long d = (long) count * 60;
        return Date.from(date.toInstant().plusSeconds(d));
    }

    /**
     * 两个时间相差毫秒
     *
     * @param dateStart
     * @param dateEnd
     */
    public static Long differenceMillisecond(Date dateStart, Date dateEnd) {
        return dateEnd.getTime() - dateStart.getTime();
    }


    /**
     * 获取当前日期String
     *
     * @param format
     * @param count
     * @return
     */
    public static String getStringDay(String format, int count) {
        Date date = addDay(new Date(), count);
        return dateToString(format, date);
    }


    /**
     * 时间日期转String
     *
     * @param format
     * @param date
     * @return
     */
    public static String dateToString(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }
}

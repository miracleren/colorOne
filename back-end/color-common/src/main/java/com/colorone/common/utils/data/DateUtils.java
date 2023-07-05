package com.colorone.common.utils.data;

import java.util.Date;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/4
 * @备注：时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {


    /**
     * 时间增天数
     */
    public static Date addDay(Date date, int count) {
        long d = (long) count * 24 * 60 * 60;
        return Date.from(date.toInstant().plusSeconds(d));
    }


    /**
     * 时间增小时数
     */
    public static Date addHour(Date date, int count) {
        long d = (long) count * 60 * 60;
        return Date.from(date.toInstant().plusSeconds(d));
    }


    /**
     * 时间增分钟数
     */
    public static Date addMin(Date date, int count) {
        long d = (long) count * 60;
        return Date.from(date.toInstant().plusSeconds(d));
    }

    /**
     * 两个时间相差毫秒
     * @param dateStart
     * @param dateEnd
     */
    public static Long differenceMillisecond(Date dateStart,Date dateEnd)
    {
        return dateEnd.getTime()-dateStart.getTime();
    }
}

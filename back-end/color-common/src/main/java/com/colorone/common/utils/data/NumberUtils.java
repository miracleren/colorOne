package com.colorone.common.utils.data;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/4
 * @备注：
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
    /**
     * 转换数据为Int
     *
     * @param value
     * @return
     */
    public static Integer toInt(Object value) {
        if (value == null)
            return null;
        if (value instanceof Integer)
            return (Integer) value;
        if (value instanceof Number)
            return ((Number) value).intValue();

        //如果是字符串
        try {
            return Integer.parseInt(StringUtils.toString(value).trim());
        } catch (Exception e) {
            return null;
        }
    }
}

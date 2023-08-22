package com.colorone.common.utils.data;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/21
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 转换为字符串
     *
     * @param value
     * @return
     */
    public static String toString(Object value) {
        if (value == null)
            return null;
        if (value instanceof String)
            return (String) value;
        return value.toString();
    }



}

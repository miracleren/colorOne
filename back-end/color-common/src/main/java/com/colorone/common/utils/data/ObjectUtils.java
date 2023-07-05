package com.colorone.common.utils.data;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/4
 * @备注：
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {
    /**
     * 判段对象是否不为空
     *
     * @param object
     * @return
     */
    public static boolean isNotNull(Object object) {
        return object != null;
    }

    /**
     * 判段对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        return object == null;
    }
}

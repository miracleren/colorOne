package com.colorone.common.utils.data;

import java.util.UUID;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/30
 * @备注：
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}

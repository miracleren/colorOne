package com.colorone.common.utils.data;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/22
 * @备注：
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {
    /**
     * @param list      list
     * @param splitCode 分割符号
     * @return 拼接串
     */
    public static String joinString(List<String> list, String splitCode) {
        StringBuilder res = new StringBuilder();
        for (String s : list) {
            res.append(s).append(splitCode);
        }
        return res.substring(0, res.length() - splitCode.length());
    }

    /**
     * @param list      list
     * @param splitCode 分割符号
     * @return 拼接串
     */
    public static String joinString(String[] list, String splitCode) {
        StringBuilder res = new StringBuilder();
        for (String s : list) {
            res.append(s).append(splitCode);
        }
        return res.substring(0, res.length() - splitCode.length());
    }

    /**
     * @param list      list
     * @param splitCode 分割符号
     * @return 拼接串
     */
    public static String joinLong(Long[] list, String splitCode) {
        StringBuilder res = new StringBuilder();
        for (Long s : list) {
            res.append(s).append(splitCode);
        }
        return res.substring(0, res.length() - 1);
    }
}

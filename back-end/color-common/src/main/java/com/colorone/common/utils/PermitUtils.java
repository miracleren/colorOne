package com.colorone.common.utils;

import com.colorone.common.utils.data.StringUtils;

import java.util.Set;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/22
 * @备注：权限管理工具类
 */
public class PermitUtils {
    public final static String AllPermitCode = "*:*:*";

    /**
     * 路径转权限标识
     *
     * @param path
     * @return
     */
    public static String toPermitCode(String path) {
        if (StringUtils.isNoneEmpty(path)) {
            path = path.replaceFirst("/", "");
            path = path.replaceAll("\\{|\\}", "").replace("/", ":");
            return path;
        }
        return null;
    }

    public static boolean checkPermits(Set<String> permits, String permit) {
        return permits.contains(AllPermitCode) || permits.contains(permit);
    }
}

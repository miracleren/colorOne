package com.colorone.common.utils;

import com.colorone.common.utils.data.StringUtils;
import org.springframework.util.AntPathMatcher;

import java.util.Set;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/22
 * @备注：权限管理工具类
 */
public class PermitUtils {
public final static String AllPermitCode = "*:*:*";
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * 路径转权限标识
     *
     * @param path
     * @return
     */
    public static String toPermitCode(String path) {
        if (StringUtils.isNoneEmpty(path)) {
            path = path.replaceFirst("/", "");
            path = path.replaceAll("\\{[^}]+\\}", "*").replace("/", ":");
            return path;
        }
        return null;
    }

    public static boolean checkPermits(Set<String> permits, String permit) {
        if (permits != null && !permits.isEmpty()) {
            // 1. 检查是否拥有超级管理员权限（全量放行）
            if (permits.contains(AllPermitCode)) {
                return true;
            }
            // 2. 遍历用户的权限列表，使用 Ant 风格匹配
            for (String userPermit : permits) {
                if (StringUtils.isNotBlank(userPermit) && PATH_MATCHER.match(userPermit, permit)) {
                    return true;
                }
            }
        }
        return false;
    }
}

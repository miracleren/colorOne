package com.colorone.common.utils;

import com.colorone.common.domain.auth.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/29 通用安全信息工具类
 */

public class SecurityUtils {

    /**
     * 获取当前登录用户Id
     **/
    public static Long getUserId() {
        try {
            return getLoginUser().getUser().getUserId();
        } catch (Exception e) {
            throw new AccessDeniedException("获取用户ID异常");
        }

    }

    /**
     * 获取当前登录用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new AccessDeniedException("获取用户名异常");
        }
    }

    /**
     * 获取登录用户信息
     */
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new AccessDeniedException("获取用户信息异常");
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 是否为超级管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isSuperAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public static boolean isSuperAdmin() {
        return isSuperAdmin(getLoginUser().getUser().getUserId());
    }
}

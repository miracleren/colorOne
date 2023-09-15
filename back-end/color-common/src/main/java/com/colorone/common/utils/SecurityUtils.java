package com.colorone.common.utils;

import com.colorone.common.domain.auth.LoginUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    /**
     * BCryptPasswordEncoder加密
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 获取当前登录用户权限过滤条件
     **/
    public static String getUserScopeWhere() {
        try {
            return getLoginUser().getScopeWhere();
        } catch (Exception e) {
            throw new AccessDeniedException("获取用户权限过滤条件异常");
        }

    }
}

package com.colorone.common.utils;

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
            return (long) 1;
        } catch (Exception e) {
            return null;
            //throw new CustomException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     * 获取当前登录用户账户
     **/
    public static String getUsername() {
        try {
            return "admin";
        } catch (Exception e) {
            return null;
            //throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }


}

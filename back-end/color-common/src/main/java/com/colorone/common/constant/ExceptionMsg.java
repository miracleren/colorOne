package com.colorone.common.constant;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/12
 * @备注：异常前端提示信息
 */
public class ExceptionMsg {

    /**
     * 用户名或密码错误提示
     */
    public static final String LOGIN_BAD_CREDENTIALS = "登录失败，当前登陆的的用户名或密码无效！";


    /**
     * 账号被禁用提示
     */
    public static final String LOGIN_USER_DISABLED = "登录失败，当前登陆的的用户账号被禁用！";

    /**
     * 无权限提示
     */
    public static final String ACCESS_DENIED = "访问失败，当前登录的账号无此权限！";

    /**
     * 账号已经过期
     */
    public static final String ACCOUNT_EXPIRED = "访问失败，当前登录的账号已经过期！";

    /**
     * 验证码无效
     */
    public static final String CAPTCHA_INVALID = "验证码无效或已过期，请重新输入！";
}

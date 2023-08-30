package com.colorone.common.constant;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/4
 * @备注：redis 缓存前缀
 */
public class RedisPrefix {

    /**
     * 登录用户缓存前缀
     */
    public static final String LOGIN_USER = "login_user:";

    /**
     * 缓存在redis的api权限配置信息标记
     */
    public static final String REDIS_PERMIT_URLS = "permit_urls";

     /**
     * 缓存在redis的验证码
     */
    public static final String REDIS_CAPTCHA_CODE = "captcha_code:";
}

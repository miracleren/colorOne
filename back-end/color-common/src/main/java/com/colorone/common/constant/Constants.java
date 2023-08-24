package com.colorone.common.constant;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/29 全局通用常量信息
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";


    /**
     * 逻辑删除已删除
     */
    public static final Integer LOGIC_DELETE_TRUE = 1;

    /**
     * 逻辑删除未删除
     */
    public static final Integer LOGIC_DELETE_FALSE = 0;


    /**
     * 缓存在redis的api权限配置信息名称
     */
    public static final String REDIS_PERMIT_URLS = "permit_urls";
}

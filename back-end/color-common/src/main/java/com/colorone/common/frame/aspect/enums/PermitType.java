package com.colorone.common.frame.aspect.enums;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/18
 * @备注：权限类型
 */
public enum PermitType {
    /**
     * 匿名可访问
     */
    ANONYMOUS,

    /**
     * 登录用户可访问
     */
    LOGIN,

    /**
     * 根据用户角色配置权限访问
     * 说明：配置后可以在系统菜单加载接口信息
     */
    ROLE,

    /**
     * 地址带token可访问
     * 说明：解决部分a标签请求鉴权，通过地址参获取token验证接口权限，默认参数名 url?t={token}
     */
    PATH_TOKEN
}

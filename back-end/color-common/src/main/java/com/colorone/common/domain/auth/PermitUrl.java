package com.colorone.common.domain.auth;

import lombok.Data;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/23
 * @备注：
 */
@Data
public class PermitUrl {
    /**
     * 接口名称
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 权限标识
     */
    private String permitCode;

    /**
     * controller名称
     */
    private String controller;

    /**
     * 接口方法名称
     */
    private String methodName;
}

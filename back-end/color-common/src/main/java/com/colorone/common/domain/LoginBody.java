package com.colorone.common.domain;

import lombok.Data;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/3
 * @备注：登录请求信息
 */
@Data
public class LoginBody {
     /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

}

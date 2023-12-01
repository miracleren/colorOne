package com.colorone.common.frame.exception;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/29
 * @备注：用户密码不正确或不符合规范要求异常类
 */
public class UserPasswordException extends Exception {

    /**
     * 用户密码不正确或不符合规范要求异常类
     *
     * @param message
     */
    public UserPasswordException(String message) {
        super(message, null);
    }

}
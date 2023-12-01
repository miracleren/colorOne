package com.colorone.common.frame.exception;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/29
 * @备注：验证码异常类
 */
public class CaptchaException extends Exception {

    /**
     * 验证码异常
     *
     * @param message
     */
    public CaptchaException(String message) {
        super(message, null);
    }
}
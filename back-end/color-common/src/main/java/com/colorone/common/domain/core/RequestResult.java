package com.colorone.common.domain.core;

import com.github.pagehelper.Page;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2 请求返回数据格式通用实体类
 */
@Data
public class RequestResult {

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 数据对象
     */
    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 初始化一个新创建的 RequestResult 对象，使其表示一个空消息。
     */
    public RequestResult() {
    }

    /**
     * 初始化一个新创建的 RequestResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public RequestResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 RequestResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public RequestResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static RequestResult success() {
        return RequestResult.success(null);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static RequestResult success(Object data) {
        return RequestResult.success("操作成功", data);
    }

    /**
     * 返回分页数据
     *
     * @return 成功消息
     */
    public static RequestResult page(Object data) {
        if (data instanceof Page) {
            PageResult pageResult = new PageResult();
            pageResult.setRows(data);
            pageResult.setTotal(((Page) data).getTotal());
            return RequestResult.success("操作成功", pageResult);
        }
        return RequestResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static RequestResult success(String msg, Object data) {
        return new RequestResult(HttpStatus.OK.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static RequestResult error() {
        return RequestResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static RequestResult error(String msg) {
        return RequestResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static RequestResult error(String msg, Object data) {
        return new RequestResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static RequestResult error(int code, String msg) {
        return new RequestResult(code, msg, null);
    }


    /**
     * 设置返回值为Map类型
     *
     * @param key
     * @param value
     */
    public void setData(String key, Object value) {
        if (!(this.data instanceof Map)) {
            this.data = new HashMap<>();
        }
        ((Map) this.data).put(key, value);
    }
}

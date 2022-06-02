package com.colorone.system.controller;

import com.colorone.common.core.domain.RequestResult;
import jakarta.servlet.http.PushBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理系统相关通用接口
 *
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    /**
     * 项目初始化测试接口
     *
     * @return
     */
    @GetMapping("/hello")
    public RequestResult helloWorld() {
        return RequestResult.success("helloWorld", "颜一简易管理系统 color-one");
    }
}

package com.colorone.system.controller;

import com.colorone.common.core.domain.RequestResult;
import com.colorone.system.service.CommonService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Resource
    private CommonService commonService;

    /**
     * 项目初始化测试接口
     *
     * @return
     */
    @GetMapping("/hello")
    public RequestResult helloWorld() {
        return RequestResult.success("helloWorld", "颜一简易管理系统 color-one");
    }

    /**
     * 用户表数据库链接测试接口
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public RequestResult getUser(@PathVariable String userId) {
        return RequestResult.success(commonService.getUser(userId));
    }

}

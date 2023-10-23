package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.system.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/16
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService commonService;


    /**
     * 项目初始化测试接口
     */
    @GetMapping("/hello")
    public RequestResult helloWorld() {
        return RequestResult.success("颜一简易管理系统 color-one");
    }


    /**
     * 用户表数据库链接测试接口
     */
    @GetMapping("/user/{userId}")
    public RequestResult getUser(@PathVariable String userId) {
        return RequestResult.success(commonService.getUser(userId));
    }

}

package com.colorone.system.controller;

import com.colorone.common.domain.Log.BaseLogLogin;
import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.system.service.BaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/11/30
 * @备注：日志管理
 */
@RestController
@RequestMapping("/log/")
public class BaseLogController {
    @Autowired
    private BaseLogService logService;

    /**
     * 查询登录日志数据列表
     *
     * @param logLogin logLogin 登录日志实体类
     * @return 登录日志列表
     */
    @GetMapping("/login/list")
    @ApiExtension(name = "查询登录日志数据列表", permitType = PermitType.ROLE)
    public RequestResult getBaseNoticeTreeList(BaseLogLogin logLogin) {
        return RequestResult.page(logService.getBaseLogLoginList(logLogin));
    }
}
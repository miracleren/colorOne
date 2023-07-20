package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.utils.SecurityUtils;
import com.colorone.system.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/20 菜单
 */
@RestController
@RequestMapping("/menu")
public class BaseMenuController {

    @Autowired
    private BaseMenuService baseMenuService;

    /**
     * 用户角色菜单树
     */
    @GetMapping("/role/tree")
    public RequestResult getMenuRoleTree() {
        Long[] roles = SecurityUtils.getLoginUser().getRoles();
        return RequestResult.success(baseMenuService.getMenuRoleTree(roles));
    }

}

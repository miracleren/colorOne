package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.system.domain.entity.BaseRole;
import com.colorone.system.service.BaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/24
 * @备注：用户角色管理
 */
@RestController
@RequestMapping("/role")
public class BaseRoleController {
    @Autowired
    private BaseRoleService baseRoleService;

    /**
     * 查询角色数据列表
     *
     * @param role role 角色实体类
     * @return 角色列表
     */
    @GetMapping("/list")
    @ApiExtension(name = "查询角色数据列表", permitType = PermitType.ROLE)
    public RequestResult getBaseRoleTreeList(BaseRole role) {
        return RequestResult.page(baseRoleService.getBaseRoleList(role));
    }

    /**
     * 新增角色数据
     *
     * @param role 角色实体类
     * @return 成功失败
     */
    @PostMapping("/add")
    @ApiExtension(name = "新增角色数据", permitType = PermitType.ROLE)
    public RequestResult addBaseRole(@RequestBody BaseRole role) {
        return RequestResult.success(baseRoleService.addBaseRole(role) > 0);
    }

    /**
     * 修改角色数据
     *
     * @param role 角色实体类
     * @return 成功失败
     */
    @PutMapping("/edit")
    @ApiExtension(name = "修改角色数据", permitType = PermitType.ROLE)
    public RequestResult editBaseRole(@RequestBody BaseRole role) {
        return RequestResult.success(baseRoleService.editBaseRole(role) > 0);
    }

    /**
     * 删除角色数据
     *
     * @param roleId 角色键值
     * @return 成功失败
     */
    @DeleteMapping("/delete/{roleId}")
    @ApiExtension(name = "删除角色数据", permitType = PermitType.ROLE)
    public RequestResult deleteBaseRole(@PathVariable Long roleId) {
        if (1L == roleId)
            return RequestResult.error("不能删除系统超级管理员角色！");
        return RequestResult.success(baseRoleService.deleteBaseRole(roleId) > 0);
    }

    /**
     * 查询角色菜单权限编号列表
     *
     * @param roleId 角色编号
     * @return 菜单ID数组
     */
    @GetMapping("/menus/{roleId}")
    @ApiExtension(name = "查询角色菜单权限编号列表", permitType = PermitType.LOGIN)
    public RequestResult getRoleMenuIds(@PathVariable Long roleId) {
        return RequestResult.success(baseRoleService.getRoleMenuIds(roleId));
    }

    /**
     * 查询角色列表，数据类型关联select组件
     *
     * @return 角色列表
     */
    @GetMapping("/select/list")
    @ApiExtension(name = "查询角色列表，数据类型关联select组件", permitType = PermitType.LOGIN)
    public RequestResult getSelectRoleList() {
        return RequestResult.success(baseRoleService.getSelectRoleList());
    }

    /**
     * 查询用户角色数组数据
     *
     * @return 角色列表
     */
    @GetMapping("/user/{userId}")
    @ApiExtension(name = "查询用户角色数组数据", permitType = PermitType.LOGIN)
    public RequestResult getUserRoles(@PathVariable Long userId) {
        return RequestResult.success(baseRoleService.getUserRoles(userId));
    }

}

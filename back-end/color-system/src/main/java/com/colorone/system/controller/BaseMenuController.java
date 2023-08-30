package com.colorone.system.controller;

import com.colorone.common.constant.RedisPrefix;
import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.common.frame.redis.RedisHelper;
import com.colorone.common.utils.SecurityUtils;
import com.colorone.system.domain.entity.BaseMenu;
import com.colorone.system.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @Autowired
    private RedisHelper redisHelper;

    /**
     * 用户角色菜单树
     */
    @GetMapping("/role/tree")
    @ApiExtension(name = "用户角色菜单树", permitType = PermitType.LOGIN)
    public RequestResult getMenuRoleTree() {
        Long[] roles = SecurityUtils.getLoginUser().getRoles();
        return RequestResult.success(baseMenuService.getMenuRoleTree(roles));

    }

    /**
     * 查询菜单数据列表
     *
     * @param menu menu 菜单实体类
     * @return 菜单列表
     */
    @GetMapping("/list")
    @ApiExtension(name = "查询菜单数据列表", permitType = PermitType.ROLE)
    public RequestResult getBaseMenuTreeList(BaseMenu menu) {
        return RequestResult.success(baseMenuService.getBaseMenuTreeList(menu));
    }

    /**
     * 新增菜单数据
     *
     * @param menu 菜单实体类
     * @return 成功失败
     */
    @PostMapping("/add")
    @ApiExtension(name = "新增菜单数据", permitType = PermitType.ROLE)
    public RequestResult addBaseMenu(@RequestBody BaseMenu menu) {
        return RequestResult.success(baseMenuService.addBaseMenu(menu) > 0);
    }

    /**
     * 修改菜单数据
     *
     * @param menu 菜单实体类
     * @return 成功失败
     */
    @PutMapping("/edit")
    @ApiExtension(name = "修改菜单数据", permitType = PermitType.ROLE)
    public RequestResult editBaseMenu(@RequestBody BaseMenu menu) {
        return RequestResult.success(baseMenuService.editBaseMenu(menu) > 0);
    }

    /**
     * 删除菜单数据
     *
     * @param menuId 菜单键值
     * @return 成功失败
     */
    @DeleteMapping("/delete/{menuId}")
    @ApiExtension(name = "删除菜单数据", permitType = PermitType.ROLE)
    public RequestResult deleteBaseMenu(@PathVariable Long menuId) {
        return RequestResult.success(baseMenuService.deleteBaseMenu(menuId) > 0);
    }

    /**
     * 获取系统操作权限接口列表
     */
    @GetMapping("/permit/url")
    @ApiExtension(name = "获取系统操作权限接口列表", permitType = PermitType.ROLE)
    public RequestResult getPermitUrl() {
        return RequestResult.success(redisHelper.getObject(RedisPrefix.REDIS_PERMIT_URLS));
    }

    /**
     * 新增菜单操作接口
     */
    @PostMapping("/permit/add")
    @ApiExtension(name = "新增菜单操作接口", permitType = PermitType.ROLE)
    public RequestResult addPermitUrls(@RequestBody List<BaseMenu> urls) {
        return RequestResult.success(baseMenuService.addPermitUrls(urls));
    }

    /**
     * 查询菜单权限树（Tree组件）
     *
     * @return 菜单树
     */
    @GetMapping("/select/tree")
    @ApiExtension(name = "查询菜单权限树（Tree组件）", permitType = PermitType.LOGIN)
    public RequestResult getBaseMenuTreeSelect() {
        return RequestResult.success(baseMenuService.getBaseMenuTreeSelect());
    }
}

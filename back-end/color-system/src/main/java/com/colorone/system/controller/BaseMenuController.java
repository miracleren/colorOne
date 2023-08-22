package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.common.utils.SecurityUtils;
import com.colorone.system.domain.entity.BaseMenu;
import com.colorone.system.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public RequestResult deleteBaseMenu(@PathVariable Long menuId) {
        return RequestResult.success(baseMenuService.deleteBaseMenu(menuId) > 0);
    }

}

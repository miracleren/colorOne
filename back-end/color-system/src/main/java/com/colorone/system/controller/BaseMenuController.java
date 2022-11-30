package com.colorone.system.controller;

import com.colorone.common.core.domain.RequestResult;
import com.colorone.common.utils.TreeBuildUtils;
import com.colorone.system.domain.BaseMenu;
import com.colorone.system.domain.SelectTree;
import com.colorone.system.service.BaseMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/28
 */
@RestController
@RequestMapping("/base/menu")
public class BaseMenuController {

    @Resource
    private BaseMenuService baseMenuService;

    /**
     * 获取菜单列表
     *
     * @param baseMenu
     * @return
     */
    @GetMapping("/list")
    public RequestResult getBasMenusList(BaseMenu baseMenu) {
        return RequestResult.success(baseMenuService.getBasMenusList(baseMenu));
    }

    /**
     * 获取菜单树
     *
     * @param baseMenu
     * @return
     */
    @GetMapping("/tree")
    public RequestResult getBasMenusTree(BaseMenu baseMenu) {
        List<BaseMenu> menus = baseMenuService.getBasMenusList(baseMenu);
        List<Map> menuTree = TreeBuildUtils.init()
                .setKey("menuId", null, null)
                .setRel("menuId", "id").setRel("menuName", "label")
                .toListMap(menus).build();
        return RequestResult.success(menuTree);
    }
}

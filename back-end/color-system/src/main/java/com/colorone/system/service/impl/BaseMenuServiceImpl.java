package com.colorone.system.service.impl;

import com.colorone.common.utils.SecurityUtils;
import com.colorone.common.utils.TreeBuildUtils;
import com.colorone.system.domain.entity.BaseMenu;
import com.colorone.system.mapper.BaseMenuMapper;
import com.colorone.system.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
@Service
public class BaseMenuServiceImpl implements BaseMenuService {
    @Autowired
    private BaseMenuMapper baseMenuMapper;

    @Override
    public List<Map> getMenuRoleTree(Long[] roles) {
        List<BaseMenu> menus = new ArrayList<>();
        //超级管理员默认加载所有菜单
        if (SecurityUtils.isSuperAdmin()) {
            menus = baseMenuMapper.selectAdminMenuList();
        } else
            menus = baseMenuMapper.selectMenuListByRoles(roles);

        List<Map> menuTree = TreeBuildUtils.init().
                setKey("menuId", "parentId", null).
                toListMap(menus).build();

        return menuTree;
    }

    @Override
    public List<Map> getBaseMenuTreeList(BaseMenu menu) {
        List<BaseMenu> menus = baseMenuMapper.selectMenuList(menu);
        List<Map> menuTree = TreeBuildUtils.init().
                setKey("menuId", "parentId", null).
                toListMap(menus).build();

        return menuTree;
    }

    @Override
    public Integer addBaseMenu(BaseMenu menu) {
        return baseMenuMapper.insert(menu);
    }

    @Override
    public Integer editBaseMenu(BaseMenu dict) {
        return baseMenuMapper.updateById(dict);
    }

    @Override
    public Integer deleteBaseMenu(Long menuId) {
        BaseMenu menu = new BaseMenu();
        menu.setMenuId(menuId);
        return baseMenuMapper.deleteById(menu);
    }

    @Override
    public Integer addPermitUrls(List<BaseMenu> urls) {
        int i = 0;
        for (BaseMenu m : urls) {
            m.setMenuType("b");
            m.setVisible(1);
            i += baseMenuMapper.insert(m);
        }
        return i;
    }

    @Override
    public List<Map> getBaseMenuTreeSelect() {
        List<Map> menus = baseMenuMapper.selectMenuListLittle();
        List<Map> menuTree = TreeBuildUtils.init().
                setKey("key", "parentId", null).build(menus);

        return menuTree;
    }
}

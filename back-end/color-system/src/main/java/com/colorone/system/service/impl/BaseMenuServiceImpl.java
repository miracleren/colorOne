package com.colorone.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.colorone.common.domain.auth.LoginUser;
import com.colorone.common.frame.security.web.TokenService;
import com.colorone.common.utils.SecurityUtils;
import com.colorone.common.utils.TreeBuildUtils;
import com.colorone.system.domain.SelectTree;
import com.colorone.system.domain.entity.BaseMenu;
import com.colorone.system.mapper.BaseMenuMapper;
import com.colorone.system.service.BaseMenuService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
@Service
public class BaseMenuServiceImpl implements BaseMenuService {
    @Resource
    private BaseMenuMapper baseMenuMapper;

    @Autowired
    private TokenService tokenService;

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
    public Integer deleteBaseMenu(Long dictId) {
        return baseMenuMapper.deleteById(dictId);
    }

    @Override
    public Integer addPermitUrls(List<BaseMenu> urls) {
        int i = 0;
        for (BaseMenu m : urls) {
            m.setMenuType("b");
            i += baseMenuMapper.insert(m);
        }
        return i;
    }
}

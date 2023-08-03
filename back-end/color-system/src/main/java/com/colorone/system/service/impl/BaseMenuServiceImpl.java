package com.colorone.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.colorone.common.utils.SecurityUtils;
import com.colorone.common.utils.TreeBuildUtils;
import com.colorone.system.domain.SelectTree;
import com.colorone.system.domain.entity.BaseMenu;
import com.colorone.system.mapper.BaseMenuMapper;
import com.colorone.system.service.BaseMenuService;
import jakarta.annotation.Resource;
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
    @Resource
    private BaseMenuMapper baseMenuMapper;

    @Override
    public List<BaseMenu> getBasMenusList(BaseMenu baseMenu) {
        LambdaQueryWrapper<BaseMenu> query = Wrappers.lambdaQuery();
        return baseMenuMapper.selectList(query);
    }

    @Override
    public List<Map> getMenuRoleTree(Long[] roles) {
        List<BaseMenu> menus = new ArrayList<>();
        //超级管理员默认加载所有菜单
        if (SecurityUtils.isSuperAdmin())
            menus = baseMenuMapper.selectMenuList();
        else
            menus = baseMenuMapper.selectMenuListByRoles(roles);

        List<Map> menuTree = TreeBuildUtils.init().
                setKey("menuId", "parentId", null).
                toListMap(menus).build();

        return menuTree;
    }
}

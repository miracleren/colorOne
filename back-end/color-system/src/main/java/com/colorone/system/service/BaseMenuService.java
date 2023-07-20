package com.colorone.system.service;

import com.colorone.system.domain.SelectTree;
import com.colorone.system.domain.entity.BaseMenu;

import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/6/2
 */
public interface BaseMenuService {

    /**
     * 获取菜单列表
     * @param baseMenu
     * @return
     */
    List<BaseMenu> getBasMenusList(BaseMenu baseMenu);

    /**
     * 根据角色生成用户菜单树
     * @param roles
     * @return
     */
    List<Map> getMenuRoleTree(Long[] roles);
}

package com.colorone.system.service;

import com.colorone.system.domain.entity.BaseMenu;

import java.util.List;

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
}

package com.colorone.system.service;

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
     * 根据角色生成用户菜单树
     *
     * @param roles
     * @return
     */
    List<Map> getMenuRoleTree(Long[] roles);

    /**
     * 获取菜单树列表
     *
     * @param menu
     * @return
     */
    List<Map> getBaseMenuTreeList(BaseMenu menu);

    /**
     * 新增菜单数据
     *
     * @param menu 菜单实体类
     * @return 成功失败
     */
    Integer addBaseMenu(BaseMenu menu);

    /**
     * 修改菜单数据
     *
     * @param menu 菜单实体类
     * @return 成功失败
     */
    Integer editBaseMenu(BaseMenu menu);

    /**
     * 删除菜单数据
     *
     * @param menuId 菜单键值
     * @return 成功失败
     */
    Integer deleteBaseMenu(Long menuId);

    /**
     * 新增菜单操作接口
     *
     * @param urls 操作接口数据
     * @return 操作数
     */
    Integer addPermitUrls(List<BaseMenu> urls);

    /**
     * 查询菜单权限树（Tree组件）
     *
     * @return
     */
    List<Map> getBaseMenuTreeSelect();
}

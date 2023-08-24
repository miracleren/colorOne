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
     * 根据角色生成用户菜单树
     *
     * @param roles
     * @return
     */
    List<Map> getMenuRoleTree(Long[] roles);

    /**
     * 获取菜单树列表
     *
     * @param dict
     * @return
     */
    List<Map> getBaseMenuTreeList(BaseMenu dict);

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
     * @param dict 菜单实体类
     * @return 成功失败
     */
    Integer editBaseMenu(BaseMenu dict);

    /**
     * 删除菜单数据
     *
     * @param dictId 菜单键值
     * @return 成功失败
     */
    Integer deleteBaseMenu(Long dictId);

    /**
     * 新增菜单操作接口
     *
     * @param urls 操作接口数据
     * @return 操作数
     */
    Integer addPermitUrls(List<BaseMenu> urls);
}

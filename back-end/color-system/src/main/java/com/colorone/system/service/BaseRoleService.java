package com.colorone.system.service;

import com.colorone.system.domain.entity.BaseRole;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/24
 * @备注：
 */
public interface BaseRoleService {

    /**
     * 获取角色树列表
     *
     * @param role
     * @return
     */
    List<BaseRole> getBaseRoleList(BaseRole role);

    /**
     * 新增角色数据
     *
     * @param role 角色实体类
     * @return 成功失败
     */
    Integer addBaseRole(BaseRole role);

    /**
     * 修改角色数据
     *
     * @param role 角色实体类
     * @return 成功失败
     */
    Integer editBaseRole(BaseRole role);

    /**
     * 删除角色数据
     *
     * @param roleId 角色键值
     * @return 成功失败
     */
    Integer deleteBaseRole(Long roleId);

    /**
     * 查询角色菜单权限编号列表
     *
     * @param roleId 角色编号
     * @return 菜单ID数组
     */
    List<Integer> getRoleMenuIds(Long roleId);
}

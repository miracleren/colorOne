package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.LabelItem;
import com.colorone.system.domain.entity.BaseRoleMenu;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/25
 * @备注：
 */
@Mapper
public interface BaseRoleMenuMapper extends BaseMapper<BaseRoleMenu> {
    /**
     * 查询角色菜单权限编号列表
     *
     * @param roleId 角色编号
     * @return
     */
    List<Integer> selectRoleMenuIds(Long roleId);

    /**
     * 清除角色关联菜单权限数据
     *
     * @param roleId   角色编号
     * @param userName 更新用户名称
     * @return
     */
    Integer deleteMenuByRoleId(@Param("roleId") Long roleId, @Param("userName") String userName);

    /**
     * 更新已存在的被删除了的记录
     *
     * @param roleMenu 实体
     * @return 变更数据
     */
    Integer updateDelRoleMenuExist(BaseRoleMenu roleMenu);

    /**
     * 查询角色列表
     * @return
     */
    List<LabelItem> selectRoleListLittle();
}

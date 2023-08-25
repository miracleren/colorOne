package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.entity.BaseMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/28
 */
@Mapper
public interface BaseMenuMapper extends BaseMapper<BaseMenu> {

    /**
     * 通过角色组查询用户菜单
     *
     * @param roles
     * @return
     */
    List<BaseMenu> selectMenuListByRoles(Long[] roles);

    /**
     * 查询管理员菜单列表
     *
     * @return
     */
    List<BaseMenu> selectAdminMenuList();

    /**
     * 查询菜单列表信息，带参
     *
     * @param menu
     * @return
     */
    List<BaseMenu> selectMenuList(BaseMenu menu);

    /**
     * 查询菜单列表，简易数据结构
     *
     * @return
     */
    List<Map> selectMenuListLittle();

}

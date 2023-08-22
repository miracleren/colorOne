package com.colorone.common.frame.security.web;

import com.colorone.common.domain.auth.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/30
 * @备注：
 */
@Mapper
public interface UserDetailsMapper {
    /**
     * 通过用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Select("select * from base_user where user_name = #{userName}")
    User selectUserByUserName(String userName);

    /**
     * 通过用户ID获取角色ID列表
     *
     * @param userId
     * @return
     */
    @Select("select role_id from base_user_role where del_flag = 0 and user_id = #{userId}")
    Long[] selectUserRoleByUserId(Long userId);

    /**
     * 通过用户角色根据菜单生成权限标识
     *
     * @param roles
     * @return
     */
    @Select("select distinct path from base_menu bm " +
            "left join base_role_menu brm on bm.menu_id = brm.menu_id " +
            "where bm.del_flag = 0 and brm.del_flag = 0 and bm.status = 0 and path is not null and brm.role_id in (${roles})")
    String[] selectUserRolePermits(String roles);
}

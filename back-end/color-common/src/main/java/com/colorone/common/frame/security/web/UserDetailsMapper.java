package com.colorone.common.frame.security.web;

import com.colorone.common.domain.auth.Dept;
import com.colorone.common.domain.auth.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 查询用户所在的部门信息
     *
     * @param deptId
     * @return
     */
    @Select("select * from base_dept where del_flag = 0 and dept_id = #{deptId}")
    Dept selectUserDeptById(Integer deptId);

    /**
     * 加载用户所在角色的数据权限
     *
     * @param roles
     * @return
     */
    @Select("select scope from base_role where del_flag = 0 and status = 0 and role_id in (${roles})")
    String[] selectRolesScopeByIds(String roles);

    /**
     * 加载用户当前部门下的所有子部门
     *
     * @param deptId
     * @return
     */
    @Select("select dept_id from base_dept where find_in_set(#{deptId},ancestors)")
    Long[] selectDeptChildren(Integer deptId);
}

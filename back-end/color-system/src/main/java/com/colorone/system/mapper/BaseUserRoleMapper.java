package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.entity.BaseUser;
import com.colorone.system.domain.entity.BaseUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/25
 * @备注：
 */
@Mapper
public interface BaseUserRoleMapper extends BaseMapper<BaseUserRole> {
    /**
     * 删除用户关联的角色
     *
     * @param user 用户信息
     * @return int
     */
    Integer deleteRoleByUserId(BaseUser user);

    /**
     * 更新已存在的用户关联的角色数据
     *
     * @param userRole 用户关联角色
     * @return int
     */
    Integer updateDelUserRoleExist(BaseUserRole userRole);
}

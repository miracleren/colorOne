package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.entity.BaseRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/24
 * @备注：
 */
@Mapper
public interface BaseRoleMapper extends BaseMapper<BaseRole> {
    /**
     * 查询用户角色列表
     *
     * @param role 参数
     * @return 数据列表
     */
    List<BaseRole> selectBaseRoleList(BaseRole role);

    /**
     * 查询用户关联的角色
     *
     * @param userId 用户ID
     * @return list
     */
    List<Long> selectUserRolesByUserId(Long userId);
}

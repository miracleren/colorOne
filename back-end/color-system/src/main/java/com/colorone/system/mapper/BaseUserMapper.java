package com.colorone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colorone.system.domain.entity.BaseUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：
 */
@Mapper
public interface BaseUserMapper extends BaseMapper<BaseUser> {
    /**
     * 通用用户名称查询用户信息
     * @param userName
     * @return
     */
    BaseUser selectUserByUserName(String userName);
}

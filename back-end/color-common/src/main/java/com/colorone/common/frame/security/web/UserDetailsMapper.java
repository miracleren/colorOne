package com.colorone.common.frame.security.web;

import com.colorone.common.domain.User;
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
}

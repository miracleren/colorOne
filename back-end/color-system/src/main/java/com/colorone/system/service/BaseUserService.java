package com.colorone.system.service;

import com.colorone.system.domain.entity.BaseUser;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：
 */
public interface BaseUserService {
    /**
     * 通用用户名称获取用户信息
     * @param userName
     * @return
     */
    BaseUser selectUserByUserName(String userName);
}

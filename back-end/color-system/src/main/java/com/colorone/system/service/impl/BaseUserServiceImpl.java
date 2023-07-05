package com.colorone.system.service.impl;

import com.colorone.system.domain.entity.BaseUser;
import com.colorone.system.mapper.BaseUserMapper;
import com.colorone.system.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：
 */
@Service
public class BaseUserServiceImpl  implements BaseUserService {
    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public BaseUser selectUserByUserName(String userName) {
        return baseUserMapper.selectUserByUserName(userName);
    }
}

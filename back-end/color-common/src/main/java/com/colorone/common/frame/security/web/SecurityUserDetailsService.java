package com.colorone.common.frame.security.web;

import com.colorone.common.domain.User;
import com.colorone.common.domain.LoginUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：security注册自定义
 */
@Component
public class SecurityUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    /**
     * security注册自定义用户登录信息查询
     *
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDetailsMapper.selectUserByUserName(userName);

        if (user == null) {
            log.info("当前登录用户名" + userName + "不存在!");
            throw new UsernameNotFoundException("当前登录用户名" + userName + "不存在!");
        }

        return new LoginUser(user);
    }
}

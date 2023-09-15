package com.colorone.common.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：登录用户信息
 */
@Data
public class LoginUser implements UserDetails {
    //用户登录信息，值初始化 SecurityUserDetailsService

    /**
     * 登录用户信息
     */
    private User user;

    @JsonIgnore
    public LoginUser(User user) {
        this.user = user;
    }


    /**
     * 登录用户信息
     */
    private Dept dept;

    /**
     * 用户登录UUID
     */
    private String uuid;

    /**
     * 用户令牌标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * token有效期
     */
    private Date expireTime;


    /**
     * 用户角色组
     */
    private Long[] roles;

    /**
     * 权限标识码，根据地址生成
     */
    private Set<String> permits = new HashSet<>();

    /**
     * 数据权限过滤条件
     */
    private String[] scopes;

    /**
     * 数据权限过滤where
     */
    private String scopeWhere;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 账户是否未过期
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return user.getStatus() == 0;
    }
}

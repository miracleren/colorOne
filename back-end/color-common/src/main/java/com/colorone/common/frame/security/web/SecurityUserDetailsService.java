package com.colorone.common.frame.security.web;

import com.colorone.common.constant.LoginLogInfo;
import com.colorone.common.domain.auth.LoginUser;
import com.colorone.common.domain.auth.User;
import com.colorone.common.utils.PermitUtils;
import com.colorone.common.utils.ScopeBuild;
import com.colorone.common.utils.SecurityUtils;
import com.colorone.common.utils.data.CollectionUtils;
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
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //用户信息
        User user = userDetailsMapper.selectUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format(LoginLogInfo.USER_NAME_NOT_FOUND, userName));
        }

        //用户角色
        Long[] roles = userDetailsMapper.selectUserRoleByUserId(user.getUserId());
        if (roles == null) {
            throw new UsernameNotFoundException(String.format(LoginLogInfo.USER_ROLE_NOT_FOUND, userName));
        }

        //返回登录用户信息实体类
        LoginUser loginUser = new LoginUser(user);
        loginUser.setRoles(roles);

        //用户权根标识
        if (SecurityUtils.isSuperAdmin(user.getUserId()))
            loginUser.getPermits().add(PermitUtils.AllPermitCode);
        else {
            String[] paths = userDetailsMapper.selectUserRolePermits(CollectionUtils.joinLong(roles, ","));
            for (String path : paths) {
                loginUser.getPermits().add(PermitUtils.toPermitCode(path));
            }
        }

        //用户部门信息,相关子部门
        loginUser.setDept(userDetailsMapper.selectUserDeptById(user.getDeptId()));
        loginUser.getDept().setChildren(CollectionUtils.joinLong(userDetailsMapper.selectDeptChildren(user.getDeptId()), ","));

        //加载用户权限过滤条件
        String[] scopes = userDetailsMapper.selectRolesScopeByIds(CollectionUtils.joinLong(roles, ","));
        loginUser.setScopes(scopes);

        //生成用户角色权限过滤语句，对开启数据权限的接口有效，注解@DataScope
        String scopeWhere = ScopeBuild.where(CollectionUtils.joinString(loginUser.getScopes(), " or "), loginUser);
        loginUser.setScopeWhere(scopeWhere);

        return loginUser;
    }
}

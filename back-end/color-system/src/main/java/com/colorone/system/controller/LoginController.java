package com.colorone.system.controller;

import com.colorone.common.domain.auth.LoginBody;
import com.colorone.common.domain.auth.LoginUser;
import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.security.web.TokenService;
import com.colorone.system.domain.vo.ClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/29
 * @备注：登录处理
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    //    @Autowired
//    private SecurityUserDetailsService securityUserDetailsService;
//
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录接口
     *
     * @return
     */
    @PostMapping("/user")
    public RequestResult login(@RequestBody LoginBody loginBody) {

        String username = loginBody.getUserName();
        String password = loginBody.getPassword();

        // 传递用户密码给到SpringSecurity执行校验，如果校验失败，会进入BadCredentialsException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        // 验证通过，设置授权信息至SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 如果验证通过了，从返回的authentication里获得完整的UserDetails信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        // 将用户的ID息保存在jwt的token中
        String token = tokenService.createToken(loginUser);

        //登录成功缓存用户信息
        tokenService.cacheLoginUser(loginUser);

        ClientUser clientUser = new ClientUser(loginUser);
        clientUser.setToken(token);

        return RequestResult.success(clientUser);
    }
}

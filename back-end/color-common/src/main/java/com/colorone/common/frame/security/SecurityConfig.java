package com.colorone.common.frame.security;

import com.colorone.common.constant.RedisPrefix;
import com.colorone.common.domain.auth.PermitUrl;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.common.frame.redis.RedisHelper;
import com.colorone.common.frame.security.filter.AuthenticationTokenFilter;
import com.colorone.common.frame.security.handle.AuthenticationEntryPointImpl;
import com.colorone.common.frame.security.handle.LogoutSuccessHandlerImpl;
import com.colorone.common.frame.security.web.SecurityUserDetailsService;
import com.colorone.common.utils.PermitUtils;
import com.colorone.common.utils.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/6/21
 * @备注：spring security配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 认证失败处理方法
     */
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    /**
     * 登出处理方法
     */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * HttpSecurity配置
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        Set<String> anonymousUrls = getApiConfig();

        //CSRF禁用，因为不使用session
        http.csrf(AbstractHttpConfigurer::disable)
                // 设置异常的EntryPoint，如果不设置，默认使用Http403ForbiddenEntryPoint
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(authenticationEntryPoint))
                // 前后端分离是无状态的，不需要session了，直接禁用。
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                // 允许所有OPTIONS请求
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                // 允许直接访问授权登录接口
                                //.requestMatchers(HttpMethod.POST, "/web/authenticate").permitAll()
                                // 允许 SpringMVC 地址匿名访问
                                .requestMatchers(anonymousUrls.toArray(new String[0])).permitAll()
                                // 其他所有接口必须有Authority信息，Authority在登录成功后的UserDetailsImpl对象中默认设置“ROLE_USER”
                                //.requestMatchers("/**").hasAnyAuthority("ROLE_USER")
                                // 允许任意请求被已登录用户访问，不检查Authority
                                .anyRequest().authenticated()
                )
                //登录成功
                .formLogin(withDefaults())
                //登出成功处理
                .logout((logout) ->
                        logout.logoutUrl("/login/user/out")
                                .logoutSuccessHandler(logoutSuccessHandler))
                .authenticationProvider(authenticationProvider())

                // 加我们自定义的过滤器，替代UsernamePasswordAuthenticationFilter
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    /**
     * 注册用户授权检查执行
     *
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //检测用户异常，方便记录日志
        authProvider.setHideUserNotFoundExceptions(false);
        // DaoAuthenticationProvider 从自定义的 userDetailsService.loadUserByUsername 方法获取UserDetails
        authProvider.setUserDetailsService(userDetailsService());
        // 设置密码编辑器
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * 登录时需要调用AuthenticationManager.authenticate执行一次校验
     *
     * @param config
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * 注册自定义用户登录信息查询
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // 调用 JwtUserDetailService实例执行实际校验
        return username -> securityUserDetailsService.loadUserByUsername(username);
    }

    /**
     * 注册密码加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 扫描Api配置，生成匿名标记接口、缓存权限标识接口
     *
     * @return
     */
    private Set<String> getApiConfig() {
        Set<String> anonymousUrls = new HashSet<>();
        List<PermitUrl> permitUrls = new ArrayList<>();

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = SpringUtils.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            ApiExtension api = handlerMethod.getMethodAnnotation(ApiExtension.class);
            if (api != null) {
                if (api.permitType() == PermitType.ANONYMOUS || api.permitType() == PermitType.PATH_TOKEN)
                    anonymousUrls.addAll(infoEntry.getKey().getPatternValues());
                else if (api.permitType() == PermitType.ROLE) {
                    for (String s : infoEntry.getKey().getPatternValues()) {
                        PermitUrl url = new PermitUrl();
                        url.setName(api.name());
                        url.setPermitCode(PermitUtils.toPermitCode(s));
                        url.setUrl(s);
                        url.setController(infoEntry.getValue().getBean().toString());
                        url.setMethodName(infoEntry.getValue().getMethod().getName());
                        permitUrls.add(url);
                    }
                }
            }

        }

        //缓存所有有权限标识的接口
        redisHelper.setObject(RedisPrefix.REDIS_PERMIT_URLS, permitUrls);

        System.out.println("***成功扫描匿名可访问接口" + anonymousUrls.size() + "处***");
        System.out.println("***成功扫描带权限标识接口" + permitUrls.size() + "处***");

        return anonymousUrls;
    }
}

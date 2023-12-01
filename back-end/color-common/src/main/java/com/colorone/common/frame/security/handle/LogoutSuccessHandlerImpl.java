package com.colorone.common.frame.security.handle;

import com.alibaba.fastjson2.JSON;
import com.colorone.common.constant.Constants;
import com.colorone.common.domain.auth.LoginUser;
import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.asyncTask.AsyncFactory;
import com.colorone.common.frame.asyncTask.AsyncTaskManager;
import com.colorone.common.frame.security.web.TokenService;
import com.colorone.common.utils.HttpServletUtils;
import com.colorone.common.utils.data.ObjectUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/14
 * @备注：自定义登出处理方法
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    /**
     * 登出处理
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtils.isNotNull(loginUser)) {
            // 删除用户缓存
            tokenService.clearToken(loginUser);
            // 记录用户退出日志
            AsyncTaskManager.getInstance().execute(AsyncFactory.setLogging(loginUser.getUsername(), Constants.SUCCESS, "成功登出系统"));
        }

        HttpServletUtils.renderString(response, JSON.toJSONString(RequestResult.success("成功登出系统!", null)));
    }
}

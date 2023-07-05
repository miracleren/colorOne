package com.colorone.common.frame.security.handle;

import com.alibaba.fastjson2.JSON;
import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.utils.HttpServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/4
 * @备注：认证失败处理方法
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        int code = HttpStatus.UNAUTHORIZED.value();
        String msg = String.format("您的 %s 访问请求，因认证失败被拒绝！", request.getRequestURI());
        HttpServletUtils.renderString(response, JSON.toJSONString(RequestResult.error(code, msg)));
    }
}

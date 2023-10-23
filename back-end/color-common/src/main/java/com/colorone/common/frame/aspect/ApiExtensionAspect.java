package com.colorone.common.frame.aspect;

import com.colorone.common.domain.auth.LoginUser;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.common.frame.security.web.TokenService;
import com.colorone.common.utils.HttpServletUtils;
import com.colorone.common.utils.PermitUtils;
import com.colorone.common.utils.data.CollectionUtils;
import com.colorone.common.utils.data.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/18
 * @备注：
 */
@Aspect
@Component
public class ApiExtensionAspect {

    @Autowired
    private TokenService tokenService;

    // 配置织入点
    @Pointcut("@annotation(com.colorone.common.frame.aspect.annotation.ApiExtension)")
    public void apiExtensionCut() {
    }

    @Around("apiExtensionCut()")
    public Object apiExtensionCut(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取注解
        ApiExtension api = getAnnotation(joinPoint);
        //根据权限类型校验权限
        if (api != null) {
            if (api.permitType() == PermitType.ROLE) {
                LoginUser loginUser = tokenService.getLoginUser(HttpServletUtils.getRequest());
                String permit = PermitUtils.toPermitCode(HttpServletUtils.getRequest().getRequestURI());
                if (ObjectUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermits()) || !PermitUtils.checkPermits(loginUser.getPermits(), permit)) {
                    return new AccessDeniedException("您无权限进行该操作");
                }
            } else if (api.permitType() == PermitType.PATH_TOKEN) {
                String token = HttpServletUtils.getParameter("t");
                LoginUser loginUser = tokenService.getLoginUser(token);
                if (ObjectUtils.isNull(loginUser))
                    return new AccessDeniedException("您无权限进行访问该链接");
            }

            return joinPoint.proceed();
        }
        return new Exception("");
    }


    /**
     * 是否存在注解，如果存在就获取
     */
    private ApiExtension getAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(ApiExtension.class);
        }
        return null;
    }
}

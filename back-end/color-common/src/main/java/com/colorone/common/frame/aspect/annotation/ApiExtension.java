package com.colorone.common.frame.aspect.annotation;

import com.colorone.common.frame.aspect.enums.PermitType;

import java.lang.annotation.*;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/8/18
 * @备注：接口扩展注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiExtension {

    /**
     * 接口名称
     */
    String name() default "";

    /**
     * 接口权限配置，默认接口登录可访问
     */
    public PermitType permitType() default PermitType.LOGIN;
}

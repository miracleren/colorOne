package com.colorone.common.frame.aspect.annotation;

import com.colorone.common.frame.aspect.enums.ScopeType;

import java.lang.annotation.*;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/11
 * @备注：Mapper 数据权限启用注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * 默认启用数据权限，加载角色配置权限范围
     *
     * @return
     */
    public ScopeType scopeType() default ScopeType.ROLE;
}

package com.ruoyi.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略当前字段
 * 如果注解用在类上，忽略当前类
 * 如果注解用在属性上，忽略当前属性
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface IgnoreTarget {
    /**
     * 使用目的，默认为空
     */
    String[] target() default {};
}

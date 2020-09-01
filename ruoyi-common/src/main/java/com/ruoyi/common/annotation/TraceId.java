package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义 @TraceId
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TraceId {

}

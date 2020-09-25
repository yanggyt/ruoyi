package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * 前端接口鉴权
 * @author bei.wu
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AjaxLogin
{

}
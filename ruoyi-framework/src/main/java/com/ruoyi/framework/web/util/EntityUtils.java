package com.ruoyi.framework.web.util;

import cn.hutool.core.util.RandomUtil;

import java.lang.reflect.Method;


/**
 * 实体类相关工具类
 * 解决问题： 1、快速对实体的常驻字段，如：createUser、updateUser等值快速注入
 *
 * @author Ace
 * @version 1.0
 * @date 2016年4月18日
 * @since 1.7
 */
public class EntityUtils {
    /**
     * 快速将bean的crtUser、crtHost、crtTime、updUser、updHost、updTime附上相关值
     *
     * @param entity 实体bean
     * @author 王浩彬
     */
    public static <T> void setCreateAndUpdatInfo(T entity) {
        setCreateInfo( entity );
        setUpdatedInfo( entity );
    }

    /**
     * 快速将bean的crtUser、crtHost、crtTime附上相关值
     *
     * @param entity 实体bean
     * @author 王浩彬
     */
    public static <T> void setCreateInfo(T entity) {
        try {
            Method[] methods = entity.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().equals( "setCreateBy" )) {
                    m.invoke( entity, com.ruoyi.framework.web.util.ShiroUtils.getUserId() );
                } else if (m.getName().equals( "setId" )) {
                    m.invoke( entity, RandomUtil.randomUUID() );
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 快速将bean的updUser、updHost、updTime附上相关值
     *
     * @param entity 实体bean
     * @author 王浩彬
     */
    public static <T> void setUpdatedInfo(T entity) {
        try {
            Method[] methods = entity.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().equals( "setUpdateBy" )) {
                    m.invoke( entity, com.ruoyi.framework.web.util.ShiroUtils.getUserId() );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

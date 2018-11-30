package com.ruoyi.framework.util;

import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Date;


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
	public static <T> void setCreatAndUpdatInfo(T entity) {
		setCreateInfo(entity);
		setUpdatedInfo(entity);
	}
	
	/**
	 * 快速将bean的crtUser、crtHost、crtTime附上相关值
	 * 
	 * @param entity 实体bean
	 * @author 王浩彬
	 */
	public static <T> void setCreateInfo(T entity){
		try {
			Method[] methods = entity.getClass().getMethods();
			for(Method m : methods){
				if(m.getName().equals("setCreateBy")){
					m.invoke(entity, ShiroUtils.getUserId());
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
	public static <T> void setUpdatedInfo(T entity){
		try {
			Method[] methods = entity.getClass().getMethods();
			for(Method m : methods){
				if(m.getName().equals("setUpdateBy")){
					m.invoke(entity, ShiroUtils.getUserId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

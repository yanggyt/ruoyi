package com.ruoyi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * description: new java files...TODO
 * @version v1.0
 * @author w
 * @date 2021年5月10日下午5:29:43
 **/
@Component
@Aspect
public class Aspects {

	/**
     * description: 配置切点
     * @return void
     * @version v1.0
     * @author w
     * @date 2021年5月10日 下午4:13:32
     */
    @Pointcut(value = "@annotation(com.ruoyi.annotation.AspectTest)")
    public void pointCut() {
    	
    }
	
    /**
     * description: 配置环绕 
     * @param point
     * @throws Throwable
     * @return Object
     * @version v1.0
     * @author w
     * @date 2021年5月10日 下午4:25:51
     */
	@Around("pointCut()")
	public void around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("========== 切面到l ======");
		point.proceed();
		
	}
}

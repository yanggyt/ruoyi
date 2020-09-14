package com.ruoyi.framework.aspectj;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 数据过滤处理
 *
 * @author ruoyi
 */
@Aspect
@Component
public class DataScopeAspect {

    @Autowired
    private ISysUserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    // 配置织入点
    @Pointcut("@annotation(com.ruoyi.common.annotation.DataScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable {
        handleDataScope(point);
    }

    @After("dataScopePointCut()")
    public void doAfter(JoinPoint point) throws Throwable{
        DataScopeContextHolder.clear();
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        // 获得注解
        SysUser currentUser = ShiroUtils.getSysUser();
        DataScope controllerDataScope = getAnnotationLog(joinPoint);

        if (controllerDataScope == null) {
            return;
        }
        // 获取当前的用户

        if (currentUser != null) {
            // 如果是超级管理员，则不过滤数据
            if (!currentUser.isAdmin()) {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope);
            }
        }
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user      用户
     */
    private void dataScopeFilter(JoinPoint joinPoint, SysUser user, DataScope controllerDataScope) {
        user = userService.selectUserWithRolesAndPostsById(user.getUserId());

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        Set<SysRole> roles = user.getRoles();
        for(SysRole sysRole : roles){
            switch (sysRole.getDataScope()){
                case DATA_SCOPE_DEPT: //当前部门
                    break;
                case DATA_SCOPE_SELF: //自己
                    break;
                case DATA_SCOPE_CUSTOM: //自定义
                    break;
                case DATA_SCOPE_DEPT_AND_CHILD: //子部门
                    break;
                default:
                    //无需过滤
                    break;
            }
        }

        DataScopeContextHolder.set(ExpressionUtils.anyOf(predicates));
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScope getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }
}

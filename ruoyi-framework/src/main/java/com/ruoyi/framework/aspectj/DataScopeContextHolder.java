package com.ruoyi.framework.aspectj;

import com.querydsl.core.types.Predicate;

public class DataScopeContextHolder {

    private static ThreadLocal<Predicate> threadLocal = new ThreadLocal<>();

    public static void set(Predicate predicate){
        threadLocal.set(predicate);
    }

    public static void clear(){
        threadLocal.remove();
    }

    public static Predicate get(){
        return threadLocal.get();
    }
}

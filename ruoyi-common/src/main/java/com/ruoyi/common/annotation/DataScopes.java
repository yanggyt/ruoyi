package com.ruoyi.common.annotation;

public enum DataScopes implements Comparable<DataScopes>{
    /**
     * 全部数据权限
     */
    DATA_SCOPE_ALL,
    /**
     * 自定数据权限
     */
    DATA_SCOPE_CUSTOM,
    /**
     * 部门数据权限
     */
    DATA_SCOPE_DEPT,
    /**
     * 部门及以下数据权限
     */
    DATA_SCOPE_DEPT_AND_CHILD,
    /**
     * 仅本人数据权限
     */
    DATA_SCOPE_SELF,
    ;
}

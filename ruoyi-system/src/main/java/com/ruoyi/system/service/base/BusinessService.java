package com.ruoyi.system.service.base;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ListPath;
import com.ruoyi.common.base.BaseService;
import com.ruoyi.system.domain.QSysDept;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BusinessService extends BaseService {

    @Autowired
    private ISysUserService userService;

    /**
     * 过滤数据权限
     * @param path dept属性
     * @param userId 当前用户id
     * @return
     */
    protected Predicate buildDataPermission(QSysDept path, Long userId){
        Set<SysDept> depts = userService.getUserRoleDepts(userId);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(ExpressionUtils.in(path, depts));
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 过滤数据权限
     * @param path dept属性
     * @param userId 当前用户id
     * @return
     */
    protected Predicate buildDataPermission(ListPath<SysDept, QSysDept> path, Long userId){
        Set<SysDept> depts = userService.getUserRoleDepts(userId);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(ExpressionUtils.in(path.any(), depts));
        return ExpressionUtils.allOf(predicates);
    }
}

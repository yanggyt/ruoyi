package com.ruoyi.framework.aspectj;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class MySpecification<T> implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        //custom filter
        addPredicate(root, query, criteriaBuilder, predicates);

        //datascope filter
        Predicate predicate = addDataScopeFilter(root, query, criteriaBuilder);
        predicates.add(predicate);

        //条件之间是 and 关系
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public abstract void addPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<Predicate> predicates);

    protected Predicate addDataScopeFilter(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        ISysUserService userService = SpringUtils.getBean(ISysUserService.class);
        SysUser sysUser = userService.selectUserWithRolesAndPostsById(ShiroUtils.getUserId());

        if(!sysUser.isAdmin()){
            for(SysRole sysRole : sysUser.getRoles()){
                switch (sysRole.getDataScope()){
                    case DATA_SCOPE_DEPT_AND_CHILD:
                        break;
                    case DATA_SCOPE_CUSTOM:
                        break;
                    case DATA_SCOPE_SELF:
                        break;
                    case DATA_SCOPE_DEPT:
                        break;
                }
            }
        }

        //条件之间是 or 关系
        return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
    }
}

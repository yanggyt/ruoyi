package com.ruoyi.common.base;

import com.ruoyi.common.core.text.Convert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>,
        QuerydslPredicateExecutor<T> {

}

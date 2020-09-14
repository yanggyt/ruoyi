package com.ruoyi.common.base;

import com.querydsl.core.types.dsl.*;
import com.ruoyi.common.utils.querydsl.ExpressionUtils;

public class BaseService {

    protected BooleanExpression buildLike(StringPath path, String value){
        return ExpressionUtils.buildLike(path, value);
    }

    protected <T extends Comparable<T>> BooleanExpression buildEqual(ComparableExpressionBase<T> path, T value){
        return ExpressionUtils.buildEqual(path, value);
    }

    protected <T extends Number & Comparable<T>> BooleanExpression buildEqual(NumberPath<T> path, T value){
        return ExpressionUtils.buildEqual(path, value);
    }

    protected <T extends Comparable<T>> BooleanExpression buildGreaterThanOrEqualTo(ComparableExpression<T> path, T value){
        return ExpressionUtils.buildGreaterThanOrEqualTo(path, value);
    }

    protected <T extends Comparable<T>> BooleanExpression buildLessThanOrEqualTo(ComparableExpression<T> path, T value){
        return ExpressionUtils.buildLessThanOrEqualTo(path, value);
    }

    protected BooleanExpression notLike(StringPath path, String value){
        return ExpressionUtils.notLike(path, value);
    }

    protected BooleanExpression notStartWith(StringPath path, String value){
        return ExpressionUtils.notStartWith(path, value);
    }
}

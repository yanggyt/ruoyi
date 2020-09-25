package com.ruoyi.common.base;

import com.querydsl.core.types.Constant;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.*;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.querydsl.ExpressionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    protected Predicate alwaysTrue(){
        Constant<Boolean> expression = (Constant<Boolean>) com.querydsl.core.types.ExpressionUtils.toExpression(Boolean.TRUE);
        return com.querydsl.core.types.ExpressionUtils.eqConst(expression, true);
    }

    public static Collection<Long> toLongIterable(String str){
        return Arrays.stream(Convert.toStrArray(str))
                .map(Long::parseLong)
                .collect(Collectors.toSet());
    }

    public static Collection<Integer> toIntegerIterable(String str){
        return Arrays.stream(Convert.toStrArray(str))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public static Collection<String> toStringIterable(String str){
        return Arrays.stream(Convert.toStrArray(str))
                .collect(Collectors.toSet());
    }

    protected  <ID, ENTITY> Collection<ENTITY> toEntityIterable(Collection<ID> ids, Function<ID, ENTITY> function){
        return Convert.toEntityIterable(ids, function);
    }
}

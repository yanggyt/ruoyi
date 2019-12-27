package com.ruoyi.common.utils.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

/**
 * QueryDsl 构建查询条件的工具类
 */
public class ExpressionUtils {

    /**
     * 构建 like 查询条件
     * @param path 对应的属性
     * @param value 对应的值
     * @return
     */
    public static BooleanExpression buildLike(StringPath path, String value){
        return path.like("%" + value + "%");
    }

    public static BooleanExpression notLike(StringPath path, String value){
        return path.notLike("%" + value + "%");
    }

    public static BooleanExpression notStartWith(StringPath path, String value){
        return path.notLike(value + "%");
    }

    /**
     * 构建 等于 查询
     * @param path
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BooleanExpression buildEqual(ComparableExpression<T> path, T value){
        return path.eq(value);
    }

    /**
     * 构建 等于 查询
     * @param path
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Number & Comparable<T>> BooleanExpression buildEqual(NumberPath<T> path, T value){
        return path.eq(value);
    }

    /**
     * 构建 大于等于 查询
     * @param path
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BooleanExpression buildGreaterThanOrEqualTo(ComparableExpression<T> path, T value){
        return path.goe(value);
    }

    /**
     * 构建 小于等于 查询
     * @param path
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BooleanExpression buildLessThanOrEqualTo(ComparableExpression<T> path, T value){
        return path.loe(value);
    }
}

package com.ruoyi.common.core.page;

import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: Enzo
 * @Date: 2019/3/25 14:48
 */
public class ExampleUtils {

    public static void andEqualTo(Example.Criteria criteria, String filed, Object value) {
        if (null != value && StringUtils.isNotBlank(value.toString())) {
            criteria.andEqualTo(filed, value);
        }
    }

    public static void andBetweenDateToDate(Example.Criteria criteria, String filed, Object fromDate, Object endDate) {
        if (null != fromDate && StringUtils.isNotBlank(fromDate.toString())
                && null != endDate && StringUtils.isNotBlank(endDate.toString())) {

            fromDate = fromDate.toString().contains(":") ? fromDate : fromDate + " 00:00:00";
            endDate = endDate.toString().contains(":") ? endDate : endDate + " 23:59:59";

            criteria.andBetween(filed, fromDate, endDate);
        }
    }

    public static void inField(Example.Criteria criteria, String filed, List<String> values) {
        criteria.andIn(filed, values);
    }

    public static void andLikeTo(Example.Criteria criteria, String field, String value) {
        if (null != value && StringUtils.isNotBlank(value.toString())) {
            criteria.andLike(field, String.format("%%%s%%", value));
        }
    }
}

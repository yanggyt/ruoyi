package com.ruoyi.common.utils;

import com.ruoyi.common.annotation.IgnoreTarget;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类描述
 *
 * @author nengyuan
 */
public class IgnoreFieldsUtils {
    public static String[] getIgnoreFields(Object o, String target) {
        Field[] fields = o.getClass().getDeclaredFields();
        if (Objects.isNull(fields) || fields.length == 0) {
            return new String[0];
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(IgnoreTarget.class)) {
                String[] t = field.getAnnotation(IgnoreTarget.class).target();
                if (Objects.isNull(t) || t.length == 0) {
                    list.add(field.getName());
                }

                for (String str : t) {
                    if (StringUtils.equals(str, target)) {
                        list.add(field.getName());
                        break;
                    }
                }
            }
        }

        return list.toArray(new String[list.size()]);
    }
}

package com.ruoyi.common.utils;

import java.util.List;

public class ListUtil {

    /**
     * 取集合最后一个
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }

    /**
     * 取第一个
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getFirstElement(List<T> list) {
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}

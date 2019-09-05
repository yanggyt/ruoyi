package com.ruoyi.common.utils.tree;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * tree结构工具类
 *
 * @author solo
 * @date 2010/09/05.
 */
public class ZtreeUtil {

    public static List<Ztree2> ztreeList(List<Ztree2> list, String pid) {
        List<Ztree2> returnList = new ArrayList<Ztree2>();
        for (Ztree2 t : list) {
            if (StringUtils.equals(t.getPid(), pid)) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    private static void  recursionFn(List<Ztree2> list, Ztree2 t) {
        List<Ztree2> childList = getChildList(list, t);// 得到子节点列表
        t.setChildren(childList);
        t.setState("open");
        t.setIsParent(false);
        for (Ztree2 tChild : childList) {
            t.setState("closed");
            t.setIsParent(true);
            if (hasChild(list, tChild)) {// 判断是否有子节点
                for (Ztree2 n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }


    // 得到子节点列表
    private static List<Ztree2> getChildList(List<Ztree2> list, Ztree2 t) {
        List<Ztree2> tlist = new ArrayList<Ztree2>();
        for (Ztree2 n : list) {
            if (StringUtils.equals(n.getPid(), t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    // 判断是否有子节点
    private static boolean hasChild(List<Ztree2> list, Ztree2 t) {
        return getChildList(list, t).size() > 0;
    }
}

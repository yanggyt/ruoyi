package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ruoyi.project.system.menu.domain.Menu;

/**
 * 权限数据处理
 * 
 * @author y
 */
public class TreeUtils
{

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @return String
     */
    public static List<Menu> getChildPerms(List<Menu> list, int praentId)
    {
        List<Menu> returnList = new ArrayList<Menu>();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();)
        {
            Menu t = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == praentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * 
     * @param list
     * @param Menu
     */
    private static void recursionFn(List<Menu> list, Menu t)
    {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Menu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<Menu> it = childList.iterator();
                while (it.hasNext())
                {
                    Menu n = (Menu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    // 得到子节点列表
    private static List<Menu> getChildList(List<Menu> list, Menu t)
    {

        List<Menu> tlist = new ArrayList<Menu>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext())
        {
            Menu n = (Menu) it.next();
            if (n.getParentId().intValue() == t.getMenuId().intValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    List<Menu> returnList = new ArrayList<Menu>();

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<Menu> getChildPerms(List<Menu> list, int typeId, String prefix)
    {
        if (list == null)
        {
            return null;
        }
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();)
        {
            Menu node = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId)
            {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<Menu> list, Menu node, String p)
    {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, node);
        if (hasChild(list, node))
        {
            // 判断是否有子节点
            returnList.add(node);
            Iterator<Menu> it = childList.iterator();
            while (it.hasNext())
            {
                Menu n = (Menu) it.next();
                n.setMenuName(p + n.getMenuName());
                recursionFn(list, n, p + p);
            }
        }
        else
        {
            returnList.add(node);
        }
    }

    // 判断是否有子节点
    private static boolean hasChild(List<Menu> list, Menu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    // 本地模拟数据测试
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        List<Menu> permList = new ArrayList<Menu>();

        Menu perm1 = new Menu();
        perm1.setMenuId(100);
        perm1.setMenuName("系统管理");
        perm1.setParentId(0);

        Menu perm2 = new Menu();
        perm2.setMenuId(101);
        perm2.setMenuName("用户管理");
        perm2.setParentId(100);

        Menu perm3 = new Menu();
        perm3.setMenuId(102);
        perm3.setMenuName("角色管理");
        perm3.setParentId(100);

        Menu perm4 = new Menu();
        perm4.setMenuId(103);
        perm4.setMenuName("菜单管理");
        perm4.setParentId(100);

        Menu perm5 = new Menu();
        perm5.setMenuId(103);
        perm5.setMenuName("日志管理");
        perm5.setParentId(100);

        permList.add(perm1);
        permList.add(perm2);
        permList.add(perm3);
        permList.add(perm4);
        permList.add(perm5);

        List<Menu> ns = TreeUtils.getChildPerms(permList, 0);
        for (Menu m : ns)
        {
            System.out.println(m.getMenuName());
            System.out.println(m.getChildren());
        }
        long end = System.currentTimeMillis();
        System.out.println("用时:" + (end - start) + "ms");

    }

}

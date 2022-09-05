package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.Classs;

import java.util.List;

/**
 * Table containing Class HierarchyService接口
 * 
 * @author zbj
 * @date 2021-08-31
 */
public interface IClasssService
{
    /**
     * 查询Table containing Class Hierarchy
     * 
     * @param classNo Table containing Class Hierarchy主键
     * @return Table containing Class Hierarchy
     */
    public Classs selectClasssByClassNo(Long classNo);

    /**
     * 查询Table containing Class Hierarchy列表
     * 
     * @param classs Table containing Class Hierarchy
     * @return Table containing Class Hierarchy集合
     */
    public List<Classs> selectClasssList(Classs classs);

    /**
     * 新增Table containing Class Hierarchy
     * 
     * @param classs Table containing Class Hierarchy
     * @return 结果
     */
    public int insertClasss(Classs classs);

    /**
     * 修改Table containing Class Hierarchy
     * 
     * @param classs Table containing Class Hierarchy
     * @return 结果
     */
    public int updateClasss(Classs classs);

    /**
     * 批量删除Table containing Class Hierarchy
     * 
     * @param classNos 需要删除的Table containing Class Hierarchy主键集合
     * @return 结果
     */
    public int deleteClasssByClassNos(String classNos);

    /**
     * 删除Table containing Class Hierarchy信息
     * 
     * @param classNo Table containing Class Hierarchy主键
     * @return 结果
     */
    public int deleteClasssByClassNo(Long classNo);

    /**
     * 查询Table containing Class Hierarchy树列表
     * 
     * @return 所有Table containing Class Hierarchy信息
     */
    public List<Ztree> selectClasssTree();
    /**
     * 查询已发布分类树
     *
     * @return
     */
    public List<Classs> selectApprovalClasss();
}

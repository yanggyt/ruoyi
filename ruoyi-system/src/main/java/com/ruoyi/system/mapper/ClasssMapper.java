package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Classs;

import java.util.List;

/**
 * Table containing Class HierarchyMapper接口
 * 
 * @author zbj
 * @date 2021-08-31
 */
public interface ClasssMapper 
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
     * 删除Table containing Class Hierarchy
     * 
     * @param classNo Table containing Class Hierarchy主键
     * @return 结果
     */
    public int deleteClasssByClassNo(Long classNo);

    /**
     * 批量删除Table containing Class Hierarchy
     * 
     * @param classNos 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClasssByClassNos(String[] classNos);
}

package com.ruoyi.front.mapper;

import java.util.List;
import com.ruoyi.front.domain.ClasssicCases;

/**
 * 典型案例Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface ClasssicCasesMapper 
{
    /**
     * 查询典型案例
     * 
     * @param id 典型案例ID
     * @return 典型案例
     */
    public ClasssicCases selectClasssicCasesById(Long id);

    /**
     * 查询典型案例列表
     * 
     * @param classsicCases 典型案例
     * @return 典型案例集合
     */
    public List<ClasssicCases> selectClasssicCasesList(ClasssicCases classsicCases);

    /**
     * 新增典型案例
     * 
     * @param classsicCases 典型案例
     * @return 结果
     */
    public int insertClasssicCases(ClasssicCases classsicCases);

    /**
     * 修改典型案例
     * 
     * @param classsicCases 典型案例
     * @return 结果
     */
    public int updateClasssicCases(ClasssicCases classsicCases);

    /**
     * 删除典型案例
     * 
     * @param id 典型案例ID
     * @return 结果
     */
    public int deleteClasssicCasesById(Long id);

    /**
     * 批量删除典型案例
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClasssicCasesByIds(String[] ids);
}

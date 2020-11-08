package com.ruoyi.web.mapper;



import com.ruoyi.web.domain.ClasssicCases;

import java.util.List;

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

}

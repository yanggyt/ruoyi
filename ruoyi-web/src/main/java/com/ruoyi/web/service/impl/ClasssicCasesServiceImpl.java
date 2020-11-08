package com.ruoyi.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.domain.ClasssicCases;
import com.ruoyi.web.mapper.ClasssicCasesMapper;
import com.ruoyi.web.service.IClasssicCasesService;

import java.util.List;

/**
 * 典型案例Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class ClasssicCasesServiceImpl implements IClasssicCasesService
{
    @Autowired
    private ClasssicCasesMapper classsicCasesMapper;

    /**
     * 查询典型案例
     * 
     * @param id 典型案例ID
     * @return 典型案例
     */
    @Override
    public ClasssicCases selectClasssicCasesById(Long id)
    {
        return classsicCasesMapper.selectClasssicCasesById(id);
    }

    /**
     * 查询典型案例列表
     * 
     * @param classsicCases 典型案例
     * @return 典型案例
     */
    @Override
    public List<ClasssicCases> selectClasssicCasesList(ClasssicCases classsicCases)
    {
        return classsicCasesMapper.selectClasssicCasesList(classsicCases);
    }
}

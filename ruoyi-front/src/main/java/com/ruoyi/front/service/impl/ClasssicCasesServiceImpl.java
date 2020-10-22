package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.ClasssicCasesMapper;
import com.ruoyi.front.domain.ClasssicCases;
import com.ruoyi.front.service.IClasssicCasesService;
import com.ruoyi.common.core.text.Convert;

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

    /**
     * 新增典型案例
     * 
     * @param classsicCases 典型案例
     * @return 结果
     */
    @Override
    public int insertClasssicCases(ClasssicCases classsicCases)
    {
        classsicCases.setCreateTime(DateUtils.getNowDate());
        return classsicCasesMapper.insertClasssicCases(classsicCases);
    }

    /**
     * 修改典型案例
     * 
     * @param classsicCases 典型案例
     * @return 结果
     */
    @Override
    public int updateClasssicCases(ClasssicCases classsicCases)
    {
        classsicCases.setUpdateTime(DateUtils.getNowDate());
        return classsicCasesMapper.updateClasssicCases(classsicCases);
    }

    /**
     * 删除典型案例对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClasssicCasesByIds(String ids)
    {
        return classsicCasesMapper.deleteClasssicCasesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除典型案例信息
     * 
     * @param id 典型案例ID
     * @return 结果
     */
    @Override
    public int deleteClasssicCasesById(Long id)
    {
        return classsicCasesMapper.deleteClasssicCasesById(id);
    }
}

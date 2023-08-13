package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JdStudentMapper;
import com.ruoyi.system.domain.JdStudent;
import com.ruoyi.system.service.IJdStudentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 交大学生数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-07
 */
@Service
public class JdStudentServiceImpl implements IJdStudentService 
{
    @Autowired
    private JdStudentMapper jdStudentMapper;

    /**
     * 查询交大学生数据
     * 
     * @param id 交大学生数据主键
     * @return 交大学生数据
     */
    @Override
    public JdStudent selectJdStudentById(Long id)
    {
        return jdStudentMapper.selectJdStudentById(id);
    }

    /**
     * 查询交大学生数据列表
     * 
     * @param jdStudent 交大学生数据
     * @return 交大学生数据
     */
    @Override
    public List<JdStudent> selectJdStudentList(JdStudent jdStudent)
    {
        return jdStudentMapper.selectJdStudentList(jdStudent);
    }

    /**
     * 新增交大学生数据
     * 
     * @param jdStudent 交大学生数据
     * @return 结果
     */
    @Override
    public int insertJdStudent(JdStudent jdStudent)
    {
        return jdStudentMapper.insertJdStudent(jdStudent);
    }

    /**
     * 修改交大学生数据
     * 
     * @param jdStudent 交大学生数据
     * @return 结果
     */
    @Override
    public int updateJdStudent(JdStudent jdStudent)
    {
        return jdStudentMapper.updateJdStudent(jdStudent);
    }

    /**
     * 批量删除交大学生数据
     * 
     * @param ids 需要删除的交大学生数据主键
     * @return 结果
     */
    @Override
    public int deleteJdStudentByIds(String ids)
    {
        return jdStudentMapper.deleteJdStudentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除交大学生数据信息
     * 
     * @param id 交大学生数据主键
     * @return 结果
     */
    @Override
    public int deleteJdStudentById(Long id)
    {
        return jdStudentMapper.deleteJdStudentById(id);
    }
}

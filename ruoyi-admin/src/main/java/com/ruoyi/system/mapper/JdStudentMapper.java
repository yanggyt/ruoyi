package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.JdStudent;

/**
 * 交大学生数据Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-07
 */
public interface JdStudentMapper 
{
    /**
     * 查询交大学生数据
     * 
     * @param id 交大学生数据主键
     * @return 交大学生数据
     */
    public JdStudent selectJdStudentById(Long id);

    /**
     * 查询交大学生数据列表
     * 
     * @param jdStudent 交大学生数据
     * @return 交大学生数据集合
     */
    public List<JdStudent> selectJdStudentList(JdStudent jdStudent);

    /**
     * 新增交大学生数据
     * 
     * @param jdStudent 交大学生数据
     * @return 结果
     */
    public int insertJdStudent(JdStudent jdStudent);

    /**
     * 修改交大学生数据
     * 
     * @param jdStudent 交大学生数据
     * @return 结果
     */
    public int updateJdStudent(JdStudent jdStudent);

    /**
     * 删除交大学生数据
     * 
     * @param id 交大学生数据主键
     * @return 结果
     */
    public int deleteJdStudentById(Long id);

    /**
     * 批量删除交大学生数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJdStudentByIds(String[] ids);
}

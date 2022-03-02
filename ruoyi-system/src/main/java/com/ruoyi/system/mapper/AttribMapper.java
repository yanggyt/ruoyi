package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Attrib;

import java.util.List;

/**
 * 属性Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
public interface AttribMapper 
{
    /**
     * 查询属性
     * 
     * @param attribNo 属性主键
     * @return 属性
     */
    public Attrib selectAttribByAttribNo(Long attribNo);

    /**
     * 查询属性列表
     * 
     * @param attrib 属性
     * @return 属性集合
     */
    public List<Attrib> selectAttribList(Attrib attrib);
    public List<Attrib> selectAttribListByClasss(long classId);

    /**
     * 新增属性
     * 
     * @param attrib 属性
     * @return 结果
     */
    public int insertAttrib(Attrib attrib);

    /**
     * 修改属性
     * 
     * @param attrib 属性
     * @return 结果
     */
    public int updateAttrib(Attrib attrib);

    /**
     * 删除属性
     * 
     * @param attribNo 属性主键
     * @return 结果
     */
    public int deleteAttribByAttribNo(Long attribNo);

    /**
     * 批量删除属性
     * 
     * @param attribNos 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttribByAttribNos(String[] attribNos);
}

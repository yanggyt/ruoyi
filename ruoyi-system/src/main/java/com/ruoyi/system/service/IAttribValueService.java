package com.ruoyi.system.service;

import com.ruoyi.system.domain.AttribValue;

import java.util.List;

/**
 * Table containing ATTRIB_VALUEService接口
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
public interface IAttribValueService 
{
    /**
     * 查询Table containing ATTRIB_VALUE
     * 
     * @param attribValueNo Table containing ATTRIB_VALUE主键
     * @return Table containing ATTRIB_VALUE
     */
    public AttribValue selectAttribValueByAttribValueNo(Long attribValueNo);

    /**
     * 查询Table containing ATTRIB_VALUE列表
     * 
     * @param attribValue Table containing ATTRIB_VALUE
     * @return Table containing ATTRIB_VALUE集合
     */
    public List<AttribValue> selectAttribValueList(AttribValue attribValue);
    /**
     * 查询Table containing ATTRIB_VALUE列表
     *
     * @param attribId 属性ID
     * @return ATTRIB_VALUE集合
     */
    public List<AttribValue> selectByAttrib(Long attribId);
    /**
     * 根据分类ID获取描述规则
     *
     * @param typeId 分类ID
     * @return 集合
     */
    public List<AttribValue> selectPropertyFormulaByTypeId(Long typeId);
    public List<AttribValue> selectPropertyFormulaByTypeAndProperty(Long typeId,Long property);

    /**
     * 新增Table containing ATTRIB_VALUE
     * 
     * @param attribValue Table containing ATTRIB_VALUE
     * @return 结果
     */
    public int insertAttribValue(AttribValue attribValue);

    /**
     * 修改Table containing ATTRIB_VALUE
     * 
     * @param attribValue Table containing ATTRIB_VALUE
     * @return 结果
     */
    public int updateAttribValue(AttribValue attribValue);

    /**
     * 批量删除Table containing ATTRIB_VALUE
     * 
     * @param attribValueNos 需要删除的Table containing ATTRIB_VALUE主键集合
     * @return 结果
     */
    public int deleteAttribValueByAttribValueNos(String attribValueNos);

    /**
     * 删除Table containing ATTRIB_VALUE信息
     * 
     * @param attribValueNo Table containing ATTRIB_VALUE主键
     * @return 结果
     */
    public int deleteAttribValueByAttribValueNo(Long attribValueNo);
}

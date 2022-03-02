package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.AttribValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Table containing ATTRIB_VALUEMapper接口
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
public interface AttribValueMapper 
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
     * 查询Table containing ATTRIB_VALUE列表
     *
     * @param typeId typeId
     * @return 集合
     */
    public List<AttribValue> selectPropertyFormulaByTypeId(Long typeId);
    public List<AttribValue> selectPropertyFormulaByTypeAndProperty(@Param("class_no")Long class_no, @Param("entity_property_no")Long entity_property_no    );

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
     * 删除Table containing ATTRIB_VALUE
     * 
     * @param attribValueNo Table containing ATTRIB_VALUE主键
     * @return 结果
     */
    public int deleteAttribValueByAttribValueNo(Long attribValueNo);

    /**
     * 批量删除Table containing ATTRIB_VALUE
     * 
     * @param attribValueNos 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttribValueByAttribValueNos(String[] attribValueNos);
}

package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.AttribValue;
import com.ruoyi.system.mapper.AttribValueMapper;
import com.ruoyi.system.service.IAttribValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Table containing ATTRIB_VALUEService业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
@Service
public class AttribValueServiceImpl implements IAttribValueService 
{
    @Autowired
    private AttribValueMapper attribValueMapper;

    /**
     * 查询Table containing ATTRIB_VALUE
     * 
     * @param attribValueNo Table containing ATTRIB_VALUE主键
     * @return Table containing ATTRIB_VALUE
     */
    @Override
    public AttribValue selectAttribValueByAttribValueNo(Long attribValueNo)
    {
        return attribValueMapper.selectAttribValueByAttribValueNo(attribValueNo);
    }

    /**
     * 查询Table containing ATTRIB_VALUE列表
     * 
     * @param attribValue Table containing ATTRIB_VALUE
     * @return Table containing ATTRIB_VALUE
     */
    @Override
    public List<AttribValue> selectAttribValueList(AttribValue attribValue)
    {
        return attribValueMapper.selectAttribValueList(attribValue);
    }

    /**
     * 新增Table containing ATTRIB_VALUE
     * 
     * @param attribValue Table containing ATTRIB_VALUE
     * @return 结果
     */
    @Override
    public int insertAttribValue(AttribValue attribValue)
    {
        return attribValueMapper.insertAttribValue(attribValue);
    }

    /**
     * 修改Table containing ATTRIB_VALUE
     * 
     * @param attribValue Table containing ATTRIB_VALUE
     * @return 结果
     */
    @Override
    public int updateAttribValue(AttribValue attribValue)
    {
        return attribValueMapper.updateAttribValue(attribValue);
    }

    /**
     * 批量删除Table containing ATTRIB_VALUE
     * 
     * @param attribValueNos 需要删除的Table containing ATTRIB_VALUE主键
     * @return 结果
     */
    @Override
    public int deleteAttribValueByAttribValueNos(String attribValueNos)
    {
        return attribValueMapper.deleteAttribValueByAttribValueNos(Convert.toStrArray(attribValueNos));
    }

    /**
     * 删除Table containing ATTRIB_VALUE信息
     * 
     * @param attribValueNo Table containing ATTRIB_VALUE主键
     * @return 结果
     */
    @Override
    public int deleteAttribValueByAttribValueNo(Long attribValueNo)
    {
        return attribValueMapper.deleteAttribValueByAttribValueNo(attribValueNo);
    }
    /**
     * 查询Table  ATTRIB_VALUE列表
     *
     * @param attribId 属性ID
     * @return ATTRIB_VALUE集合
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<AttribValue>selectByAttrib(Long attribId){
        return attribValueMapper.selectByAttrib(attribId);
    }
    /**
     * 根据分类ID获取描述规则
     *
     * @param typeId 分类ID
     * @return 集合
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<AttribValue>selectPropertyFormulaByTypeId(Long typeId){
        return attribValueMapper.selectPropertyFormulaByTypeId(typeId);
    }
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<AttribValue> selectPropertyFormulaByTypeAndProperty(Long typeId,Long entity_property_no ){
        return attribValueMapper.selectPropertyFormulaByTypeAndProperty(typeId,entity_property_no);
    }
}

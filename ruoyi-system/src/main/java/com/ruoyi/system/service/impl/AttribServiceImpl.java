package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.Attrib;
import com.ruoyi.system.mapper.AttribMapper;
import com.ruoyi.system.service.IAttribService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 属性Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
@Service
public class AttribServiceImpl implements IAttribService 
{
    @Autowired
    private AttribMapper attribMapper;

    /**
     * 查询属性
     * 
     * @param attribNo 属性主键
     * @return 属性
     */
    @Override
    public Attrib selectAttribByAttribNo(Long attribNo)
    {
        return attribMapper.selectAttribByAttribNo(attribNo);
    }

    /**
     * 查询属性列表
     * 
     * @param attrib 属性
     * @return 属性
     */
    @Override
    public List<Attrib> selectAttribList(Attrib attrib)
    {
        return attribMapper.selectAttribList(attrib);
    }
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<Attrib> selectAttribListByClasss(Long classsNo) {
        return attribMapper.selectAttribListByClasss(classsNo);
    }

    /**
     * 新增属性
     * 
     * @param attrib 属性
     * @return 结果
     */
    @Override
    public int insertAttrib(Attrib attrib)
    {
        return attribMapper.insertAttrib(attrib);
    }

    /**
     * 修改属性
     * 
     * @param attrib 属性
     * @return 结果
     */
    @Override
    public int updateAttrib(Attrib attrib)
    {
        return attribMapper.updateAttrib(attrib);
    }

    /**
     * 批量删除属性
     * 
     * @param attribNos 需要删除的属性主键
     * @return 结果
     */
    @Override
    public int deleteAttribByAttribNos(String attribNos)
    {
        return attribMapper.deleteAttribByAttribNos(Convert.toStrArray(attribNos));
    }

    /**
     * 删除属性信息
     * 
     * @param attribNo 属性主键
     * @return 结果
     */
    @Override
    public int deleteAttribByAttribNo(Long attribNo)
    {
        return attribMapper.deleteAttribByAttribNo(attribNo);
    }
}

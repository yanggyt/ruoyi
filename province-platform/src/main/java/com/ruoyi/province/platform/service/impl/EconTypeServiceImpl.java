package com.ruoyi.province.platform.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.province.platform.utils.BussUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.province.platform.mapper.EconTypeMapper;
import com.ruoyi.province.platform.domain.EconType;
import com.ruoyi.province.platform.service.IEconTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 经济类型Service业务层处理
 * 
 * @author dalin
 * @date 2020-12-19
 */
@Service
public class EconTypeServiceImpl implements IEconTypeService 
{
    @Autowired
    private EconTypeMapper econTypeMapper;

    /**
     * 查询经济类型
     * 
     * @param econId 经济类型ID
     * @return 经济类型
     */
    @Override
    public EconType selectEconTypeById(Long econId)
    {
        return econTypeMapper.selectEconTypeById(econId);
    }

        /**
     * 查询经济类型列表
     * 
     * @param econType 经济类型
     * @return 经济类型
     */
    @Override
    public List<EconType> selectEconTypeList(EconType econType)
    {
        return econTypeMapper.selectEconTypeList(econType);
    }

    /**
     * 新增经济类型
     * 
     * @param econType 经济类型
     * @return 结果
     */
    @Override
    public int insertEconType(EconType econType)
    {
        // 判断重复

        // 单据号生成
        econType.setDocNum("00001".concat( BussUtils.nextValue("econType") ) );
        econType.setCreateTime(DateUtils.getNowDate());
        return econTypeMapper.insertEconType(econType);
    }

    /**
     * 修改经济类型
     * 
     * @param econType 经济类型
     * @return 结果
     */
    @Override
    public int updateEconType(EconType econType)
    {
        econType.setUpdateTime(DateUtils.getNowDate());
        return econTypeMapper.updateEconType(econType);
    }

    /**
     * 删除经济类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEconTypeByIds(String ids)
    {
        return econTypeMapper.deleteEconTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除经济类型信息
     * 
     * @param econId 经济类型ID
     * @return 结果
     */
    @Override
    public int deleteEconTypeById(Long econId)
    {
        return econTypeMapper.deleteEconTypeById(econId);
    }
}

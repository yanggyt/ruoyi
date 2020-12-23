package com.ruoyi.province.platform.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.domain.EconType;
import com.ruoyi.province.platform.mapper.EconTypeMapper;
import com.ruoyi.province.platform.service.IEconTypeService;
import com.ruoyi.province.platform.utils.BussUtils;
import com.ruoyi.system.domain.SysPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 检验经济类型 名称是否重复
     *
     * @param econName 经济类型ID
     * @return 1 / 0
     */
    @Override
    public String checkEconNameUnique(EconType econType)
    {
        Long econId = StringUtils.isNull( econType.getEconId() ) ? -1L : econType.getEconId();
        EconType info = econTypeMapper.checkEconNameUnique( econType.getEconName() );
        if (StringUtils.isNotNull(info) && info.getEconId().longValue() != econId.longValue())
        {
            return BussiConstants.DOC_NAME_NOT_UNIQUE;
        }
        return BussiConstants.DOC_NAME_UNIQUE;
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
        econType.setDocNum( "00001".concat(BussUtils.nextValue("econType")  ) );
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

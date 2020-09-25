package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizProductTypeMapper;
import com.ruoyi.business.domain.BizProductType;
import com.ruoyi.business.service.IBizProductTypeService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 产品分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-05
 */
@Service
public class BizProductTypeServiceImpl implements IBizProductTypeService 
{
    @Resource
    private BizProductTypeMapper bizProductTypeMapper;

    /**
     * 查询产品分类
     * 
     * @param productTypeId 产品分类ID
     * @return 产品分类
     */
    @Override
    public BizProductType selectBizProductTypeById(Long productTypeId)
    {
        return bizProductTypeMapper.selectBizProductTypeById(productTypeId);
    }

    /**
     * 查询产品分类列表
     * 
     * @param bizProductType 产品分类
     * @return 产品分类
     */
    @Override
    public List<BizProductType> selectBizProductTypeList(BizProductType bizProductType)
    {
        return bizProductTypeMapper.selectBizProductTypeList(bizProductType);
    }

    /**
     * 新增产品分类
     * 
     * @param bizProductType 产品分类
     * @return 结果
     */
    @Override
    public int insertBizProductType(BizProductType bizProductType)
    {
        bizProductType.setCreateTime(DateUtils.getNowDate());
        return bizProductTypeMapper.insertBizProductType(bizProductType);
    }

    /**
     * 修改产品分类
     * 
     * @param bizProductType 产品分类
     * @return 结果
     */
    @Override
    public int updateBizProductType(BizProductType bizProductType)
    {
        bizProductType.setUpdateTime(DateUtils.getNowDate());
        return bizProductTypeMapper.updateBizProductType(bizProductType);
    }

    /**
     * 删除产品分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizProductTypeByIds(String ids)
    {
        return bizProductTypeMapper.deleteBizProductTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品分类信息
     * 
     * @param productTypeId 产品分类ID
     * @return 结果
     */
    @Override
    public int deleteBizProductTypeById(Long productTypeId)
    {
        return bizProductTypeMapper.deleteBizProductTypeById(productTypeId);
    }
}

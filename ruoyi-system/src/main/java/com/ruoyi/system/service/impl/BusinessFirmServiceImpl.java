package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusinessFirmMapper;
import com.ruoyi.system.domain.BusinessFirm;
import com.ruoyi.system.service.IBusinessFirmService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商家信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-19
 */
@Service
public class BusinessFirmServiceImpl implements IBusinessFirmService 
{
    @Autowired
    private BusinessFirmMapper businessFirmMapper;

    /**
     * 查询商家信息
     * 
     * @param id 商家信息ID
     * @return 商家信息
     */
    @Override
    public BusinessFirm selectBusinessFirmById(Long id)
    {
        return businessFirmMapper.selectBusinessFirmById(id);
    }

    /**
     * 查询商家信息列表
     * 
     * @param businessFirm 商家信息
     * @return 商家信息
     */
    @Override
    public List<BusinessFirm> selectBusinessFirmList(BusinessFirm businessFirm)
    {
        return businessFirmMapper.selectBusinessFirmList(businessFirm);
    }

    /**
     * 新增商家信息
     * 
     * @param businessFirm 商家信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBusinessFirm(BusinessFirm businessFirm)
    {
        businessFirm.setCreateTime(DateUtils.getNowDate());
        return businessFirmMapper.insertBusinessFirm(businessFirm);
    }

    /**
     * 修改商家信息
     * 
     * @param businessFirm 商家信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBusinessFirm(BusinessFirm businessFirm)
    {
        businessFirm.setUpdateTime(DateUtils.getNowDate());
        return businessFirmMapper.updateBusinessFirm(businessFirm);
    }

    /**
     * 删除商家信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBusinessFirmByIds(String ids)
    {
        return businessFirmMapper.deleteBusinessFirmByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商家信息信息
     * 
     * @param id 商家信息ID
     * @return 结果
     */
    @Override
    public int deleteBusinessFirmById(Long id)
    {
        return businessFirmMapper.deleteBusinessFirmById(id);
    }
}

package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.LegalServicesMapper;
import com.ruoyi.front.domain.LegalServices;
import com.ruoyi.front.service.ILegalServicesService;
import com.ruoyi.common.core.text.Convert;

/**
 * 法律服务Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class LegalServicesServiceImpl implements ILegalServicesService 
{
    @Autowired
    private LegalServicesMapper legalServicesMapper;

    /**
     * 查询法律服务
     * 
     * @param id 法律服务ID
     * @return 法律服务
     */
    @Override
    public LegalServices selectLegalServicesById(Long id)
    {
        return legalServicesMapper.selectLegalServicesById(id);
    }

    /**
     * 查询法律服务列表
     * 
     * @param legalServices 法律服务
     * @return 法律服务
     */
    @Override
    public List<LegalServices> selectLegalServicesList(LegalServices legalServices)
    {
        return legalServicesMapper.selectLegalServicesList(legalServices);
    }

    /**
     * 新增法律服务
     * 
     * @param legalServices 法律服务
     * @return 结果
     */
    @Override
    public int insertLegalServices(LegalServices legalServices)
    {
        legalServices.setCreateTime(DateUtils.getNowDate());
        return legalServicesMapper.insertLegalServices(legalServices);
    }

    /**
     * 修改法律服务
     * 
     * @param legalServices 法律服务
     * @return 结果
     */
    @Override
    public int updateLegalServices(LegalServices legalServices)
    {
        legalServices.setUpdateTime(DateUtils.getNowDate());
        return legalServicesMapper.updateLegalServices(legalServices);
    }

    /**
     * 删除法律服务对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLegalServicesByIds(String ids)
    {
        return legalServicesMapper.deleteLegalServicesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除法律服务信息
     * 
     * @param id 法律服务ID
     * @return 结果
     */
    @Override
    public int deleteLegalServicesById(Long id)
    {
        return legalServicesMapper.deleteLegalServicesById(id);
    }
}

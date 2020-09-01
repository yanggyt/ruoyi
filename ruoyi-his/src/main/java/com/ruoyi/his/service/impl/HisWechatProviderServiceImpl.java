package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.ruoyi.his.domain.HisMerchantWechat;
import com.ruoyi.his.mapper.HisWechatProviderMapper;
import com.ruoyi.his.domain.HisWechatProvider;
import com.ruoyi.his.service.IHisWechatProviderService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.List;


/**
 * 微信服务商Service业务层处理
 * 
 * @author bend
 * @date 2020-07-27
 */
@Service
public class HisWechatProviderServiceImpl implements IHisWechatProviderService
{
    @Resource
    private HisWechatProviderMapper hisWechatProviderMapper;

    /**
     * 查询微信服务商
     * 
     * @param id 微信服务商ID
     * @return 微信服务商
     */
    @Override
    public HisWechatProvider selectHisWechatProviderById(Long id)
    {
        return hisWechatProviderMapper.selectHisWechatProviderById(id);
    }

    /**
     * 查询微信服务商
     *
     * @param hisWechatProvider 微信服务商ID
     * @return 微信服务商
     */
    @Override
    public HisWechatProvider selectHisWechatProvider(HisWechatProvider hisWechatProvider)
    {
        return hisWechatProviderMapper.selectOne(hisWechatProvider);
    }

    /**
     * 查询微信服务商列表
     * 
     * @param hisWechatProvider 微信服务商
     * @return 微信服务商
     */
    @Override
    public List<HisWechatProvider> selectHisWechatProviderList(HisWechatProvider hisWechatProvider)
    {
        return hisWechatProviderMapper.selectHisWechatProviderList(hisWechatProvider);
    }

    /**
     * 新增微信服务商
     * 
     * @param hisWechatProvider 微信服务商
     * @return 结果
     */
    @Transactional
    @Override
    public int insertHisWechatProvider(HisWechatProvider hisWechatProvider)
    {
        hisWechatProvider.setCreateTime(DateUtils.getNowDate());
        int rows = hisWechatProviderMapper.insertSelective(hisWechatProvider);
        insertHisMerchantWechat(hisWechatProvider);
        return rows;
    }

    /**
     * 批量新增微信服务商
     *
     * @param hisWechatProviderList 微信服务商列表
     * @return 结果
     */
    @Override
    public int insertHisWechatProviderBatch(List<HisWechatProvider> hisWechatProviderList)
    {
        return hisWechatProviderMapper.insertList(hisWechatProviderList);
    }

    /**
     * 修改微信服务商
     * 
     * @param hisWechatProvider 微信服务商
     * @return 结果
     */
    @Transactional
    @Override
    public int updateHisWechatProvider(HisWechatProvider hisWechatProvider)
    {
        hisWechatProviderMapper.deleteHisMerchantWechatByProviderId(hisWechatProvider.getId());
        insertHisMerchantWechat(hisWechatProvider);
        return hisWechatProviderMapper.updateByPrimaryKeySelective(hisWechatProvider);
    }

    /**
     * 删除微信服务商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHisWechatProviderByIds(String ids)
    {
        hisWechatProviderMapper.deleteHisMerchantWechatByProviderIds(Convert.toStrArray(ids));
        return hisWechatProviderMapper.deleteByIds(ids);
    }

    /**
     * 删除微信服务商信息
     * 
     * @param id 微信服务商ID
     * @return 结果
     */
    @Override
    public int deleteHisWechatProviderById(Long id)
    {
        hisWechatProviderMapper.deleteHisMerchantWechatByProviderId(id);
        return hisWechatProviderMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增特约商户信息
     * 
     * @param hisWechatProvider 微信服务商对象
     */
    public void insertHisMerchantWechat(HisWechatProvider hisWechatProvider)
    {
        List<HisMerchantWechat> hisMerchantWechatList = hisWechatProvider.getHisMerchantWechatList();
        Long id = hisWechatProvider.getId();
        if (StringUtils.isNotNull(hisMerchantWechatList))
        {
            List<HisMerchantWechat> list = new ArrayList<>();
            for (HisMerchantWechat hisMerchantWechat : hisMerchantWechatList)
            {
                hisMerchantWechat.setProviderId(id);
                hisMerchantWechat.setCreateTime(hisWechatProvider.getCreateTime());
                list.add(hisMerchantWechat);
            }
            if (list.size() > 0)
            {
                hisWechatProviderMapper.batchHisMerchantWechat(list);
            }
        }
    }
}

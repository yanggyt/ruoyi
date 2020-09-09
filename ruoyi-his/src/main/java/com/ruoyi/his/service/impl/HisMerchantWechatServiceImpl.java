package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisMerchantWechatMapper;
import com.ruoyi.his.domain.HisMerchantWechat;
import com.ruoyi.his.service.IHisMerchantWechatService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 特约商户Service业务层处理
 * 
 * @author bend
 * @date 2020-07-27
 */
@Service
public class HisMerchantWechatServiceImpl implements IHisMerchantWechatService
{
    @Resource
    private HisMerchantWechatMapper hisMerchantWechatMapper;

    /**
     * 查询特约商户
     * 
     * @param id 特约商户ID
     * @return 特约商户
     */
    @Override
    public HisMerchantWechat selectHisMerchantWechatById(Long id)
    {
        return hisMerchantWechatMapper.selectHisMerchantWechatById(id);
    }

    /**
     * 查询特约商户
     *
     * @param hisMerchantWechat 特约商户ID
     * @return 特约商户
     */
    @Override
    public HisMerchantWechat selectHisMerchantWechat(HisMerchantWechat hisMerchantWechat)
    {
        return hisMerchantWechatMapper.selectOne(hisMerchantWechat);
    }

    /**
     * 查询特约商户列表
     * 
     * @param hisMerchantWechat 特约商户
     * @return 特约商户
     */
    @Override
    public List<HisMerchantWechat> selectHisMerchantWechatList(HisMerchantWechat hisMerchantWechat)
    {
        return hisMerchantWechatMapper.selectHisMerchantWechatList(hisMerchantWechat);
    }

    /**
     * 新增特约商户
     * 
     * @param hisMerchantWechat 特约商户
     * @return 结果
     */
    @Override
    public int insertHisMerchantWechat(HisMerchantWechat hisMerchantWechat)
    {
        return hisMerchantWechatMapper.insertSelective(hisMerchantWechat);
    }

    /**
     * 批量新增特约商户
     *
     * @param hisMerchantWechatList 特约商户列表
     * @return 结果
     */
    @Override
    public int insertHisMerchantWechatBatch(List<HisMerchantWechat> hisMerchantWechatList)
    {
        return hisMerchantWechatMapper.insertList(hisMerchantWechatList);
    }

    /**
     * 修改特约商户
     * 
     * @param hisMerchantWechat 特约商户
     * @return 结果
     */
    @Override
    public int updateHisMerchantWechat(HisMerchantWechat hisMerchantWechat)
    {
        return hisMerchantWechatMapper.updateByPrimaryKeySelective(hisMerchantWechat);
    }

    /**
     * 删除特约商户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisMerchantWechatByIds(String ids)
    {
        return hisMerchantWechatMapper.deleteByIds(ids);
    }

    /**
     * 删除特约商户信息
     * 
     * @param id 特约商户ID
     * @return 结果
     */
    @Override
    public int deleteHisMerchantWechatById(Long id)
    {
        return hisMerchantWechatMapper.deleteByPrimaryKey(id);
    }
}

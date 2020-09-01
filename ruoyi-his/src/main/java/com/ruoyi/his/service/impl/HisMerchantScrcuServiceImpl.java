package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisMerchantScrcuMapper;
import com.ruoyi.his.domain.HisMerchantScrcu;
import com.ruoyi.his.service.IHisMerchantScrcuService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 农信商户Service业务层处理
 * 
 * @author bend
 * @date 2020-07-27
 */
@Service
public class HisMerchantScrcuServiceImpl implements IHisMerchantScrcuService
{
    @Resource
    private HisMerchantScrcuMapper hisMerchantScrcuMapper;

    /**
     * 查询农信商户
     * 
     * @param id 农信商户ID
     * @return 农信商户
     */
    @Override
    public HisMerchantScrcu selectHisMerchantScrcuById(Long id)
    {
        return hisMerchantScrcuMapper.selectHisMerchantScrcuById(id);
    }

    /**
     * 查询农信商户
     *
     * @param hisMerchantScrcu 农信商户ID
     * @return 农信商户
     */
    @Override
    public HisMerchantScrcu selectHisMerchantScrcu(HisMerchantScrcu hisMerchantScrcu)
    {
        return hisMerchantScrcuMapper.selectOne(hisMerchantScrcu);
    }

    /**
     * 查询农信商户列表
     * 
     * @param hisMerchantScrcu 农信商户
     * @return 农信商户
     */
    @Override
    public List<HisMerchantScrcu> selectHisMerchantScrcuList(HisMerchantScrcu hisMerchantScrcu)
    {
        return hisMerchantScrcuMapper.selectHisMerchantScrcuList(hisMerchantScrcu);
    }

    /**
     * 新增农信商户
     * 
     * @param hisMerchantScrcu 农信商户
     * @return 结果
     */
    @Override
    public int insertHisMerchantScrcu(HisMerchantScrcu hisMerchantScrcu)
    {
        return hisMerchantScrcuMapper.insertSelective(hisMerchantScrcu);
    }

    /**
     * 批量新增农信商户
     *
     * @param hisMerchantScrcuList 农信商户列表
     * @return 结果
     */
    @Override
    public int insertHisMerchantScrcuBatch(List<HisMerchantScrcu> hisMerchantScrcuList)
    {
        return hisMerchantScrcuMapper.insertList(hisMerchantScrcuList);
    }

    /**
     * 修改农信商户
     * 
     * @param hisMerchantScrcu 农信商户
     * @return 结果
     */
    @Override
    public int updateHisMerchantScrcu(HisMerchantScrcu hisMerchantScrcu)
    {
        return hisMerchantScrcuMapper.updateByPrimaryKeySelective(hisMerchantScrcu);
    }

    /**
     * 删除农信商户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisMerchantScrcuByIds(String ids)
    {
        return hisMerchantScrcuMapper.deleteByIds(ids);
    }

    /**
     * 删除农信商户信息
     * 
     * @param id 农信商户ID
     * @return 结果
     */
    @Override
    public int deleteHisMerchantScrcuById(Long id)
    {
        return hisMerchantScrcuMapper.deleteByPrimaryKey(id);
    }
}

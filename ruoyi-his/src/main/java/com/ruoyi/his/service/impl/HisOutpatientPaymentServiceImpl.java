package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisOutpatientPaymentMapper;
import com.ruoyi.his.domain.HisOutpatientPayment;
import com.ruoyi.his.service.IHisOutpatientPaymentService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 门诊预交Service业务层处理
 * 
 * @author bend
 * @date 2020-07-14
 */
@Service
public class HisOutpatientPaymentServiceImpl implements IHisOutpatientPaymentService
{
    @Resource
    private HisOutpatientPaymentMapper hisOutpatientPaymentMapper;

    /**
     * 查询门诊预交
     * 
     * @param id 门诊预交ID
     * @return 门诊预交
     */
    @Override
    public HisOutpatientPayment selectHisOutpatientPaymentById(Long id)
    {
        return hisOutpatientPaymentMapper.selectHisOutpatientPaymentById(id);
    }

    /**
     * 查询门诊预交
     *
     * @param hisOutpatientPayment 门诊预交ID
     * @return 门诊预交
     */
    @Override
    public HisOutpatientPayment selectHisOutpatientPayment(HisOutpatientPayment hisOutpatientPayment)
    {
        return hisOutpatientPaymentMapper.selectOne(hisOutpatientPayment);
    }

    /**
     * 查询门诊预交列表
     * 
     * @param hisOutpatientPayment 门诊预交
     * @return 门诊预交
     */
    @Override
    public List<HisOutpatientPayment> selectHisOutpatientPaymentList(HisOutpatientPayment hisOutpatientPayment)
    {
        return hisOutpatientPaymentMapper.selectHisOutpatientPaymentList(hisOutpatientPayment);
    }

    /**
     * 新增门诊预交
     * 
     * @param hisOutpatientPayment 门诊预交
     * @return 结果
     */
    @Override
    public int insertHisOutpatientPayment(HisOutpatientPayment hisOutpatientPayment)
    {
        return hisOutpatientPaymentMapper.insertSelective(hisOutpatientPayment);
    }

    /**
     * 批量新增门诊预交
     *
     * @param hisOutpatientPaymentList 门诊预交列表
     * @return 结果
     */
    @Override
    public int insertHisOutpatientPaymentBatch(List<HisOutpatientPayment> hisOutpatientPaymentList)
    {
        return hisOutpatientPaymentMapper.insertList(hisOutpatientPaymentList);
    }

    /**
     * 修改门诊预交
     * 
     * @param hisOutpatientPayment 门诊预交
     * @return 结果
     */
    @Override
    public int updateHisOutpatientPayment(HisOutpatientPayment hisOutpatientPayment)
    {
        return hisOutpatientPaymentMapper.updateByPrimaryKeySelective(hisOutpatientPayment);
    }

    /**
     * 删除门诊预交对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisOutpatientPaymentByIds(String ids)
    {
        return hisOutpatientPaymentMapper.deleteByIds(ids);
    }

    /**
     * 删除门诊预交信息
     * 
     * @param id 门诊预交ID
     * @return 结果
     */
    @Override
    public int deleteHisOutpatientPaymentById(Long id)
    {
        return hisOutpatientPaymentMapper.deleteByPrimaryKey(id);
    }
}

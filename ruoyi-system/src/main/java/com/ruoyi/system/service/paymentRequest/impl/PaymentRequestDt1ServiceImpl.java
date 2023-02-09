package com.ruoyi.system.service.paymentRequest.impl;


import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;
import com.ruoyi.system.mapper.paymentRequest.PaymentRequestDt1Mapper;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestDt1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请款dt1 服务层实现
 */
@Service
public class PaymentRequestDt1ServiceImpl implements IPaymentRequestDt1Service
{
	@Autowired
	private PaymentRequestDt1Mapper paymentRequestDt1Mapper;

	/**
     * 查询请款dt1信息
     * 
     * @param id 请款dt1ID
     * @return 请款dt1信息
     */
    @Override
	public PaymentRequestDt1 selectPaymentRequestDt1ById(Long id)
	{
	    return paymentRequestDt1Mapper.selectPaymentRequestDt1ById(id);
	}
	
	/**
     * 查询请款dt1列表
     * 
     * @param paymentRequestDt1 请款dt1信息
     * @return 请款dt1集合
     */
	@Override
	public List<PaymentRequestDt1> selectPaymentRequestDt1List(PaymentRequestDt1 paymentRequestDt1)
	{
	    return paymentRequestDt1Mapper.selectPaymentRequestDt1List(paymentRequestDt1);
	}
	
    /**
     * 新增请款dt1
     * 
     * @param paymentRequestDt1 请款dt1信息
     * @return 结果
     */
	@Override
	public int insertPaymentRequestDt1(PaymentRequestDt1 paymentRequestDt1)
	{
	    return paymentRequestDt1Mapper.insertPaymentRequestDt1(paymentRequestDt1);
	}
	
	/**
     * 修改请款dt1
     * 
     * @param paymentRequestDt1 请款dt1信息
     * @return 结果
     */
	@Override
	public int updatePaymentRequestDt1(PaymentRequestDt1 paymentRequestDt1)
	{
	    return paymentRequestDt1Mapper.updatePaymentRequestDt1(paymentRequestDt1);
	}

	/**
     * 删除请款dt1对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePaymentRequestDt1ByIds(String ids)
	{
		return paymentRequestDt1Mapper.deletePaymentRequestDt1ByIds(Convert.toStrArray(ids));
	}
	
}

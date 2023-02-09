package com.ruoyi.system.service.paymentRequest.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestFile;
import com.ruoyi.system.mapper.paymentRequest.PaymentRequestFileMapper;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请款相关文件 服务层实现
 */
@Service
public class PaymentRequestFileServiceImpl implements IPaymentRequestFileService
{
	@Autowired
	private PaymentRequestFileMapper paymentRequestFileMapper;

	/**
     * 查询请款相关文件信息
     * 
     * @param id 请款相关文件ID
     * @return 请款相关文件信息
     */
    @Override
	public PaymentRequestFile selectPaymentRequestFileById(Long id)
	{
	    return paymentRequestFileMapper.selectPaymentRequestFileById(id);
	}
	
	/**
     * 查询请款相关文件列表
     * 
     * @param paymentRequestFile 请款相关文件信息
     * @return 请款相关文件集合
     */
	@Override
	public List<PaymentRequestFile> selectPaymentRequestFileList(PaymentRequestFile paymentRequestFile)
	{
	    return paymentRequestFileMapper.selectPaymentRequestFileList(paymentRequestFile);
	}
	
    /**
     * 新增请款相关文件
     * 
     * @param paymentRequestFile 请款相关文件信息
     * @return 结果
     */
	@Override
	public int insertPaymentRequestFile(PaymentRequestFile paymentRequestFile)
	{
	    return paymentRequestFileMapper.insertPaymentRequestFile(paymentRequestFile);
	}
	
	/**
     * 修改请款相关文件
     * 
     * @param paymentRequestFile 请款相关文件信息
     * @return 结果
     */
	@Override
	public int updatePaymentRequestFile(PaymentRequestFile paymentRequestFile)
	{
	    return paymentRequestFileMapper.updatePaymentRequestFile(paymentRequestFile);
	}

	/**
     * 删除请款相关文件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePaymentRequestFileByIds(String ids)
	{
		return paymentRequestFileMapper.deletePaymentRequestFileByIds(Convert.toStrArray(ids));
	}
	
}

package com.ruoyi.system.mapper.paymentRequest;




import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;

import java.util.List;

/**
 * 请款dt1 数据层
 */
public interface PaymentRequestDt1Mapper 
{
	/**
     * 查询请款dt1信息
     * 
     * @param id 请款dt1ID
     * @return 请款dt1信息
     */
	public PaymentRequestDt1 selectPaymentRequestDt1ById(Long id);
	
	/**
     * 查询请款dt1列表
     * 
     * @param paymentRequestDt1 请款dt1信息
     * @return 请款dt1集合
     */
	public List<PaymentRequestDt1> selectPaymentRequestDt1List(PaymentRequestDt1 paymentRequestDt1);
	
	/**
     * 新增请款dt1
     * 
     * @param paymentRequestDt1 请款dt1信息
     * @return 结果
     */
	public int insertPaymentRequestDt1(PaymentRequestDt1 paymentRequestDt1);
	
	/**
     * 修改请款dt1
     * 
     * @param paymentRequestDt1 请款dt1信息
     * @return 结果
     */
	public int updatePaymentRequestDt1(PaymentRequestDt1 paymentRequestDt1);
	
	/**
     * 删除请款dt1(逻辑删除)
     * 
     * @param id 请款dt1ID
     * @return 结果
     */
	public int deletePaymentRequestDt1ById(Long id);
	
	/**
     * 批量删除请款dt1(逻辑删除)
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePaymentRequestDt1ByIds(String[] ids);

	/**
	 * 删除请款dt1(逻辑删除)
	 *
	 * @param requestId requestId
	 * @return 结果
	 */
	public int deletePaymentRequestDt1ByRequestId(Long requestId);
	
}
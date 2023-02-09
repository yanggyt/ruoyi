package com.ruoyi.system.service.paymentRequest;




import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;

import java.util.List;

/**
 * 请款dt1 服务层
 */
public interface IPaymentRequestDt1Service 
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
     * 删除请款dt1信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePaymentRequestDt1ByIds(String ids);
	
}

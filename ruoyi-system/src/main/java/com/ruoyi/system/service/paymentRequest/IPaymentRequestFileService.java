package com.ruoyi.system.service.paymentRequest;



import com.ruoyi.system.domain.paymentRequest.PaymentRequestFile;

import java.util.List;

/**
 * 请款相关文件 服务层
 */
public interface IPaymentRequestFileService 
{
	/**
     * 查询请款相关文件信息
     * 
     * @param id 请款相关文件ID
     * @return 请款相关文件信息
     */
	public PaymentRequestFile selectPaymentRequestFileById(Long id);
	
	/**
     * 查询请款相关文件列表
     * 
     * @param paymentRequestFile 请款相关文件信息
     * @return 请款相关文件集合
     */
	public List<PaymentRequestFile> selectPaymentRequestFileList(PaymentRequestFile paymentRequestFile);
	
	/**
     * 新增请款相关文件
     * 
     * @param paymentRequestFile 请款相关文件信息
     * @return 结果
     */
	public int insertPaymentRequestFile(PaymentRequestFile paymentRequestFile);
	
	/**
     * 修改请款相关文件
     * 
     * @param paymentRequestFile 请款相关文件信息
     * @return 结果
     */
	public int updatePaymentRequestFile(PaymentRequestFile paymentRequestFile);
		
	/**
     * 删除请款相关文件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePaymentRequestFileByIds(String ids);
	
}

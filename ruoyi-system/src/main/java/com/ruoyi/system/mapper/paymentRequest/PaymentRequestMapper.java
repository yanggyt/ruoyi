package com.ruoyi.system.mapper.paymentRequest;

import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;

import java.util.List;

/**
 * 请款单Mapper接口
 * 
 * @author SKaiL
 * @date 2022-09-21
 */
public interface PaymentRequestMapper 
{
    /**
     * 查询请款单
     * 
     * @param id 请款单主键
     * @return 请款单
     */
    public PaymentRequest selectPaymentRequestById(Long id);

    /**
     * 查询请款单列表
     * 
     * @param paymentRequest 请款单
     * @return 请款单集合
     */
    public List<PaymentRequest> selectPaymentRequestList(PaymentRequest paymentRequest);
    /**
     * 查询我的草稿(待提交和撤回)
     * @param paymentRequest
     * @return
     */
    public List<PaymentRequest> selectPaymentRequestListDraft(PaymentRequest paymentRequest);
    /**
     * 新增请款单
     * 
     * @param paymentRequest 请款单
     * @return 结果
     */
    public int insertPaymentRequest(PaymentRequest paymentRequest);

    /**
     * 修改请款单
     * 
     * @param paymentRequest 请款单
     * @return 结果
     */
    public int updatePaymentRequest(PaymentRequest paymentRequest);

    /**
     * 删除请款单
     * 
     * @param id 请款单主键
     * @return 结果
     */
    public int deletePaymentRequestById(Long id);

    /**
     * 批量删除请款单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePaymentRequestByIds(String[] ids);

    /**
     * 批量删除请款单dt1
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePaymentRequestDt1ByRequestIds(String[] ids);
    
    /**
     * 批量新增请款单dt1
     * 
     * @param paymentRequestDt1List 请款单dt1列表
     * @return 结果
     */
    public int batchPaymentRequestDt1(List<PaymentRequestDt1> paymentRequestDt1List);
    

    /**
     * 通过请款单主键删除请款单dt1信息
     * 
     * @param id 请款单ID
     * @return 结果
     */
    public int deletePaymentRequestDt1ByRequestId(Long id);


    /**
     * 将指定字段置为空,前加签相关的字段
     */
    public int updateBeforeForNull(Long id);

    /**
     * 将指定字段置为空,后加签相关的字段
     */
    public int updateAfterForNull(Long id);

    /**
     * 将指定字段置为空,保留相关的字段
     */
    public int updateReserveForNull(Long id);

    public int updateForRetainNull(Long paymentRequestId);


    /**
     * 获取最新的一行
     */
    public List<PaymentRequest> selectTheLastLine(String args);

}

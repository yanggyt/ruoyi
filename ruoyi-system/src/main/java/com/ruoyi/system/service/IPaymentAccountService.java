package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.PaymentAccount;

/**
 * 付款账号信息Service接口
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
public interface IPaymentAccountService 
{
    /**
     * 查询付款账号信息
     * 
     * @param id 付款账号信息主键
     * @return 付款账号信息
     */
    public PaymentAccount selectPaymentAccountById(Long id);

    /**
     * 查询付款账号信息列表
     * 
     * @param paymentAccount 付款账号信息
     * @return 付款账号信息集合
     */
    public List<PaymentAccount> selectPaymentAccountList(PaymentAccount paymentAccount);

    /**
     * 新增付款账号信息
     * 
     * @param paymentAccount 付款账号信息
     * @return 结果
     */
    public int insertPaymentAccount(PaymentAccount paymentAccount);

    /**
     * 修改付款账号信息
     * 
     * @param paymentAccount 付款账号信息
     * @return 结果
     */
    public int updatePaymentAccount(PaymentAccount paymentAccount);

    /**
     * 批量删除付款账号信息
     * 
     * @param ids 需要删除的付款账号信息主键集合
     * @return 结果
     */
    public int deletePaymentAccountByIds(String ids);

    /**
     * 删除付款账号信息信息
     * 
     * @param id 付款账号信息主键
     * @return 结果
     */
    public int deletePaymentAccountById(Long id);
}

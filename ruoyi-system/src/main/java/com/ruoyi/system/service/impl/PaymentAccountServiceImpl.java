package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PaymentAccountMapper;
import com.ruoyi.system.domain.PaymentAccount;
import com.ruoyi.system.service.IPaymentAccountService;
import com.ruoyi.common.core.text.Convert;

/**
 * 付款账号信息Service业务层处理
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
@Service
public class PaymentAccountServiceImpl implements IPaymentAccountService 
{
    @Autowired
    private PaymentAccountMapper paymentAccountMapper;

    /**
     * 查询付款账号信息
     * 
     * @param id 付款账号信息主键
     * @return 付款账号信息
     */
    @Override
    public PaymentAccount selectPaymentAccountById(Long id)
    {
        return paymentAccountMapper.selectPaymentAccountById(id);
    }

    /**
     * 查询付款账号信息列表
     * 
     * @param paymentAccount 付款账号信息
     * @return 付款账号信息
     */
    @Override
    public List<PaymentAccount> selectPaymentAccountList(PaymentAccount paymentAccount)
    {
        return paymentAccountMapper.selectPaymentAccountList(paymentAccount);
    }

    /**
     * 新增付款账号信息
     * 
     * @param paymentAccount 付款账号信息
     * @return 结果
     */
    @Override
    public int insertPaymentAccount(PaymentAccount paymentAccount)
    {
        paymentAccount.setCreateTime(DateUtils.getNowDate());
        return paymentAccountMapper.insertPaymentAccount(paymentAccount);
    }

    /**
     * 修改付款账号信息
     * 
     * @param paymentAccount 付款账号信息
     * @return 结果
     */
    @Override
    public int updatePaymentAccount(PaymentAccount paymentAccount)
    {
        paymentAccount.setUpdateTime(DateUtils.getNowDate());
        return paymentAccountMapper.updatePaymentAccount(paymentAccount);
    }

    /**
     * 批量删除付款账号信息
     * 
     * @param ids 需要删除的付款账号信息主键
     * @return 结果
     */
    @Override
    public int deletePaymentAccountByIds(String ids)
    {
        return paymentAccountMapper.deletePaymentAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除付款账号信息信息
     * 
     * @param id 付款账号信息主键
     * @return 结果
     */
    @Override
    public int deletePaymentAccountById(Long id)
    {
        return paymentAccountMapper.deletePaymentAccountById(id);
    }
}

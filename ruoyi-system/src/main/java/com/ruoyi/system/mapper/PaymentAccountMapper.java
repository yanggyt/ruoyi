package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.PaymentAccount;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 付款账号信息Mapper接口
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
public interface PaymentAccountMapper 
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
     * 删除付款账号信息
     * 
     * @param id 付款账号信息主键
     * @return 结果
     */
    public int deletePaymentAccountById(Long id);

    /**
     * 批量删除付款账号信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePaymentAccountByIds(String[] ids);

    /**
     * 获取合并账套下的汇率(默认以美元)
     * @param currencyStr 币别
     * @return 汇率(1币别 * 汇率 = 美元)
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(value = DataSourceType.SLAVE)
    public String selectCurrency(String currencyStr);
}

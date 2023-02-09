package com.ruoyi.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestConstant {

    /** A生成性支出-经理最大金额 */
    @Value("${PaymentRequest.MAX_A4}")
    public String MAX_A4;
    /** B非生成性支出(持续性)-经理最大金额 */
    @Value("${PaymentRequest.MAX_B4}")
    public String MAX_B4;
    /** C非生成性支出(非持续性)-经理最大金额 */
    @Value("${PaymentRequest.MAX_C4}")
    public String MAX_C4;
    /** D差旅费-经理最大金额 */
    @Value("${PaymentRequest.MAX_D4}")
    public String MAX_D4;
    /** E交际费-经理最大金额 */
    @Value("${PaymentRequest.MAX_E4}")
    public String MAX_E4;

    /** A生成性支出-总监最大金额 */
    @Value("${PaymentRequest.MAX_A3}")
    public String MAX_A3;
    /** B非生成性支出(持续性)-总监最大金额 */
    @Value("${PaymentRequest.MAX_B3}")
    public String MAX_B3;
    /** C非生成性支出(非持续性)-总监最大金额 */
    @Value("${PaymentRequest.MAX_C3}")
    public String MAX_C3;
    /** D差旅费-总监最大金额 */
    @Value("${PaymentRequest.MAX_D3}")
    public String MAX_D3;
    /** E交际费-总监最大金额 */
    @Value("${PaymentRequest.MAX_E3}")
    public String MAX_E3;

    /** A生成性支出-执行总监最大金额 */
    @Value("${PaymentRequest.MAX_A2}")
    public String MAX_A2;
    /** B非生成性支出(持续性)-执行总监最大金额 */
    @Value("${PaymentRequest.MAX_B2}")
    public String MAX_B2;
    /** C非生成性支出(非持续性)-执行总监最大金额 */
    @Value("${PaymentRequest.MAX_C2}")
    public String MAX_C2;
    /** D差旅费-执行总监最大金额 */
    @Value("${PaymentRequest.MAX_D2}")
    public String MAX_D2;
    /** E交际费-执行总监最大金额 */
    @Value("${PaymentRequest.MAX_E2}")
    public String MAX_E2;

    /** A生成性支出-总经理最大金额 */
    @Value("${PaymentRequest.MAX_A1}")
    public String MAX_A1;
    /** B非生成性支出(持续性)-总经理最大金额 */
    @Value("${PaymentRequest.MAX_B1}")
    public String MAX_B1;
    /** C非生成性支出(非持续性)-总经理最大金额 */
    @Value("${PaymentRequest.MAX_C1}")
    public String MAX_C1;
    /** D差旅费-总经理最大金额 */
    @Value("${PaymentRequest.MAX_D1}")
    public String MAX_D1;
    /** E交际费-总经理最大金额 */
    @Value("${PaymentRequest.MAX_E1}")
    public String MAX_E1;

    /** 陈总，核决主管 */
    @Value("${PaymentRequest.RULING1}")
    public String RULING1;
    @Value("${PaymentRequest.RULINGNAME1}")
    public String RULINGNAME1;

    /** 游总，核决主管 */
    @Value("${PaymentRequest.RULING2}")
    public String RULING2;
    @Value("${PaymentRequest.RULINGNAME2}")
    public String RULINGNAME2;

    /** 杭州会计 */
    @Value("${PaymentRequest.HZ_ACCOUNTING}")
    public String HZ_ACCOUNTING;
    @Value("${PaymentRequest.HZ_ACCOUNTING_NAME}")
    public String HZ_ACCOUNTING_NAME;

    /** 开曼\矽望 会计 */
    @Value("${PaymentRequest.KM_ACCOUNTING}")
    public String KM_ACCOUNTING;
    @Value("${PaymentRequest.KM_ACCOUNTING_NAME}")
    public String KM_ACCOUNTING_NAME;

    /** 南京会计 */
    @Value("${PaymentRequest.NJ_ACCOUNTING}")
    public String NJ_ACCOUNTING;
    @Value("${PaymentRequest.NJ_ACCOUNTING_NAME}")
    public String NJ_ACCOUNTING_NAME;

    /** 成都会计 */
    @Value("${PaymentRequest.CD_ACCOUNTING}")
    public String CD_ACCOUNTING;
    @Value("${PaymentRequest.CD_ACCOUNTING_NAME}")
    public String CD_ACCOUNTING_NAME;

    /** 西安会计 */
    @Value("${PaymentRequest.XA_ACCOUNTING}")
    public String XA_ACCOUNTING;
    @Value("${PaymentRequest.XA_ACCOUNTING_NAME}")
    public String XA_ACCOUNTING_NAME;

    /** 上海\上海矽力杰微电子 会计 */
    @Value("${PaymentRequest.SH_ACCOUNTING}")
    public String SH_ACCOUNTING;
    @Value("${PaymentRequest.SH_ACCOUNTING_NAME}")
    public String SH_ACCOUNTING_NAME;

    /** 南京香港会计 */
    @Value("${PaymentRequest.NJXG_ACCOUNTING}")
    public String NJXG_ACCOUNTING;
    @Value("${PaymentRequest.NJXG_ACCOUNTING_NAME}")
    public String NJXG_ACCOUNTING_NAME;

    /** 台湾矽力杰会计 */
    @Value("${PaymentRequest.TW_ACCOUNTING}")
    public String TW_ACCOUNTING;
    @Value("${PaymentRequest.TW_ACCOUNTING_NAME}")
    public String TW_ACCOUNTING_NAME;

    /** 香港\萨摩亚 会计 */
    @Value("${PaymentRequest.XG_ACCOUNTING}")
    public String XG_ACCOUNTING;
    @Value("${PaymentRequest.XG_ACCOUNTING_NAME}")
    public String XG_ACCOUNTING_NAME;

    /** 南京香港会计 */
    @Value("${PaymentRequest.HF_ACCOUNTING}")
    public String HF_ACCOUNTING;
    @Value("${PaymentRequest.HF_ACCOUNTING_NAME}")
    public String HF_ACCOUNTING_NAME;

    /** 澳门会计 */
    @Value("${PaymentRequest.AM_ACCOUNTING}")
    public String AM_ACCOUNTING;
    @Value("${PaymentRequest.AM_ACCOUNTING_NAME}")
    public String AM_ACCOUNTING_NAME;

    /** 杭州总账 */
    @Value("${PaymentRequest.HZ_GENERAL_LEDGER}")
    public String HZ_GENERAL_LEDGER;
    @Value("${PaymentRequest.HZ_GENERAL_LEDGER_NAME}")
    public String HZ_GENERAL_LEDGER_NAME;

    /** 开曼\矽望 总账 */
    @Value("${PaymentRequest.KM_GENERAL_LEDGER}")
    public String KM_GENERAL_LEDGER;
    @Value("${PaymentRequest.KM_GENERAL_LEDGER_NAME}")
    public String KM_GENERAL_LEDGER_NAME;

    /** 南京总账 */
    @Value("${PaymentRequest.NJ_GENERAL_LEDGER}")
    public String NJ_GENERAL_LEDGER;
    @Value("${PaymentRequest.NJ_GENERAL_LEDGER_NAME}")
    public String NJ_GENERAL_LEDGER_NAME;

    /** 成都总账 */
    @Value("${PaymentRequest.CD_GENERAL_LEDGER}")
    public String CD_GENERAL_LEDGER;
    @Value("${PaymentRequest.CD_GENERAL_LEDGER_NAME}")
    public String CD_GENERAL_LEDGER_NAME;

    /** 西安总账 */
    @Value("${PaymentRequest.XA_GENERAL_LEDGER}")
    public String XA_GENERAL_LEDGER;
    @Value("${PaymentRequest.XA_GENERAL_LEDGER_NAME}")
    public String XA_GENERAL_LEDGER_NAME;

    /** 上海总账 */
    @Value("${PaymentRequest.SH_GENERAL_LEDGER}")
    public String SH_GENERAL_LEDGER;
    @Value("${PaymentRequest.SH_GENERAL_LEDGER_NAME}")
    public String SH_GENERAL_LEDGER_NAME;

    /** 上海矽力杰微电子 总账 */
    @Value("${PaymentRequest.SHW_GENERAL_LEDGER}")
    public String SHW_GENERAL_LEDGER;
    @Value("${PaymentRequest.SHW_GENERAL_LEDGER_NAME}")
    public String SHW_GENERAL_LEDGER_NAME;

    /** 南京香港 总账 */
    @Value("${PaymentRequest.NJXG_GENERAL_LEDGER}")
    public String NJXG_GENERAL_LEDGER;
    @Value("${PaymentRequest.NJXG_GENERAL_LEDGER_NAME}")
    public String NJXG_GENERAL_LEDGER_NAME;

    /** 台湾总账 */
    @Value("${PaymentRequest.TW_GENERAL_LEDGER}")
    public String TW_GENERAL_LEDGER;
    @Value("${PaymentRequest.TW_GENERAL_LEDGER_NAME}")
    public String TW_GENERAL_LEDGER_NAME;

    /** 台湾总账(特殊处理) */
    @Value("${PaymentRequest.TW_GENERAL_LEDGER1}")
    public String TW_GENERAL_LEDGER1;
    @Value("${PaymentRequest.TW_GENERAL_LEDGER1_NAME}")
    public String TW_GENERAL_LEDGER1_NAME;

    /** 香港\萨摩亚 总账 */
    @Value("${PaymentRequest.XG_GENERAL_LEDGER}")
    public String XG_GENERAL_LEDGER;
    @Value("${PaymentRequest.XG_GENERAL_LEDGER_NAME}")
    public String XG_GENERAL_LEDGER_NAME;

    /** 合肥总账 */
    @Value("${PaymentRequest.HF_GENERAL_LEDGER}")
    public String HF_GENERAL_LEDGER;
    @Value("${PaymentRequest.HF_GENERAL_LEDGER_NAME}")
    public String HF_GENERAL_LEDGER_NAME;

    /** 澳门总账 */
    @Value("${PaymentRequest.AM_GENERAL_LEDGER}")
    public String AM_GENERAL_LEDGER;
    @Value("${PaymentRequest.AM_GENERAL_LEDGER_NAME}")
    public String AM_GENERAL_LEDGER_NAME;

}

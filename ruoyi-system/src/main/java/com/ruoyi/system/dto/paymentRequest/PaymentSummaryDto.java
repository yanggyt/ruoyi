package com.ruoyi.system.dto.paymentRequest;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 生成性支出汇总
 *
 * @author SKaiL
 * @version 1.0
 * @date 2022/6/10 15:53
 */
@Data
public class PaymentSummaryDto {

    @Excel(name = "流水号")
    private Long id;

    @Excel(name = "申请人编号")
    private String userCode;
    private Boolean userCodeFlag = true;

    @Excel(name = "付款日期", dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;
    private Boolean paymentDateFlag = true;

    @Excel(name = "公司别", readConverterExp = "6=NJ,20=NJHK,14=NJSH")
    private String company;
    private Boolean companyFlag = true;

    @Excel(name = "请款种类")
    private String type;

    @Excel(name = "供应商编号")
    private String supplierCode;
    private Boolean supplierCodeFlag = true;

    @Excel(name = "供应商名称")
    private String supplierName;
    private Boolean supplierNameFlag = true;

    @Excel(name = "请款币别", readConverterExp = "1=RMB,2=USD,3=TWD,4=HKD,5=KRW,6=MOP")
    private String currency;
    private Boolean currencyFlag = true;

    @Excel(name = "请款金额")
    private String amount;
    private Boolean amountFlag = true;

    @Excel(name = "请款数量")
    private String quantity;
    private Boolean quantityFlag = true;

    @Excel(name = "进货期间")
    private String deliveryTimeRange;

    @Excel(name = "备注")
    private String remark;

    /** 收款人 */
    private String payee;

    /** 收款银行 */
    private String bankName;

    /** 收款账号 */
    private String accountNumber;

    /** 收款人地址 */
    private String address;

    /** 开户行代码 */
    private String swiftCode;

    /** 开户行地址 */
    private String bankAddress;


}

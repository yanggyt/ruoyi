package com.ruoyi.system.dto.paymentRequest;


import com.ruoyi.common.annotation.Excel;
import lombok.Data;


/**
 * 明细汇总
 *
 * @author zys
 * @version 1.0
 * @date 2022/8/2
 */
@Data
public class PaymentRequestDt1Dto {
    
    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;
    private Boolean projectCodeFlag = true;
    
    /** 摘 要 说 明 */
    @Excel(name = "摘要说明")
    private String content;
    private Boolean contentFlag = true;   
    
    /** 单位 */
    @Excel(name = "单位")
    private String unit;
    private Boolean unitFlag = true;

    @Excel(name = "数量")
    private String quantity;
    private Boolean quantityFlag = true;

    /** 单价 */
    @Excel(name = "单价")
    private String unitPrice;
    private Boolean unitPriceFlag = true;
    
    /** 总金额 */
    @Excel(name = "总金额")
    private String totalAmount;
    private Boolean totalAmountFlag = true;
    
    @Excel(name = "请款币别", readConverterExp = "1=RMB,2=USD,3=TWD,4=HKD,5=KRW,6=MOP")
    private String currency;
    private Boolean currencyFlag = true;
    
    @Excel(name = "备注")
    private String remark;

}

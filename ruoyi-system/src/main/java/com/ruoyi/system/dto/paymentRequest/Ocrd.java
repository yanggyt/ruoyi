package com.ruoyi.system.dto.paymentRequest;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 业务伙伴
 *
 * @author SKaiL
 * @date 2022/6/24 14:57
 * @version 1.0
 */
@Data
public class Ocrd extends BaseEntity {

    private Long id;

    private Long parentId;

    /** 代码 */
    private String cardCode;

    /** 名称 */
    private String cardName;

    /** 银行名称 */
    private String uBankname;

    /** 银行账号 */
    private String uBankaccount;

    /** swift Code */
    private String uBankswift;

    /** 开户行地址 */
    private String uBankaddress;

    /** 收款人地址 */
    private String uBankreceaddress;

}

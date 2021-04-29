package com.ruoyi.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * product对象 cbfa_apply_user
 *
 * @author ruoyi
 * @date 2021-04-29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ApplyUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String sex;

    private String age;

    private String enterpriseName;

    private String idcrds;

    private String importEnterprise;

    private String establishment;

    private String proportion;

    private String proportionUserMax;

    private String shareholding;

    private String administrativePenalty;

    private String significantLawsuit;

    private String overdue;

    private String badRecord;

    private String lastYearSaleroom;

    private String homochronousSaleroom;

    private String makeOutAninvoice;

    private String declareCount;

    private String recentSaleroom;

    private String makeOutAninvoiceTime;

    private String ratepayingTime;

    private String ratepayingLimit;

    private String ratepayingRecent;

    private String brackets;

    private String loansOverdueCount;

    private String blacklist;

    private String creditCard;

    private String loansExamine;

    private String liabilities;

    private String liabilitiesProbability;

    private String companyLoanOverdue;

    private String totalAmountOfBorrowing;

    private String lineOfCredit;

    private String yearTransaction;

    private String clearanceCount;

    private String halfaYearSum;

    private String legalPerson;

    private String legalPersonRecord;

    private String promiseEnterprise;

    private String atPresentOverdue;

    private String yearPerson;

    private String investigation;

}

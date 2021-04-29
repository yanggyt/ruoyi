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
 * product对象 cbfa_product_information
 *
 * @author ruoyi
 * @date 2021-04-29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductInformation
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String productName;

    private String productDescribe;

    private String productDescribeB;

    private String productDescribeC;

}

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
 * product对象 cbfa_product_tag
 *
 * @author ruoyi
 * @date 2021-04-29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String beanName;

    private Long rules;

    private String instructions;


}

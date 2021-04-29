package com.ruoyi.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * product对象 cbfa_product
 *
 * @author ruoyi
 * @date 2021-04-29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long procuctInformationId;

    private Long procuctTagId;

    private String requested;

    private String requiremssage;

    private String beanName;

    private Long rules;

    private String instructions;



}

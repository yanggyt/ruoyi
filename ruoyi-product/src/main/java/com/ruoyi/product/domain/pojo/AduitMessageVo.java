package com.ruoyi.product.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: Wang
 * @Date: 2021/04/27 20:41
 * 功能描述: 记录单个产品的是否准入信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AduitMessageVo {

    private Long id;
    private StringBuilder success;//成功信息
    private StringBuilder loser;//失败信息
    private Boolean flag;//是否完全通过



}

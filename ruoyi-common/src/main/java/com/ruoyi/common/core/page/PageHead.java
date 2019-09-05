package com.ruoyi.common.core.page;

import com.ruoyi.common.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PageHead
 *
 * @author solo
 * @date 2019-09-05
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageHead extends BaseBean {

    @ApiModelProperty(value = "排序字段", example = "id")
    private String sort;

    @ApiModelProperty(value = "排序方式", example = "desc")
    private String order;

    @ApiModelProperty(value = "当前页数", example = "1")
    private Integer page = 1;

    @ApiModelProperty(value = "每页多少数据", example = "10")
    private Integer rows = 10;

}

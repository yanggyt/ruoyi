package com.ruoyi.common.core.page;

import com.ruoyi.common.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PageResult
 * @author solo
 * @date 2019-09-05
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageResult<T> extends BaseBean {

    @ApiModelProperty(value = "总页数", example = "12")
    private Long totalCount;

    @ApiModelProperty(value = "对象")
    private T body;
}

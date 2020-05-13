package com.ruoyi.common.core.domain;

import com.ruoyi.common.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * 协议请求
 *
 * @author solo
 * @date 2019/09/05.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Request<T> extends BaseBean {

    @ApiModelProperty(value = "序列码", example = "a006910b-5274-4514-89c6-5ca08aa19e60")
    private String serialNum;

    @ApiModelProperty(value = "请求内容")
    private T data;
}

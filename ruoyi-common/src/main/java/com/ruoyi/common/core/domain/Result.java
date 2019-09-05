package com.ruoyi.common.core.domain;

import com.ruoyi.common.base.BaseBean;
import com.ruoyi.common.result.ErrorCodes;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author solo
 * @date 2019/09/05
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Result extends BaseBean {

    @ApiModelProperty(value = "响应提示码", example = "1000")
    private ErrorCodes errorCode;

    @ApiModelProperty(value = "响应内容")
    private Object data;

    /**
     * 操作成功
     *
     * @return
     */
    public static Result ok() {
        return Result.builder().errorCode(ErrorCodes.SUCCESS).build();
    }

    /**
     * 操作成功
     *
     * @param data 返回值
     * @return
     */
    public static Result ok(Object data) {
        return Result.builder().data(data).errorCode(ErrorCodes.SUCCESS).build();
    }


    /**
     * 操作失败
     *
     * @param errorCode 失败明细
     * @return
     */
    public static Result fail(ErrorCodes errorCode) {
        return Result.builder().data(errorCode.getMessage()).errorCode(errorCode).build();
    }

    /**
     * 操作失败
     *
     * @param data 失败明细
     * @return
     */
    public static Result fail(Object data) {
        return Result.builder().data(data).errorCode(ErrorCodes.FAIL).build();
    }

    /**
     * 是否操作成功
     *
     * @return
     */
    public boolean isSuccess() {
        return errorCode.getCode() == 0;
    }
}

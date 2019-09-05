package com.ruoyi.common.core.domain;

import com.ruoyi.common.base.BaseBean;
import com.ruoyi.common.result.ErrorCodes;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * Service返回
 *
 * @author solo
 * @date 2019/09/05.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ServiceResult<T> extends BaseBean {
    @ApiModelProperty(value = "序列码", example = "a006910b-5274-4514-89c6-5ca08aa19e60")
    private String serialNum;

    @ApiModelProperty(value = "错误码，0 表示成功", example = "0")
    private Integer errorCode;

    @ApiModelProperty(value = "提示语", example = "操作成功")
    private String message;

    @ApiModelProperty(value = "记录总数", example = "12")
    private Long total;

    @ApiModelProperty(value = "响应内容")
    private T rows;

    /**
     * 操作成功
     */
    public static ServiceResult ok() {
        return ok(ErrorCodes.SUCCESS);
    }

    /**
     * 操作成功
     */
    public static ServiceResult ok(Object data) {
        return ServiceResult.builder()
                .errorCode(ErrorCodes.SUCCESS.getCode())
                .message(ErrorCodes.SUCCESS.getMessage())
                .rows(data)
                .build();
    }


    /**
     * 操作成功
     */
    public static ServiceResult ok(ErrorCodes errorCode) {
        return ServiceResult.builder()
                .errorCode(ErrorCodes.SUCCESS.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    /**
     * 操作成功 可自定义成功响应描述
     *
     * @param data
     * @param message
     * @return
     */
    public static ServiceResult ok(Object data, String message, Long count) {
        return ServiceResult.builder()
                .errorCode(ErrorCodes.SUCCESS.getCode())
                .message(message)
                .rows(data)
                .total(count)
                .build();
    }

    /**
     * 操作成功 可自定义成功响应描述
     *
     * @param data
     * @param message
     * @return
     */
    public static ServiceResult ok(Object data, String message) {
        return ServiceResult.builder()
                .errorCode(ErrorCodes.SUCCESS.getCode())
                .message(message)
                .rows(data)
                .build();
    }

    /**
     * 操作成功 可自定义成功响应描述
     *
     * @param data
     * @param count
     * @return
     */
    public static ServiceResult ok(Object data, Long count) {
        return ServiceResult.builder()
                .errorCode(ErrorCodes.SUCCESS.getCode())
                .message(ErrorCodes.SUCCESS.getMessage())
                .total(count)
                .rows(data)
                .build();
    }

    /**
     * 返回失败原因信息
     */
    public static ServiceResult fail(ErrorCodes errorCode) {
        return ServiceResult.builder()
                .errorCode(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    /**
     * 返回失败原因信息
     */
    public static ServiceResult fail(ErrorCodes errorCode,Object data) {
        return ServiceResult.builder()
                .errorCode(errorCode.getCode())
                .message(errorCode.getMessage())
                .rows(data)
                .build();
    }

    /**
     * 返回失败原因信息
     */
    public static ServiceResult fail(String message,Object data) {
        return ServiceResult.builder()
                .errorCode(ErrorCodes.FAIL.getCode())
                .message(message)
                .rows(data)
                .build();
    }

    /**
     * 返回失败原因信息
     */
    public static ServiceResult fail(String message) {
        return ServiceResult.builder()
                .errorCode(ErrorCodes.FAIL.getCode())
                .message(message)
                .build();
    }

    /**
     * 操作失败，默认错误
     */
    public static ServiceResult fail() {
        return ServiceResult.builder()
                .errorCode(ErrorCodes.FAIL.getCode())
                .message(ErrorCodes.FAIL.getMessage())
                .build();
    }
}

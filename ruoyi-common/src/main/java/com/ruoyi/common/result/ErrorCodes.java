package com.ruoyi.common.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统错误码
 *
 * @author solo
 * @date 2018/2/23.
 */
public enum ErrorCodes {
    /**
     * 错误码
     */
    FAIL(-1, "操作失败"),
    SUCCESS(0, "操作成功"),
    ;

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.ruoyi.tts.exception;

/**
 * @author GideonYeung
 * @date 2020/7/7 9:49
 */
public class SerialPortParameterFailureException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SerialPortParameterFailureException() {
    }

    @Override
    public String toString() {
        return "设置串口参数失败！打开串口操作未完成！";
    }
}

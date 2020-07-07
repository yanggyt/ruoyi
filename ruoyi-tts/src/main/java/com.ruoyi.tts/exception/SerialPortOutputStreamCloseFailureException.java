package com.ruoyi.tts.exception;

/**
 * @author GideonYeung
 * @date 2020/7/7 9:55
 */
public class SerialPortOutputStreamCloseFailureException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SerialPortOutputStreamCloseFailureException() {
    }

    @Override
    public String toString() {
        return "关闭串口对象的输出流出错";
    }
}

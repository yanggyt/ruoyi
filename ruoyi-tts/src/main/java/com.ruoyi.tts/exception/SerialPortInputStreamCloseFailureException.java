package com.ruoyi.tts.exception;

/**
 * @author GideonYeung
 * @date 2020/7/7 9:58
 */
public class SerialPortInputStreamCloseFailureException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SerialPortInputStreamCloseFailureException() {
    }

    @Override
    public String toString() {
        return "关闭串口对象输入流出错";
    }
}

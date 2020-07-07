package com.ruoyi.tts.exception;

/**
 * @author GideonYeung
 * @date 2020/7/7 9:51
 */
public class NotSerialPortException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public NotSerialPortException() {
    }
    @Override
    public String toString() {
        return "端口指向设备不是串口类型！";
    }
}

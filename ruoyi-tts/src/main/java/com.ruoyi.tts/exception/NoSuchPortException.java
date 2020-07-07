package com.ruoyi.tts.exception;

/**
 * @author GideonYeung
 * @date 2020/7/7 9:50
 */
public class NoSuchPortException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoSuchPortException() {
    }
    @Override
    public String toString() {
        return "没有该端口对应的串口设备！";
    }
}

package com.ruoyi.tts.exception;

/**
 * @author GideonYeung
 * @date 2020/7/7 9:57
 */
public class ReadDataFromSerialPortFailureException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ReadDataFromSerialPortFailureException() {
    }

    @Override
    public String toString() {
        return "从串口读取数据时出错";
    }
}

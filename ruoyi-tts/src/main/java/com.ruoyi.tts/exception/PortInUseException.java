package com.ruoyi.tts.exception;

/**
 * @author GideonYeung
 * @date 2020/7/7 9:52
 */
public class PortInUseException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public PortInUseException() {
    }
    @Override
    public String toString() {
        return "端口已被占用！";
    }
}

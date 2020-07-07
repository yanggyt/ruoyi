package com.ruoyi.tts.exception;

/**
 * @author GideonYeung
 * @date 2020/7/7 9:59
 */
public class TooManyListenersException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TooManyListenersException() {
    }

    @Override
    public String toString() {
        return "监听类对象过多！";
    }
}

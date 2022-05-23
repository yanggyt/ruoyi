package com.ruoyi.common.core.domain;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 表格分页数据对象
 *
 * @author ruoyi
 */
public class Result<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private T data;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;

    /**
     * 表格数据对象
     */
    public Result()
    {
        this.code=200;
    }
    /**
     * 表格数据对象
     */
    public Result(int code, String msg)
    {
        this.code=code;
        this.msg=msg;
    }
    /**
     * 表格数据对象
     */
    public Result(T data)
    {
        this.code=200;
        this.data=data;
    }
    /**
     * 表格数据对象
     */
    public Result(int code)
    {
        this.code=code;
    }
    /**
     * 分页
     *
     * @param data 列表数据
     * @param total 总记录数
     */
    public Result(T data, int total)
    {
        this.data = data;
        this.total = total;
    }



    public Result(int code, String msg, T data)
    {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(int code, String msg, T data, int total)
    {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.total = total;
    }


    public static Result success(){
        return new Result();
    }
    public static <T> Result success(T data){
        return new Result(data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static Result error()
    {
        return Result.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(String msg)
    {
        return Result.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> Result error(String msg, T data)
    {
        return new Result(500, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(int code, String msg)
    {
        return new Result(code, msg, null);
    }


    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

}

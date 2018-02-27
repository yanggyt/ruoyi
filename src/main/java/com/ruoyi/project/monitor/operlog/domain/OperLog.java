package com.ruoyi.project.monitor.operlog.domain;

import java.util.Date;
import lombok.Data;

/**
 * 操作日志记录 oper_log
 * 
 * @author yangzz
 */
@Data
public class OperLog
{
    // 日志主键
    private Integer operId;
    // 模块标题
    private String title;
    // 功能请求
    private String action;
    // 来源渠道
    private String channel;
    // 操作员名称
    private String loginName;
    // 部门名称
    private String deptName;
    // 请求url
    private String opertUrl;
    // 操作地址
    private String opertIp;
    // 请求参数
    private String opertParam;
    // 状态0正常 1异常
    private int status;
    // 错误消息
    private String errorMsg;
    // 操作时间
    private Date operTime;
}

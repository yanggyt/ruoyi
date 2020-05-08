package com.ruoyi.quartz.task;

import org.springframework.stereotype.Component;

/**
 * @program: RuoYi
 * @description: 定时任务调度测试2
 * @author: hulang
 * @since: 2020-04-11 23:27
 */
@Component("myTask")
public class MyTask {
    
    /**
     * test task
     */
    public void testTask() {
        System.out.println("打印定时任务测试日志");
    }
}

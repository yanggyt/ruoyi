/**
 * 
 */
package com.ruoyi.quartz.task;

import org.springframework.stereotype.Component;

/**
 * @author icytail
 *
 */
@Component("MyTask")
public class MyTask {

	public void foc() {
		System.out.println("自定义的定时调度");
	}
}

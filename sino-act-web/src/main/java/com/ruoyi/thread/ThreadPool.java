package com.ruoyi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	// 单例线程池--抽奖逻辑
	public static final ExecutorService drawActivityExecutorService = Executors.newFixedThreadPool(1000);
	public static final ExecutorService awardExecutorService = Executors.newFixedThreadPool(1000);
	//短信发送线程池
	public static final ExecutorService smsExecutorService= Executors.newFixedThreadPool(100);
}

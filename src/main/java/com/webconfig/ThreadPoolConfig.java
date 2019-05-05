package com.webconfig;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig
{
	@Bean("asyncAddExecutor")
	public Executor asyncAddExecutor()
	{
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 核心线程数：正常情况下开启的线程数量
		executor.setCorePoolSize(10);
		// 当核心线程都在跑任务，还有多余的任务会存在此处
		executor.setQueueCapacity(200);
		// 最大线程数：如果QueueCapacity存满了，就会启动更多的线程，直到线程数达到maxPoolSize。
		// 如果还有任务，则根据拒绝策略进行处理。
		executor.setMaxPoolSize(20);
		// 线程空闲时间：空闲时间超过的会被销毁（不包括核心线程）
		executor.setKeepAliveSeconds(60);
		// 线程名称前缀
		executor.setThreadNamePrefix("asyncAddExecutor-");
		// 拒绝策略：不在新线程中执行任务，而是由调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 拒绝策略：直接抛异常拒绝
		// executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		executor.initialize();
		return executor;
	}
}

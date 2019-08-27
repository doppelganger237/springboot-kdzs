package com.zhang.kdzs.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/10 22:25
 * @description：
 * @modified By：
 */

@Configuration
@EnableAsync
public class SpringTaskExecutor implements AsyncConfigurer {


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(20);

        // 修复主线程结束子线程结束的问题 Junit里面没效果
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);


        taskExecutor.initialize();
        return taskExecutor;
         }


}

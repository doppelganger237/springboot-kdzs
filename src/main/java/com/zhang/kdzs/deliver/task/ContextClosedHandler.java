package com.zhang.kdzs.deliver.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 11:57
 * @description：
 * @modified By：
 */
public class ContextClosedHandler implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    ThreadPoolTaskExecutor executor;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        executor.shutdown();
    }
}

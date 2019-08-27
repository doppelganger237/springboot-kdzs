package com.zhang.kdzs.deliver.task;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 10:12
 * @description：
 * @modified By：
 */
@Component
public class WuliuFactory implements ApplicationContextAware {

    private  Map<Long, IWuLiuTask> taskMap;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IWuLiuTask> map = applicationContext.getBeansOfType(IWuLiuTask.class);
        taskMap = new HashMap<>();
        map.forEach((key, value) -> taskMap.put(value.getID(), value));

    }

    // 这用的着泛型吗??
    public  <T extends IWuLiuTask> T getTask(long code) {
        return (T) taskMap.get(code);
    }

}

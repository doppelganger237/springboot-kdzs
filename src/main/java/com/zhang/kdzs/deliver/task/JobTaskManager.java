package com.zhang.kdzs.deliver.task;

import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 9:35
 * @description：
 * @modified By：
 */
@Component
@Slf4j
public class JobTaskManager {

    @Autowired
    WuliuTaskExecutor wuliuTaskExecutor;

/*
    private Map<Integer, LogisticsJob> taskContainer = new HashMap<>();
*/


    // 主线程结束子线程也会结束 在实际应用中可能会出问题 还没找到好方法修复
    public void submit(LogisticsJob logisticsJob) {

      // taskContainer.put(logisticsJob.getId(),logisticsJob);
        wuliuTaskExecutor.executor(logisticsJob);
    }

    /**
    * @Description: 获得任务完成的百分比 ex.100
    * @Param: [job]
    * @return: float
    * @date: 2019/8/18
     */
    public float getJobFinishPercent(LogisticsJob job){
        int allSize = job.getUseCompanyId().size();
        Optional<List<LogisticsResult>> o = Optional.ofNullable(job.getResults());
        
        int doneSize= o.orElse(Collections.emptyList()).stream().collect(Collectors.groupingBy(j->j.getParentId())).size();
        return   Math.round((float)doneSize/allSize*100);


    }



}

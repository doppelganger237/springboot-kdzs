package com.zhang.kdzs.deliver.task;

import com.zhang.kdzs.area.entity.Adress;
import com.zhang.kdzs.area.service.AreaService;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import com.zhang.kdzs.deliver.serivce.DeliverService;
import com.zhang.kdzs.deliver.task.handler.RestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 9:48
 * @description：
 * @modified By：
 */

@Component
@Slf4j
public class WuliuTaskExecutor {


    @Autowired
    DeliverService deliverService;


    @Autowired
    private AreaService areaService;

    @Autowired
    private WuliuFactory factory;

    @Autowired
    private RestHandler restHandler;

    @Async
    public void executor(LogisticsJob job) {

        log.info("执行异步任务" + job.getId());

        //骚的一批lambda 我不知道这效率怎么样 这两个要是堆一起更不用看了
        List<Long> alreadyHandle = Optional.ofNullable(job.getResults()).orElse(Collections.emptyList()).stream().map(LogisticsResult::getParentId).collect(Collectors.toList());
        List<Long> shouldHanlde = job.getUseCompanyId().stream().filter(item -> !alreadyHandle.contains(item)).collect(Collectors.toList());


        Adress from = areaService.fromAreaCode(job.getFrom());
        Adress to = areaService.fromAreaCode(job.getDestination());

        for (Long id : shouldHanlde) {

            IWuLiuTask task = factory.getTask(id);
            if (task == null) {
                log.info("不支持物流ID" + id);
                continue;
            }
            List<LogisticsResult> results = new ArrayList<>();
            try {
                List<Object> data = restHandler.hanlder(task, job, from, to);
                results = task.handleResult(job, data);

            } catch (Exception e) {
                // 以后换成日记记录异常
                e.printStackTrace();
            }
            if (results != null && (!results.isEmpty())) {
                deliverService.addResultList(results);
            }

        }
        job.setStatus(1);
        log.info("保存Job");
        log.info(job.toString());
        deliverService.updateJob(job);
        // log.info(job.getResults().toString());


    }
}

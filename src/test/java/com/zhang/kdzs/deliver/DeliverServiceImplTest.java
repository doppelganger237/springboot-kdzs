package com.zhang.kdzs.deliver;

import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import com.zhang.kdzs.deliver.serivce.DeliverService;
import com.zhang.kdzs.deliver.task.JobTaskManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 8:32
 * @description：
 * @modified By：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Rollback(value = false)
public class DeliverServiceImplTest {


    @Autowired
    private DeliverService deliverService;

    @Autowired
    private JobTaskManager manager;

    @Test
    public void addJob(){

       /*LogisticsJob job = new LogisticsJob();

        Map<String,Double> map = new HashMap<>();
        map.put("DeBang",0.0);
        map.put("ShunFeng",0.0);

        job.setFrom("310105");
        job.setDestination("210106");

        job.setWeight(1D);
        job.setResults(map);

        manager.submit(job);

        while (true);*/
        //deliverService.saveJob(job);

    }

    @Test
    public void getJob() {

        LogisticsJob job=deliverService.getJobById(9);

        log.info(job.toString());
        log.info(job.getResults().toString());

        List<LogisticsResult> list = new ArrayList<>();
/*
        list.add(new LogisticsResult(1L,9L,"abc","abc",1,1D));
        list.add(new LogisticsResult(1L,9L,"abc","abc",1,1D));*/
//deliverService.addResult(new LogisticsResult(5L,9L,"abc","abc",1,1D));
        deliverService.addResultList(list);

        /*
        LogisticsJob job = deliverService.getJobById(1);
        log.info(job.toString());
*/
    }
}
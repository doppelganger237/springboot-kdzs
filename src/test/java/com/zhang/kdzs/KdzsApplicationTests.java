package com.zhang.kdzs;

import com.zhang.kdzs.deliver.serivce.DeliverService;
import com.zhang.kdzs.deliver.task.JobTaskManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KdzsApplicationTests {

	@Autowired
	private JobTaskManager manager;

	@Autowired
	private DeliverService deliverService;

	@Test
	public void contextLoads() {
	}


	@Test
	public void temp(){
log.info(
		manager.getJobFinishPercent(deliverService.getJobById(48))+"%");


	}

}

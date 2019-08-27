package com.zhang.kdzs;

import com.zhang.kdzs.deliver.task.WuliuFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 10:30
 * @description：
 * @modified By：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WuliuSelectorTest {

    @Autowired
    WuliuFactory selector;

    @Test
    public void test(){

  //  IWuLiuTask constructor=selector.getTask(1);

  //  log.info("id"+constructor.getID());
    }

}
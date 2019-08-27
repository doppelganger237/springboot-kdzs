package com.zhang.kdzs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/27 10:27
 * @description：
 * @modified By：
 */

@Slf4j
public class JavaTest {

    @Test
    public void test(){

        Double data = 166.6D;

        Double bigDecimal=new BigDecimal(data).setScale(0,BigDecimal.ROUND_CEILING     ).doubleValue();

        log.info(bigDecimal+"");


    }

}

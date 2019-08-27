package com.zhang.kdzs.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/8 21:33
 * @description：日记记录
 * @modified By：
 */

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());



}

package com.zhang.kdzs.deliver.task.handler;

import org.springframework.http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/24 19:33
 * @description：
 * @modified By：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RestClient {

    HttpMethod method() default HttpMethod.GET;

    String url();

    Class type() default Object.class;

    boolean useJson() default false;



}

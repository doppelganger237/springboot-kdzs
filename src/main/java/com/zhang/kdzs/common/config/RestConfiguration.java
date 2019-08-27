package com.zhang.kdzs.common.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 23:51
 * @description：
 * @modified By：
 */
@Configuration
public class RestConfiguration {


    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();

    }


}

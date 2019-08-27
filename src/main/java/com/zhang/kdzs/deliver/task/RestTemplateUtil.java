package com.zhang.kdzs.deliver.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/20 23:06
 * @description：
 * @modified By：
 */
@Component
public class RestTemplateUtil {

    @Autowired
    private RestTemplate restTemplate;

    public <T>T PostFormData(String url, MultiValueMap<String, String> param,Class<T> tClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, headers);

        T object = restTemplate.postForEntity(url, request,tClass).getBody();
        return object;

    }


}

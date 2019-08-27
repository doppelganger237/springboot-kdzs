package com.zhang.kdzs.deliver.task.handler;

import com.zhang.kdzs.area.entity.Adress;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/24 19:44
 * @description：
 * @modified By：
 */

@Slf4j
@Component
public class RestHandler {


    @Autowired
    private RestTemplate restTemplate;


    public List<Object> hanlder(Object object, LogisticsJob job, Adress from, Adress to) {

        List<Object> list = new ArrayList();

        for (Method method : object.getClass().getMethods()) {
            RestClient client = method.getAnnotation(RestClient.class);

            if (client != null) {
                try {

                    Object data = method.invoke(object, job, from, to);
                    //获得要提交的数据 没有处理void
                    if (method.getReturnType() != null && data == null) {
                        continue;
                    }

                    log.info(data.toString());
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity httpEntity = null;
                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(client.url());

                    if (client.method().equals(HttpMethod.POST)) {
                        httpEntity = new HttpEntity(data, headers);
                        if (client.useJson()) {
                            headers.setContentType(MediaType.APPLICATION_JSON);
                        } else {
                            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                        }
                    } else {
                        httpEntity = new HttpEntity(headers);

                        Map<String, Object> map = (Map<String, Object>) data;

                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            builder.queryParam(entry.getKey(), entry.getValue());
                        }

                    }


                    Object restObject = restTemplate.exchange(builder.build(true).toUri(), client.method(), httpEntity, client.type()).getBody();
                    log.info(restObject.toString());
                    list.add(restObject);

                } catch (Exception e) {
                    e.printStackTrace();
                    return list;
                }

            }

        }

        return list;


    }


}

package com.zhang.kdzs.deliver.task.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhang.kdzs.area.entity.Adress;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import com.zhang.kdzs.deliver.task.IWuLiuTask;
import com.zhang.kdzs.deliver.task.handler.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 10:00
 * @description：
 * @modified By：
 */
@Slf4j
@Component
public class ShunFeng implements IWuLiuTask {


    @Override
    public long getID() {
        return 2;
    }



    @RestClient(url = "http://www.sf-express.com/sf-service-owf-web/service/rate/newRates",method = HttpMethod.GET,type = JSONArray.class)
    public Map<String,Object> commonGet(LogisticsJob job,Adress from,Adress to){
        return getStringObjectMap(job, from, to, 2);
    }

    private Map<String, Object> getStringObjectMap(LogisticsJob job, Adress from, Adress to, int type) {
        Map<String,Object > map = new HashMap<>();
        map.put("origin","A"+from.getArea().getCode()+"000");
        map.put("dest","A"+to.getArea().getCode()+"000");
        map.put("weight",job.getWeight());
        map.put("volume",job.getVolume().calculateTotal());
        map.put("time", getDateWithEncoding());
        map.put("queryType", type);
        map.put("lang","sc");
        map.put("region","cn");

        return map;
    }


    @RestClient(url = "http://www.sf-express.com/sf-service-owf-web/service/rate/ratesByHeavyCargo",method = HttpMethod.GET,type = JSONArray.class)
    public Map<String,Object> heavyGet(LogisticsJob job,Adress from,Adress to){
        if(!(job.getWeight()>=20)){
            return null;
        }
        return getStringObjectMap(job, from, to, 1);
    }

    @Override
    public List<LogisticsResult> handleResult(LogisticsJob job, List<Object> objects) {
        log.info("使用顺丰处理物流");
        List<LogisticsResult> results = new ArrayList<>();
        for(Object o:objects){
            JSONArray jsonArray=(JSONArray) o;
            for (int i=0;i<jsonArray.size();i++ ) {
                JSONObject next =  jsonArray.getJSONObject(i);
                String name = next.getString("limitTypeName");
                Double money = next.getDouble("freight");
                LogisticsResult result = new LogisticsResult(job.getId(),name,getID(),money);
                results.add(result);
            }

        }

        return results;
    }



    private String getDateWithEncoding(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String time = sdf.format(new Date());
        try{
            time =  URLEncoder.encode(time,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();;
        }

        return time;
    }


}

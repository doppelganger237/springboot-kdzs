package com.zhang.kdzs.deliver.task.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhang.kdzs.area.entity.Adress;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import com.zhang.kdzs.deliver.task.IWuLiuTask;
import com.zhang.kdzs.deliver.task.handler.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 10:00
 * @description：
 * @modified By：
 */
@Slf4j
@Component
public class DeBang implements IWuLiuTask {

    @Override
    public long getID() {
        return 1L;
    }

    @RestClient(method = HttpMethod.POST, url = "https://www.deppon.com/phonerest/price/ordersFreightReckon", useJson = true, type = JSONObject.class)
    public JSONObject postTest(LogisticsJob job, Adress from, Adress to) {
        JSONObject data = new JSONObject();
        //不玩驼峰了?
        data.put("originalsaddress", from.getProvince().getName() + "-" + from.getCity().getName() + "-" + from.getArea().getName());
        data.put("originalsStreet", to.getProvince().getName() + "-" + to.getCity().getName() + "-" + to.getArea().getName());
        data.put("packageWeightFlag", 0);
        data.put("totalWeight", job.getWeight());


        Double m3 = new BigDecimal(job.getVolume().calculateTotal() / 1000000).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        data.put("totalVolume", m3);


        data.put("client", true);
        return data;
    }


    @Override
    public List<LogisticsResult> handleResult(LogisticsJob job, List<Object> objects) {
        log.info("使用德邦物流处理数据");
        JSONObject object = (JSONObject) objects.get(0);
        log.info(object.toJSONString());

        JSONArray array = object.getJSONArray("result");

        List<LogisticsResult> results = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {

            JSONObject next = array.getJSONObject(i);
            String name = next.getString("productName");
            Double money = next.getDouble("totalfee");

            LogisticsResult result = new LogisticsResult(job.getId(), name, getID(), money);
            results.add(result);
        }

        log.info(array.toJSONString());
        return results;
    }


}

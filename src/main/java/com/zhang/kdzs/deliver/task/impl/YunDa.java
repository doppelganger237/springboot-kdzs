package com.zhang.kdzs.deliver.task.impl;

import com.zhang.kdzs.area.entity.Adress;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import com.zhang.kdzs.deliver.task.IWuLiuTask;
import com.zhang.kdzs.deliver.task.handler.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/25 10:25
 * @description：
 * @modified By：
 */
@Component
@Slf4j
public class YunDa implements IWuLiuTask{


    @Override
    public long getID() {
        return 5;
    }



    @RestClient(url = "http://www.yundaex.com/cn/data/search.php", method = HttpMethod.POST, type = String.class)
    public MultiValueMap<String, Object> form(LogisticsJob job, Adress from, Adress to) {

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("act", "Getyunfei");
        map.add("sp", from.getProvince().getCode());
        map.add("ss", from.getCity().getCode());
        map.add("ss", from.getArea().getCode());


        map.add("mp", to.getProvince().getCode());
        map.add("ms", to.getCity().getCode());
        map.add("ms", to.getArea().getCode());


        //韵达这算法太蠢了吧?
        Double volume = new BigDecimal(job.getVolume().calculateTotal()/6000).setScale(0,BigDecimal.ROUND_CEILING     ).doubleValue();

        // 哪个重量大用哪个
        if(volume<job.getWeight()){
            volume = job.getWeight();
        }

        map.add("zl",volume);
        return map;

    }



    @Override
    public List<LogisticsResult> handleResult(LogisticsJob job, List<Object> objects) {

        log.info("使用韵达处理");
        String weight = (String) objects.get(0);
        //懒

        return Arrays.asList(LogisticsResult.builder().parentId(getID()).jobId(job.getId()).name("韵达通用").price(Double.valueOf(weight)).build());

    }
}

package com.zhang.kdzs;

import com.alibaba.fastjson.JSONObject;
import com.zhang.kdzs.deliver.task.handler.RestClient;
import com.zhang.kdzs.deliver.task.handler.RestHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/24 20:02
 * @description：
 * @modified By：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class RestHandlerTest {


    @SpyBean
    private RestHandler restHandler;





    @Test
    public void hanlder() throws Throwable {

        // restHandler.around(proceedingJoinPoint,getClass().getMethod("fromTest").getAnnotation(RestClient.class));
    }

    @RestClient(method = HttpMethod.POST, url = "https://www.deppon.com/phonerest/price/ordersFreightReckon", useJson = true, type = JSONObject.class)
    public JSONObject postTest() {

        JSONObject data = new JSONObject();

        data.put("originalsaddress", "湖北省-荆门市-东宝区");
        data.put("originalsStreet", "江苏省-苏州市-姑苏区");
        data.put("packageWeightFlag", 0);
        data.put("totalVolume", 0.001);
        data.put("totalWeight", 2D);
        data.put("totalVolume", 0);
        data.put("client", true);

        return data;

    }

    @RestClient(method = HttpMethod.POST, url = "http://www.yto.net.cn/api/base/freight", type = JSONObject.class)
    public MultiValueMap<String, String> fromTest() {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("fromLabel", "湖北省-荆门市");
        map.add("fromProvinceCode", "420000");
        map.add("fromProvinceName", "湖北省");
        map.add("fromCityCode", "448000");
        map.add("fromCityName", "荆门市");


        map.add("toLabel", "江苏省-苏州市");
        map.add("toProvinceCode", "32000");
        map.add("toProvinceName", "江苏省");
        map.add("toCityCode", "215000");
        map.add("toCityName", "苏州");
        map.add("weight", "3");

        return map;


    }


}
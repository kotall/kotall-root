package com.kotall.oms.thirdapi.weather;

import com.google.gson.Gson;
import com.kotall.oms.thirdapi.weather.baidu.BaiDuWeatherResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/5/2
 */
@Slf4j
@Component("baiDuWeatherApi")
public class BaiDuWeatherApi {

    private static final String AP = "C57d8969e054b27c4cbc2dd845502aa2";

    private static final String URL = "http://api.map.baidu.com/telematics/v3/weather?location=LOCATION&output=json&ak=AP";

    @Autowired
    RestTemplate restTemplate;

    public BaiDuWeatherResult queryWithJson(String location) {
        BaiDuWeatherResult result = null;
        try {
            String url = URL.replaceAll("LOCATION", location).replaceAll("AP", AP);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            if (HttpStatus.SC_OK == responseEntity.getStatusCode().value()) {
                Gson gson = new Gson();
                result = gson.fromJson(responseEntity.getBody(), BaiDuWeatherResult.class);
            }
        } catch (Exception e) {
            log.error("查询百度天气预报接口异常！", e);
        }
        log.info("查询百度天气预报结果：{}", result);

        return result;
    }
}

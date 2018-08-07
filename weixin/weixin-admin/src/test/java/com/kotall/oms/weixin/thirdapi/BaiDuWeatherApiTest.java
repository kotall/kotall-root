package com.kotall.oms.weixin.thirdapi;

import com.github.aracwong.weixin.utils.HttpClientUtil;
import org.junit.Test;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/5/2
 */
public class BaiDuWeatherApiTest {

    private static final String URL = "http://api.map.baidu.com/telematics/v3/weather?location=LOCATION&output=json&ak=AP";

    @Test
    public void testBaiDuWeatherApi() {
        String url = URL.replaceAll("LOCATION", "上海").replaceAll("AP", "C57d8969e054b27c4cbc2dd845502aa2");
        System.out.println(url);
        String json = HttpClientUtil.get(url, null);
        System.out.println(json);

    }
}

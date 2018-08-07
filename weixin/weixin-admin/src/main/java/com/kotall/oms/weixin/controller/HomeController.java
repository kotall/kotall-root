package com.kotall.oms.weixin.controller;

import com.kotall.oms.thirdapi.weather.BaiDuWeatherApi;
import com.kotall.oms.thirdapi.weather.baidu.BaiDuWeatherResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/4/29 0029 下午 3:44
 */
@Slf4j
@RestController
@RequestMapping("/wx")
public class HomeController {

    @Autowired
    BaiDuWeatherApi baiDuWeatherApi;

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        log.info("请求对象：{}", request);
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getParameter(name);
            log.info("=== 请求参数：{} = {}", name, value);
        }
        return "<h6>hello world!</h6>";
    }

    @GetMapping("/weather")
    public BaiDuWeatherResult weather(@RequestParam String location) {
        BaiDuWeatherResult data = this.baiDuWeatherApi.queryWithJson(location);
        return data;
    }
}

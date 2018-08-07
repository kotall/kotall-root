package com.kotall.oms.weixin.service.impl;

import com.github.aracwong.weixin.framework.annotation.WxHandler;
import com.github.aracwong.weixin.framework.constant.WxHandlerType;
import com.github.aracwong.weixin.framework.core.WxResponse;
import com.github.aracwong.weixin.framework.handler.text.DefaultWxTextRequestHandler;
import com.github.aracwong.weixin.framework.msg.text.WxTextMsgReq;
import com.github.aracwong.weixin.framework.msg.text.WxTextMsgResp;
import com.google.common.base.Strings;
import com.kotall.oms.thirdapi.weather.BaiDuWeatherApi;
import com.kotall.oms.thirdapi.weather.baidu.BaiDuWeatherResult;
import com.kotall.oms.weixin.service.WxTextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/5/1 0001 上午 10:38
 */
@Slf4j
@Component("wxTextMsgService")
@WxHandler(forType = WxHandlerType.HANDLER_TEXT)
public class WxTextServiceImpl extends DefaultWxTextRequestHandler implements WxTextService {

    @Autowired
    BaiDuWeatherApi baiDuWeatherApi;

    @Override
    public void handle(WxTextMsgReq request, WxResponse response) {
        log.info("处理微信 text 请求: {}", request);
        WxTextMsgResp wxTextMsgResp = new WxTextMsgResp(response);
        String content = request.getContent();
        int index = content.indexOf("天气");
        if (!Strings.isNullOrEmpty(content) && index > 0) {
            String city = content.substring(0, index);
            log.info("查询所在城市 {} 天气：", city);
            BaiDuWeatherResult weatherResult = baiDuWeatherApi.queryWithJson(city.trim());

            if (null != weatherResult && null != weatherResult.getResults()) {
                List<BaiDuWeatherResult.Weather.WeatherData> weatherDataList = weatherResult.getResults().get(0).getWeatherData();
                StringBuffer sb = new StringBuffer();
                sb.append(city).append("最近几天天气如下：\r\n");
                weatherDataList.forEach(weatherData ->
                        sb.append("\r\n--------\r\n")
                                .append(weatherData.getDate() + "\r\n")
                                .append(weatherData.getWeather() + "\r\n")
                                .append(weatherData.getWind() + "\r\n")
                                .append(weatherData.getTemperature())
                );
                wxTextMsgResp.setContent(sb.toString());
            } else {
                wxTextMsgResp.setContent("抱歉，暂时没有查询到您查询的城市的天气情况！");
            }
        } else {
            wxTextMsgResp.setContent("这是一条自动回复消息");
        }
        response.setAttribute("body", wxTextMsgResp);
    }
}

package com.kotall.oms.weixin.config;

import com.github.aracwong.weixin.api.*;
import com.github.aracwong.weixin.api.impl.*;
import com.github.aracwong.weixin.framework.core.WxHandlerDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/4/28
 */
@Configuration
public class WxApiConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WxHandlerDispatcher wxMsgHandler() {
        return new WxHandlerDispatcher();
    }

    @Bean
    public WxAccessTokenApi wxAccessTokenApi() {
        return new WxAccessTokenApiImpl();
    }

    @Bean
    public WxMenuApi wxMenuApi() {
        return new WxMenuApiImpl();
    }

    @Bean
    public WxKeFuApi wxKeFuApi() {
        return new WxKeFuApiImpl();
    }

    @Bean
    public WxQrCodeApi wxQrCodeApi() {
        return new WxQrCodeApiImpl();
    }

    @Bean
    public WxShortUrlApi wxLongUrlToShortApi() {
        return new WxShortUrlApiImpl();
    }

    @Bean
    public WxUserApi wxUserApi() {
        return new WxUserApiImpl();
    }
}

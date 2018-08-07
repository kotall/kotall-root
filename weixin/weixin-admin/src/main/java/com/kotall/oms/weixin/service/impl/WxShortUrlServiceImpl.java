package com.kotall.oms.weixin.service.impl;

import com.kotall.oms.weixin.service.WxShortUrlService;
import com.github.aracwong.weixin.api.WxShortUrlApi;
import com.github.aracwong.weixin.dto.url.WxShortUrlReqDto;
import com.github.aracwong.weixin.dto.url.WxShortUrlRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/6
 */
@Slf4j
@Service("wxLongUrlToShortService")
public class WxShortUrlServiceImpl implements WxShortUrlService {

    @Autowired
    private WxShortUrlApi wxShortUrlApi;

    @Override
    public WxShortUrlRespDto shortUrl(String appId, WxShortUrlReqDto reqDto) {
        WxShortUrlRespDto respDto = this.wxShortUrlApi.shortUrl(appId, reqDto);
        return respDto;
    }
}

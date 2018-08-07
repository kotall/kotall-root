package com.kotall.oms.weixin.controller;

import com.kotall.oms.weixin.service.WxShortUrlService;
import com.github.aracwong.weixin.dto.url.WxShortUrlReqDto;
import com.github.aracwong.weixin.dto.url.WxShortUrlRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/6
 */
@RestController
@RequestMapping("/wx/shortUrl")
public class WxShortUrlController {

    @Autowired
    private WxShortUrlService wxShortUrlService;

    @RequestMapping("/create/{appId}")
    public WxShortUrlRespDto create(@PathVariable("appId") String appId) {

        WxShortUrlReqDto reqDto = new WxShortUrlReqDto();
        reqDto.setAction("long2short");
        reqDto.setLongUrl("http://www.baidu.com");
        return this.wxShortUrlService.shortUrl(appId, reqDto);
    }
}

package com.kotall.oms.weixin.service;

import com.github.aracwong.weixin.dto.url.WxShortUrlReqDto;
import com.github.aracwong.weixin.dto.url.WxShortUrlRespDto;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/6
 */
public interface WxShortUrlService {

    WxShortUrlRespDto shortUrl(String appId, WxShortUrlReqDto reqDto);
}

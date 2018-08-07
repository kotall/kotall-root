package com.kotall.oms.weixin.service;

import com.github.aracwong.weixin.dto.custom.WxKeFuReqDto;
import com.github.aracwong.weixin.dto.result.WxResult;

/**
 * 客服服务
 *
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/6
 */
public interface WxKeFuService {

    WxResult create(String appId, WxKeFuReqDto wxKeFuReqDto);
}

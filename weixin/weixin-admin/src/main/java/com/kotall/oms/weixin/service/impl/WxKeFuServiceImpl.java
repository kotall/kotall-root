package com.kotall.oms.weixin.service.impl;

import com.kotall.oms.weixin.service.WxKeFuService;
import com.github.aracwong.weixin.api.WxKeFuApi;
import com.github.aracwong.weixin.dto.custom.WxKeFuReqDto;
import com.github.aracwong.weixin.dto.result.WxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/6
 */
@Slf4j
@Service("wxKeFuService")
public class WxKeFuServiceImpl implements WxKeFuService {

    @Autowired
    private WxKeFuApi wxKeFuApi;

    @Override
    public WxResult create(String appId, WxKeFuReqDto wxKeFuReqDto) {
        WxResult wxResult = wxKeFuApi.create(appId, wxKeFuReqDto);
        return wxResult;
    }
}

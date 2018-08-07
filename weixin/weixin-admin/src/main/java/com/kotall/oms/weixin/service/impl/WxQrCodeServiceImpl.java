package com.kotall.oms.weixin.service.impl;

import com.kotall.oms.weixin.service.WxQrCodeService;
import com.github.aracwong.weixin.api.WxQrCodeApi;
import com.github.aracwong.weixin.dto.qrcode.WxQrCodeReqDto;
import com.github.aracwong.weixin.dto.qrcode.WxQrCodeRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/6
 */
@Slf4j
@Service("wxQrCodeService")
public class WxQrCodeServiceImpl implements WxQrCodeService {

    @Autowired
    private WxQrCodeApi wxQrCodeApi;

    @Override
    public WxQrCodeRespDto create(String appId, WxQrCodeReqDto wxQrCodeReqDto) {
        WxQrCodeRespDto wxResult = wxQrCodeApi.create(appId, wxQrCodeReqDto);
        return wxResult;
    }
}

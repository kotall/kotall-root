package com.kotall.oms.weixin.service;

import com.github.aracwong.weixin.dto.qrcode.WxQrCodeReqDto;
import com.github.aracwong.weixin.dto.qrcode.WxQrCodeRespDto;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/6
 */
public interface WxQrCodeService {

    WxQrCodeRespDto create(String appId, WxQrCodeReqDto wxQrCodeReqDto);
}

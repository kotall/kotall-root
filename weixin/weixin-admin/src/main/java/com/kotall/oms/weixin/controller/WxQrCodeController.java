package com.kotall.oms.weixin.controller;

import com.kotall.oms.weixin.service.WxQrCodeService;
import com.github.aracwong.weixin.dto.qrcode.WxQrCodeReqDto;
import com.github.aracwong.weixin.dto.qrcode.WxQrCodeRespDto;
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
@RequestMapping("/wx/qrcode")
public class WxQrCodeController {

    @Autowired
    private WxQrCodeService wxQrCodeService;

    @RequestMapping("/create/{appId}")
    public WxQrCodeRespDto create(@PathVariable("appId") String appId) {
        WxQrCodeReqDto wxQrCodeReqDto = new WxQrCodeReqDto();
        wxQrCodeReqDto.setActionName("QR_SCENE");
        wxQrCodeReqDto.setExpireSeconds(604800L);
        wxQrCodeReqDto.getActionInfo().getScene().setSceneId(123L);
        wxQrCodeReqDto.getActionInfo().getScene().setSceneStr(null);

        return this.wxQrCodeService.create(appId, wxQrCodeReqDto);
    }


}

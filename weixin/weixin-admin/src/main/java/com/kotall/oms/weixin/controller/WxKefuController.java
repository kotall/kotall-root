package com.kotall.oms.weixin.controller;

import com.kotall.oms.weixin.service.WxKeFuService;
import com.github.aracwong.weixin.dto.custom.WxKeFuReqDto;
import com.github.aracwong.weixin.dto.result.WxResult;
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
@RequestMapping("/wx/kefu")
public class WxKefuController {

    @Autowired
    private WxKeFuService wxKeFuService;

    @RequestMapping("/create/{appId}")
    public WxResult create(@PathVariable("appId") String appId) {
        WxKeFuReqDto wxKeFuReqDto = new WxKeFuReqDto();
        wxKeFuReqDto.setKeFuAccount("10001@gh_d16f815ff87a");
        wxKeFuReqDto.setNickname("aracwong");
        /** 新版客服没有此字段*/
        // wxKeFuReqDto.setPassword("123456");
        WxResult result = this.wxKeFuService.create(appId, wxKeFuReqDto);
        return result;
    }
}

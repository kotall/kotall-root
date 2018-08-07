package com.kotall.oms.weixin.controller;

import com.kotall.oms.weixin.entity.WxAccountEntity;
import com.kotall.oms.weixin.service.WxAccountService;
import com.github.aracwong.weixin.api.WxAccessTokenApi;
import com.github.aracwong.weixin.api.WxUserApi;
import com.github.aracwong.weixin.dto.accesstoken.WxJsAccessTokenReq;
import com.github.aracwong.weixin.dto.accesstoken.WxJsAccessTokenResp;
import com.github.aracwong.weixin.dto.user.WxUserInfoReq;
import com.github.aracwong.weixin.dto.user.WxUserInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/8
 */
@Slf4j
@Controller
@RequestMapping("/wx/jsapi")
public class JsApiController {

    @Autowired
    private WxAccessTokenApi wxAccessTokenApi;
    @Autowired
    private WxUserApi wxUserApi;
    @Autowired
    private WxAccountService wxAccountService;

    @RequestMapping("/redirect")
    public String jsApi() {
        return "js/redirect";
    }


    @RequestMapping("/auth")
    public String auth(@RequestParam("code") String code, @RequestParam("state") String state) {
        WxAccountEntity wxAccountEntity = this.wxAccountService.getAccountByUserId(state);
        WxJsAccessTokenReq wxJsAccessTokenReq = new WxJsAccessTokenReq();
        wxJsAccessTokenReq.setCode(code);
        wxJsAccessTokenReq.setAppId(wxAccountEntity.getAppId());
        wxJsAccessTokenReq.setAppSecret(wxAccountEntity.getAppSecret());
        WxJsAccessTokenResp wxJsAccessTokenResp = wxAccessTokenApi.getJsAccessToken(wxJsAccessTokenReq);
        log.info("静默授权结果：{}", wxJsAccessTokenResp);
        return "js/index";
    }

    @RequestMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String state) {
        WxAccountEntity wxAccountEntity = this.wxAccountService.getAccountByUserId(state);
        WxJsAccessTokenReq wxJsAccessTokenReq = new WxJsAccessTokenReq();
        wxJsAccessTokenReq.setCode(code);
        wxJsAccessTokenReq.setAppId(wxAccountEntity.getAppId());
        wxJsAccessTokenReq.setAppSecret(wxAccountEntity.getAppSecret());
        WxJsAccessTokenResp wxJsAccessTokenResp = wxAccessTokenApi.getJsAccessToken(wxJsAccessTokenReq);
        log.info("提示授权结果：{}", wxJsAccessTokenResp);

        WxUserInfoReq wxUserInfoReq = new WxUserInfoReq();
        wxUserInfoReq.setOpenId(wxJsAccessTokenResp.getOpenId());
        wxUserInfoReq.setAccessToken(wxJsAccessTokenResp.getAccessToken());
        WxUserInfoResp wxUserInfoResp = wxUserApi.getUserInfo(wxUserInfoReq);
        log.info("用户信息: {}", wxUserInfoResp);

        return "js/index";
    }


}

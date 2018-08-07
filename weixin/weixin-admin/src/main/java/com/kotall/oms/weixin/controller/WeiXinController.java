package com.kotall.oms.weixin.controller;

import com.google.common.base.Strings;
import com.kotall.oms.weixin.entity.WxAccountEntity;
import com.kotall.oms.weixin.service.WxAccountService;
import com.github.aracwong.weixin.framework.core.WxDefaultRequest;
import com.github.aracwong.weixin.framework.core.WxDefaultResponse;
import com.github.aracwong.weixin.framework.core.WxHandlerDispatcher;
import com.github.aracwong.weixin.framework.core.WxResponse;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/2/10 0010 下午 8:52
 */
@Slf4j
//@RestController
public class WeiXinController {

    @Autowired
    private WxHandlerDispatcher dispatcher;

    @Autowired
    private WxAccountService wxAccountService;

    /**
     * 微信服务器认证处理
     * 验证服务器地址有效性：微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带四个参数
     *
     * @return
     */
    @RequestMapping(value = "/wxRequest/{userId}", method = RequestMethod.GET)
    public String handleGet(@RequestParam("timestamp") String timestamp,
                            @RequestParam("nonce") String nonce,
                            @RequestParam("signature") String signature,
                            @RequestParam("echostr") String echostr,
                            @PathVariable("userId") String userId) {

        log.info("========== 接收到微信服务器的 GET 认证请求：");
        log.info("========== 请求参数：userId={}, timestamp={}, nonce={}, signature={}, echostr={}", userId, timestamp, nonce, signature, echostr);
        List<String> list = new ArrayList<>();
        list.add(timestamp);
        list.add(nonce);

        WxAccountEntity wxAccountEntity = this.wxAccountService.getAccountByUserId(userId);
        if (wxAccountEntity == null) {
            return null;
        }
        list.add(wxAccountEntity.getToken());

        StringBuffer sb = new StringBuffer();
        list.forEach(str -> sb.append(str));

        String encryptStr = DigestUtils.sha1Hex(sb.toString());
        if (encryptStr.equals(signature)) {
            log.info("========== 认证成功！");
            return echostr;
        }

        log.info("========== 认证失败！");
        return null;
    }

    /**
     * 微信服务器消息处理
     *
     * @return
     */
    @RequestMapping(value = "/wxRequest/{userId}", method = RequestMethod.POST)
    public String handlePost(@RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce,
                             @RequestParam("signature") String signature,
                             @RequestParam("openid") String openid,
                             @RequestBody String data,
                             @PathVariable("userId") String userId) {
        log.info("========== 接收到微信服务器的 POST 请求：");
        log.info("========== 请求签名信息：userId={}, timestamp={}, nonce={}, signature={}, openid={}", userId, timestamp, nonce, signature, openid);
        log.info("========== 请求的数据：\r\n{}", data);
        String result = "";
        try {
            if (!Strings.isNullOrEmpty(data)) {
                WxDefaultRequest wxRequest = new WxDefaultRequest(data);
                WxResponse wxResponse = new WxDefaultResponse(wxRequest);

                this.dispatcher.doDispatch(wxRequest, wxResponse);

                String KEY_RESPONSE_BODY = "body";
                Object responseBody = wxResponse.getAttribute(KEY_RESPONSE_BODY);
                if (null != wxResponse.getAttribute(KEY_RESPONSE_BODY)) {
                    XStream xStream = new XStream();
                    xStream.autodetectAnnotations(true);
                    String xml = xStream.toXML(responseBody);
                    log.info("响应报文：\r\n{}", xml);
                    result = xml;
                }
            }
        } catch (Exception e) {
            log.error("处理微信 POST 请求出现异常！", e);
        }

        return result;
    }
}

package com.kotall.oms.weixin.service.impl;

import com.github.aracwong.weixin.framework.annotation.WxHandler;
import com.github.aracwong.weixin.framework.constant.WxHandlerType;
import com.github.aracwong.weixin.framework.core.WxResponse;
import com.github.aracwong.weixin.framework.handler.image.DefaultWxImageRequestHandler;
import com.github.aracwong.weixin.framework.msg.image.WxImageMsgReq;
import com.kotall.oms.weixin.service.WxImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/5/1 0001 下午 12:08
 */
@Slf4j
@Component("wxImageMsgService")
@WxHandler(forType = WxHandlerType.HANDLER_IMAGE)
public class WxImageServiceImpl extends DefaultWxImageRequestHandler implements WxImageService {

    @Override
    public void handle(WxImageMsgReq request, WxResponse response) {
        super.handle(request, response);
    }
}

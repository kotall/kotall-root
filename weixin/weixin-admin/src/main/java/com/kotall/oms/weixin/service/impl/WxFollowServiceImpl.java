package com.kotall.oms.weixin.service.impl;

import com.github.aracwong.weixin.framework.annotation.WxHandler;
import com.github.aracwong.weixin.framework.constant.WxHandlerType;
import com.github.aracwong.weixin.framework.constant.WxMsgType;
import com.github.aracwong.weixin.framework.core.WxResponse;
import com.github.aracwong.weixin.framework.event.WxFollowEventReq;
import com.github.aracwong.weixin.framework.handler.event.DefaultWxFollowEventHandler;
import com.github.aracwong.weixin.framework.msg.text.WxTextMsgResp;
import com.kotall.oms.weixin.service.WxFollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/5/2 0002 下午 10:39
 */
@Slf4j
@Component("wxFollowEventService")
@WxHandler(forType = WxHandlerType.HANDLER_EVENT_FOLLOW)
public class WxFollowServiceImpl extends DefaultWxFollowEventHandler implements WxFollowService {

    @Override
    public void handle(WxFollowEventReq request, WxResponse response) {
        log.info("处理微信 关注/取消关注 事件: {}", request);
        String event = request.getEvent();
        String content = "";
        if (WxMsgType.EVENT_SUBSCRIBE.equals(event.trim())) {
            content = "关注";
        } else if (WxMsgType.EVENT_UNSUBSCRIBE.equals(event.trim())) {
            content = "取消关注";
        }

        log.info("用户：{}, {}了公众号", request.getFromUserName(), content);

        WxTextMsgResp wxTextMsgResp = new WxTextMsgResp(response);
        wxTextMsgResp.setMsgType(WxMsgType.TEXT);

        wxTextMsgResp.setContent("感谢您关注『口淘科技』！\n" +
                "您可以回复如下关键词获取专业信息：\n" +
                "→ \"城市+天气\"： 查询天气信息, 如: 上海天气\n");
        response.setAttribute("body", wxTextMsgResp);
    }
}

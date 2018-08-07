package com.kotall.oms.weixin.service.impl;

import com.github.aracwong.weixin.api.WxMenuApi;
import com.github.aracwong.weixin.dto.menu.WxReqMenuDto;
import com.github.aracwong.weixin.dto.result.WxResult;
import com.github.aracwong.weixin.framework.annotation.WxHandler;
import com.github.aracwong.weixin.framework.constant.WxHandlerType;
import com.github.aracwong.weixin.framework.constant.WxMsgType;
import com.github.aracwong.weixin.framework.core.WxResponse;
import com.github.aracwong.weixin.framework.event.WxMenuEventReq;
import com.github.aracwong.weixin.framework.handler.event.DefaultWxMenuEventHandler;
import com.github.aracwong.weixin.framework.msg.news.WxNewsMsgResp;
import com.kotall.oms.weixin.dao.WxMenuDao;
import com.kotall.oms.weixin.dto.TreeDto;
import com.kotall.oms.weixin.entity.WxMenuEntity;
import com.kotall.oms.weixin.service.WxMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/5/3
 */
@Slf4j
@Component("wxMenuEventService")
@WxHandler(forType = WxHandlerType.HANDLER_EVENT_MENU_DEFAULT)
public class WxMenuServiceImpl extends DefaultWxMenuEventHandler implements WxMenuService {

    @Autowired
    private WxMenuApi wxMenuApi;
    @Autowired
    private WxMenuDao wxMenuDao;

    @Override
    public void handle(WxMenuEventReq request, WxResponse response) {
        String event = request.getEvent();
        String eventKey = request.getEventKey();
        if (WxMsgType.EVENT_CLICK.equals(event)) {
            log.info("接收到菜单点击按钮事件");

            WxNewsMsgResp newsMsgResp = new WxNewsMsgResp(response);
            newsMsgResp.setMsgType(WxMsgType.NEWS);
            newsMsgResp.setArticleCount("3");

            WxNewsMsgResp.Article article1 = new WxNewsMsgResp.Article();
            article1.setTitle("欢颜您的关注");
            article1.setDescription("这是一个关注消息");
            article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/0h2lt7m1c03hXK37hKbkLtRTen9u6H03bhY7rPoTlpnHdqVMcfA4VFibiakj7lBMW3BgxwelOE0I3YcMDpOnoOxw/0");
            article1.setUrl("http://kotall2018.frp2.chuantou.org/wxadmin/home");
            newsMsgResp.getArticles().add(article1);

            WxNewsMsgResp.Article article2 = new WxNewsMsgResp.Article();
            article2.setTitle("普京连任俄总统");
            article2.setDescription("这是一个俄国新闻消息消息");
            article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/0h2lt7m1c03hXK37hKbkLtRTen9u6H03bhY7rPoTlpnHdqVMcfA4VFibiakj7lBMW3BgxwelOE0I3YcMDpOnoOxw/0");
            article2.setUrl("http://kotall2018.frp2.chuantou.org/wxadmin/home");
            newsMsgResp.getArticles().add(article2);

            WxNewsMsgResp.Article article3 = new WxNewsMsgResp.Article();
            article3.setTitle("今日天气");
            article3.setDescription("今日江浙沪有大雨天气");
            article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/0h2lt7m1c03hXK37hKbkLtRTen9u6H035L36bHuiaAabTsy8QNb1r83icicbJcYZVv6Bs78u0Be6gicHiaz7yDK6GZA/0");
            article3.setUrl("http://kotall2018.frp2.chuantou.org/wxadmin/home");
            newsMsgResp.getArticles().add(article3);

            response.setAttribute("body", newsMsgResp);
        } else if (WxMsgType.EVENT_VIEW.equals(event)) {
            log.info("接收到菜单链接按钮事件");
        }
    }

    @Override
    public WxResult create(String appId, List<WxReqMenuDto> menus) {
        WxResult wxResult = this.wxMenuApi.create(appId, menus);
        return wxResult;
    }

    @Override
    public WxMenuEntity save(WxMenuEntity wxMenuEntity) {
        WxMenuEntity rtEntity = wxMenuDao.save(wxMenuEntity);
        return rtEntity;
    }

    
	@Override
	public WxResult syncWxMenu(String appId) {
		List<WxMenuEntity> menuList = this.wxMenuDao.findByAppId(appId);

        WxMenuEntity rootEntity = new WxMenuEntity();
        rootEntity.setId(0);
        TreeDto<WxMenuEntity> rootMenu = new TreeDto<>();
        rootMenu.setRoot(rootEntity);
        List<TreeDto<WxMenuEntity>> subList = rootMenu.getSubList();
        for (int i=0; i < menuList.size(); i++) {
            WxMenuEntity entity = menuList.get(i);
            if (rootMenu.getRoot().getId() == entity.getParentId()) {
                WxMenuEntity newMenu = new WxMenuEntity();
                BeanUtils.copyProperties(entity, newMenu);
                TreeDto<WxMenuEntity> treeNode = new TreeDto<>();
                treeNode.setRoot(newMenu);
                subList.add(treeNode);
                menuList.remove(entity);
            }
        }
        rootMenu.setSubList(subList);

		this.fire2Tree(rootMenu.getSubList(), menuList);

		WxReqMenuDto reqMenuDto = new WxReqMenuDto();
		this.convert2MenuDto(reqMenuDto, rootMenu);

		log.info("微信菜单: \r\n{}", reqMenuDto.getSubMenus());
		return this.wxMenuApi.create(appId, reqMenuDto.getSubMenus());
	}


    private void fire2Tree(List<TreeDto<WxMenuEntity>> subMenuList, List<WxMenuEntity> menuList) {
        for (TreeDto<WxMenuEntity> wxMenuEntityTreeDto : subMenuList) {
            for (int i=0; i < menuList.size(); i++) {
                WxMenuEntity entity = menuList.get(i);
                if (wxMenuEntityTreeDto.getRoot().getId() == entity.getParentId()) {
                    TreeDto<WxMenuEntity> treeDto = new TreeDto<>();
                    WxMenuEntity newEntity = new WxMenuEntity();
                    BeanUtils.copyProperties(entity, newEntity);
                    treeDto.setRoot(newEntity);
                    wxMenuEntityTreeDto.getSubList().add(treeDto);
                }
            }

            fire2Tree(wxMenuEntityTreeDto.getSubList(), menuList);
        }
	}

    private void convert2MenuDto(WxReqMenuDto reqMenuDto, TreeDto<WxMenuEntity> rootMenu) {

        BeanUtils.copyProperties(rootMenu.getRoot(), reqMenuDto);

        List<WxReqMenuDto> subMenus = reqMenuDto.getSubMenus();
        List<TreeDto<WxMenuEntity>> treeDtoList = rootMenu.getSubList();
        if (subMenus == null && treeDtoList.size() > 0) {
            subMenus = new ArrayList<>();
            reqMenuDto.setSubMenus(subMenus);
        }
        for (TreeDto<WxMenuEntity> wxMenuEntityTreeDto : treeDtoList) {
            WxReqMenuDto subMenuDto = new WxReqMenuDto();
            convert2MenuDto(subMenuDto, wxMenuEntityTreeDto);
            subMenus.add(subMenuDto);
        }
    }

}

package com.kotall.oms.weixin.controller;

import com.kotall.oms.weixin.service.WxMenuService;
import com.github.aracwong.weixin.dto.menu.WxReqMenuDto;
import com.github.aracwong.weixin.dto.result.WxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/5/3 0003 下午 10:15
 */
@RestController
@RequestMapping("/wx/menu")
public class WxMenuController {

    @Autowired
    WxMenuService wxMenuService;

    @RequestMapping("/create/{appId}")
    public WxResult create(@PathVariable("appId") String appId) {
        // wxd23854d784085be9
        WxReqMenuDto menu1 = new WxReqMenuDto();
        menu1.setKey("V1001_MY_STORY");
        menu1.setName("边走边乔");
        menu1.setType("click");
        // --------------------------------------------------

        WxReqMenuDto menu2 = new WxReqMenuDto();
        menu2.setName("工具箱");


        List<WxReqMenuDto> subMenus2 = new ArrayList<>();
        WxReqMenuDto subMenu21 = new WxReqMenuDto();

        subMenu21.setName("VIEW");
        subMenu21.setType("view");
        subMenu21.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");

        WxReqMenuDto subMenu22 = new WxReqMenuDto();
        subMenu22.setName("静默授权");
        subMenu22.setType("view");
        //subMenu22.setUrl("http://kotall2018.frp2.chuantou.org/wxadmin/jsapi/redirect");
        //subMenu22.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd23854d784085be9&redirect_uri=http://kotall2018.frp2.chuantou.org/wxadmin/home&response_type=code&scope=snsapi_base&state=123456#wechat_redirect");
        subMenu22.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd23854d784085be9&redirect_uri=http%3a%2f%2fkotall2018.frp2.chuantou.org%2fwx%2fjsapi%2fauth&response_type=code&scope=snsapi_base&state=kotall#wechat_redirect");

        WxReqMenuDto subMenu23 = new WxReqMenuDto();
        subMenu23.setKey("V1001_GOOD");
        subMenu23.setName("CLICK");
        subMenu23.setType("click");

        WxReqMenuDto subMenu24 = new WxReqMenuDto();
        subMenu24.setName("请求授权");
        subMenu24.setType("view");
        subMenu24.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd23854d784085be9&redirect_uri=http%3a%2f%2fkotall2018.frp2.chuantou.org%2fwx%2fjsapi%2fuserInfo&response_type=code&scope=snsapi_userinfo&state=kotall#wechat_redirect");


        subMenus2.add(subMenu21);
        subMenus2.add(subMenu22);
        subMenus2.add(subMenu23);
        subMenus2.add(subMenu24);

        menu2.setSubMenus(subMenus2);
        // --------------------------------------------------

        WxReqMenuDto menu3 = new WxReqMenuDto();
        menu3.setName("收藏夹");

        List<WxReqMenuDto> subMenus3 = new ArrayList<>();

        WxReqMenuDto subMenu31 = new WxReqMenuDto();
        subMenu31.setKey("V1002_SCAN");
        subMenu31.setName("scancode_push");
        subMenu31.setType("scancode_push");

        WxReqMenuDto subMenu32 = new WxReqMenuDto();
        subMenu32.setKey("V1003_PIC");
        subMenu32.setName("pic_sysphoto");
        subMenu32.setType("pic_sysphoto");

        WxReqMenuDto subMenu33 = new WxReqMenuDto();
        subMenu33.setKey("V1005_PIC");
        subMenu33.setName("pic_photo_or_album");
        subMenu33.setType("pic_photo_or_album");

        WxReqMenuDto subMenu34 = new WxReqMenuDto();
        subMenu34.setKey("V1006_PIC");
        subMenu34.setName("pic_weixin");
        subMenu34.setType("pic_weixin");

        WxReqMenuDto subMenu35 = new WxReqMenuDto();
        subMenu35.setKey("V1007_PIC");
        subMenu35.setName("location_select");
        subMenu35.setType("location_select");


        subMenus3.add(subMenu31);
        subMenus3.add(subMenu32);
        subMenus3.add(subMenu33);
        subMenus3.add(subMenu34);
        subMenus3.add(subMenu35);


        menu3.setSubMenus(subMenus3);

        List<WxReqMenuDto> menus = new ArrayList<>();
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        WxResult result = this.wxMenuService.create(appId, menus);
        return result;
    }

    @RequestMapping("/sync/{appId}")
    public WxResult sync(@PathVariable("appId") String appId) {
        WxResult result = this.wxMenuService.syncWxMenu(appId);
        return result;
    }

}

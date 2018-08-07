package com.kotall.oms.weixin.menu;

import com.google.gson.Gson;
import com.github.aracwong.weixin.dto.menu.WxReqMenuDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/4/25 0025 下午 11:07
 */
public class MenuTest {

    @Test
    public void testCreateMenu() {
        WxReqMenuDto wxMenu1 = new WxReqMenuDto();
        wxMenu1.setKey("V1001_TODAY_MUSIC");
        wxMenu1.setName("今日歌曲");
        wxMenu1.setType("click");


        List<WxReqMenuDto> subMenus = new ArrayList<>();

        WxReqMenuDto subMenu1 = new WxReqMenuDto();
        subMenu1.setName("搜索");
        subMenu1.setType("view");
        subMenu1.setUrl("http://www.soso.com/");

        WxReqMenuDto subMenu2 = new WxReqMenuDto();
        subMenu2.setName("wxa");
        subMenu2.setType("miniprogram");
        subMenu2.setUrl("http://www.soso.com/");
        subMenu2.setAppId("wx286b93c14bbf93aa");
        subMenu2.setPagePath("pages/lunar/index");

        WxReqMenuDto subMenu3 = new WxReqMenuDto();
        subMenu3.setKey("V1001_GOOD");
        subMenu3.setName("赞一下我们");
        subMenu3.setType("click");

        subMenus.add(subMenu1);
        subMenus.add(subMenu2);
        subMenus.add(subMenu3);

        WxReqMenuDto wxMenu2 = new WxReqMenuDto();
        wxMenu2.setName("菜单");
        wxMenu2.setSubMenus(subMenus);


        List<WxReqMenuDto> menus = new ArrayList<>();
        menus.add(wxMenu1);
        menus.add(wxMenu2);

        Gson gson = new Gson();
        Map<String, List<WxReqMenuDto>> wxMenuMap = new HashMap<>();
        wxMenuMap.put("button", menus);
        String json = gson.toJson(wxMenuMap);
        System.out.println(json);


    }
}

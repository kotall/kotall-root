package com.kotall.oms.weixin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kotall.oms.weixin.entity.WxMenuEntity;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxMenuServiceTest {

    @Autowired
    private WxMenuService wxMenuService;
    

    @Test
    public void testSaveMenu() {
        WxMenuEntity wxMenuEntity = new WxMenuEntity();
        wxMenuEntity.setParentId(0);
        wxMenuEntity.setAppId("wxd23854d784085be9");
        wxMenuEntity.setType("view");
        wxMenuEntity.setName("边走边乔");
        wxMenuEntity.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");
        this.wxMenuService.save(wxMenuEntity);
    }

    @Test
    public void testSyncWxMenu() {
        this.wxMenuService.syncWxMenu("wxd23854d784085be9");
    }
    
    @Test
    public void createProMenuList() {
        String appId = "wxd23854d784085be9";
    	// 产品中心
    	//      -- 口淘商城
    	//      -- 电商解决方案
    	// 收藏夹
    	//      -- 创意
    	//      -- 搜索
    	// 边走边乔
    	//      -- 见闻
    	//      -- 想法
    	//      -- 日记
    	WxMenuEntity rootMenu1 = new WxMenuEntity();
    	
    	rootMenu1.setParentId(0);
    	rootMenu1.setAppId(appId);
    	rootMenu1.setName("产品中心");
        WxMenuEntity rtWxMenEntity = this.wxMenuService.save(rootMenu1);
        
        int rootMenu1_id = rtWxMenEntity.getId();
        
        WxMenuEntity rootMenu1_sub1 = new WxMenuEntity();
        rootMenu1_sub1.setParentId(rootMenu1_id);
        rootMenu1_sub1.setAppId(appId);
        rootMenu1_sub1.setName("口淘商城");
        rootMenu1_sub1.setType("view");
        rootMenu1_sub1.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");
        this.wxMenuService.save(rootMenu1_sub1);
        
        WxMenuEntity rootMenu1_sub2 = new WxMenuEntity();
        rootMenu1_sub2.setParentId(rootMenu1_id);
        rootMenu1_sub2.setAppId(appId);
        rootMenu1_sub2.setName("电商解决方案");
        rootMenu1_sub2.setType("view");
        rootMenu1_sub2.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");
        this.wxMenuService.save(rootMenu1_sub2);
        
        
        
        // ---------------------------------------------------------------------
        WxMenuEntity rootMenu2 = new WxMenuEntity();
    	
        rootMenu2.setParentId(0);
        rootMenu2.setAppId(appId);
        rootMenu2.setName("收藏夹");
        rtWxMenEntity = this.wxMenuService.save(rootMenu2);
        
        int rootMenu2_id = rtWxMenEntity.getId();
        
        WxMenuEntity rootMenu2_sub1 = new WxMenuEntity();
        rootMenu2_sub1.setParentId(rootMenu2_id);
        rootMenu2_sub1.setAppId(appId);
        rootMenu2_sub1.setName("创意");
        rootMenu2_sub1.setType("view");
        rootMenu2_sub1.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");
        this.wxMenuService.save(rootMenu2_sub1);
        
        WxMenuEntity rootMenu2_sub2 = new WxMenuEntity();
        rootMenu2_sub2.setParentId(rootMenu2_id);
        rootMenu2_sub2.setAppId(appId);
        rootMenu2_sub2.setName("搜索");
        rootMenu2_sub2.setType("view");
        rootMenu2_sub2.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");
        this.wxMenuService.save(rootMenu2_sub2);
        

        
        // ---------------------------------------------------------------------
        WxMenuEntity rootMenu3 = new WxMenuEntity();
    	
        rootMenu3.setParentId(0);
        rootMenu3.setAppId(appId);
        rootMenu3.setName("边走边乔");
        rtWxMenEntity = this.wxMenuService.save(rootMenu3);
        
        int rootMenu3_id = rtWxMenEntity.getId();
        
        WxMenuEntity rootMenu3_sub1 = new WxMenuEntity();
        rootMenu3_sub1.setParentId(rootMenu3_id);
        rootMenu3_sub1.setAppId(appId);
        rootMenu3_sub1.setName("见闻");
        rootMenu3_sub1.setType("view");
        rootMenu3_sub1.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");
        this.wxMenuService.save(rootMenu3_sub1);
        
        WxMenuEntity rootMenu3_sub2 = new WxMenuEntity();
        rootMenu3_sub2.setParentId(rootMenu3_id);
        rootMenu3_sub2.setAppId(appId);
        rootMenu3_sub2.setName("想法");
        rootMenu3_sub2.setType("view");
        rootMenu3_sub2.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");
        this.wxMenuService.save(rootMenu3_sub2);
        
        
        WxMenuEntity rootMenu3_sub3 = new WxMenuEntity();
        rootMenu3_sub3.setParentId(rootMenu3_id);
        rootMenu3_sub3.setAppId(appId);
        rootMenu3_sub3.setName("日记");
        rootMenu3_sub3.setType("view");
        rootMenu3_sub3.setUrl("http://kotall2018.frp2.chuantou.org/wx/home");
        this.wxMenuService.save(rootMenu3_sub3);
    	
    	
    }
}

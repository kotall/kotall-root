package com.kotall.oms.weixin.service;

import com.kotall.oms.admin.dto.SysMenuDto;
import com.kotall.oms.admin.service.SysMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuServiceTest {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void testAddSysMenu() {
        SysMenuDto m1 = new SysMenuDto();
        m1.setEventIconClass("arrow");
        m1.setIconClass("icon-home");
        m1.setTitle("仪表盘");
        m1 = this.sysMenuService.saveSysMenu(m1);

        SysMenuDto m11 = new SysMenuDto();
        m11.setUrl("ui_colors.html");
        m11.setIconClass("icon-bar-chart");
        m11.setTitle("Dashboard 1");
        m11.setPid(m1.getId());
        this.sysMenuService.saveSysMenu(m11);

        SysMenuDto m12 = new SysMenuDto();
        m12.setUrl("ui_general.html");
        m12.setIconClass("icon-bulb");
        m12.setTitle("Dashboard 2");
        m12.setPid(m1.getId());
        this.sysMenuService.saveSysMenu(m12);

        // ----------------------------------------
        SysMenuDto m2 = new SysMenuDto();
        m2.setEventIconClass("arrow");
        m2.setIconClass("icon-diamond");
        m2.setTitle("权限管理");
        m2 = this.sysMenuService.saveSysMenu(m2);

        SysMenuDto m21 = new SysMenuDto();
        m21.setUrl("ui_colors.html");
        m21.setTitle("Color Library");
        m21.setPid(m2.getId());
        this.sysMenuService.saveSysMenu(m21);

        SysMenuDto m22 = new SysMenuDto();
        m22.setUrl("ui_general.html");
        m22.setEventIconClass("arrow");
        m22.setTitle("General Components");
        m22.setPid(m2.getId());
        m22 = this.sysMenuService.saveSysMenu(m22);

        SysMenuDto m221 = new SysMenuDto();
        m221.setUrl("ui_page_progress_style_1.html");
        m221.setTitle("Flash");
        m221.setPid(m22.getId());
        this.sysMenuService.saveSysMenu(m221);

        SysMenuDto m222 = new SysMenuDto();
        m222.setUrl("ui_page_progress_style_2.html");
        m222.setTitle("Big Counter");
        m222.setPid(m22.getId());
        this.sysMenuService.saveSysMenu(m222);

        // ----------------------------------------
        SysMenuDto m3 = new SysMenuDto();
        m3.setEventIconClass("arrow");
        m3.setIconClass("icon-home");
        m3.setTitle("仪表盘");
        m3 = this.sysMenuService.saveSysMenu(m3);

        SysMenuDto m31 = new SysMenuDto();
        m31.setUrl("ui_colors.html");
        m31.setTitle("Color Library");
        m31.setPid(m3.getId());
        this.sysMenuService.saveSysMenu(m31);

        SysMenuDto m32 = new SysMenuDto();
        m32.setUrl("ui_general.html");
        m32.setTitle("General Components");
        m32.setPid(m3.getId());
        this.sysMenuService.saveSysMenu(m32);



    }

    @Test
    public void testEagerFindSysMenu() {
        List<SysMenuDto> sysMenuDtoList = this.sysMenuService.eagerFindMenuByPid(0);

    }
}

package com.kotall.oms.admin.controller;

import com.kotall.oms.admin.dto.SysMenuDto;
import com.kotall.oms.admin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RestController
@RequestMapping("/admin/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/list")
    public List<SysMenuDto> list() {
        List<SysMenuDto> sysMenuDtoList = this.sysMenuService.eagerFindMenuByPid(0);
        return sysMenuDtoList;
    }

}

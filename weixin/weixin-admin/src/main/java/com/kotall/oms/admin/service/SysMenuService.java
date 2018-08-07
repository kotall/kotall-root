package com.kotall.oms.admin.service;

import com.kotall.oms.admin.dto.SysMenuDto;

import java.util.List;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface SysMenuService {

    SysMenuDto saveSysMenu(SysMenuDto sysMenuDto);

    List<SysMenuDto> eagerFindMenuByPid(int pid);

    List<SysMenuDto> lazyFindMenuByPid(int pid);



}

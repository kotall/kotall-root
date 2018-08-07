package com.kotall.oms.admin.service.impl;

import com.kotall.oms.admin.dao.SysMenuDao;
import com.kotall.oms.admin.dto.SysMenuDto;
import com.kotall.oms.admin.entity.SysMenuEntity;
import com.kotall.oms.admin.service.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public SysMenuDto saveSysMenu(SysMenuDto sysMenuDto) {
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        BeanUtils.copyProperties(sysMenuDto, sysMenuEntity);
        sysMenuEntity = this.sysMenuDao.save(sysMenuEntity);
        sysMenuDto.setId(sysMenuEntity.getId());
        return sysMenuDto;
    }

    @Override
    public List<SysMenuDto> eagerFindMenuByPid(int pid) {
        List<SysMenuEntity> sysMenuEntityList = this.sysMenuDao.findByPid(pid);
        List<SysMenuDto> sysMenuDtoList = new ArrayList<>();
        sysMenuEntityList.forEach(entity -> {
            SysMenuDto sysMenuDto = new SysMenuDto();
            BeanUtils.copyProperties(entity, sysMenuDto);
            sysMenuDtoList.add(sysMenuDto);
        });

        sysMenuDtoList.forEach(sysMenuDto -> this.retrieveSubMenu(sysMenuDto));
        return sysMenuDtoList;
    }

    private void retrieveSubMenu(SysMenuDto sysMenuDto) {
        List<SysMenuEntity> subMenuEntityList = this.sysMenuDao.findByPid(sysMenuDto.getId());
        if (subMenuEntityList != null && subMenuEntityList.size() > 0) {
            List<SysMenuDto> subMenus = new ArrayList<>();
            subMenuEntityList.forEach(entity -> {
                SysMenuDto menuDto = new SysMenuDto();
                BeanUtils.copyProperties(entity, menuDto);
                this.retrieveSubMenu(menuDto);
                subMenus.add(sysMenuDto);
            });
            sysMenuDto.setSubMenuList(subMenus);
        }
    }

    @Override
    public List<SysMenuDto> lazyFindMenuByPid(int pid) {
        List<SysMenuEntity> sysMenuEntityList = this.sysMenuDao.findByPid(pid);
        List<SysMenuDto> sysMenuDtoList = new ArrayList<>();
        sysMenuEntityList.forEach(entity -> {
            SysMenuDto sysMenuDto = new SysMenuDto();
            BeanUtils.copyProperties(entity, sysMenuDto);
        });
        return sysMenuDtoList;
    }
}

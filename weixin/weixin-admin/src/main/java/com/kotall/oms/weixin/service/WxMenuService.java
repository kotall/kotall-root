package com.kotall.oms.weixin.service;

import com.github.aracwong.weixin.dto.menu.WxReqMenuDto;
import com.github.aracwong.weixin.dto.result.WxResult;
import com.kotall.oms.weixin.entity.WxMenuEntity;

import java.util.List;

/**
 * 微信菜单操作接口
 *
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/5/3
 */
public interface WxMenuService {

    /**
     * 创建微信默认菜单
     * @param appId
     * @param menus
     * @return
     */
    WxResult create(String appId, List<WxReqMenuDto> menus);


    /**
     * 保存微信菜单记录
     *
     * @param wxMenuEntity
     *        微信菜单对象
     * @return
     */
    WxMenuEntity save(WxMenuEntity wxMenuEntity);
    
    /**
     * 同步微信菜单到微信服务器
     * 
     * @param appId
     *        appId
     * @return
     */
    WxResult syncWxMenu(String appId);

}

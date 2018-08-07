package com.kotall.oms.weixin.service;

import com.github.aracwong.weixin.dto.accesstoken.WxAccountDto;
import com.kotall.oms.weixin.entity.WxAccountEntity;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/4/28
 */
public interface WxAccountService {

    /**
     * 根据系统 userId查询微信帐号配置信息
     *
     * @param userId
     * @return
     */
    WxAccountEntity getAccountByUserId(String userId);

    /**
     * 根据 url 获取微信账号配置信息
     *
     * @param url url
     * @return 微信账号信息
     */
    WxAccountDto getAccountByUrl(String url);
}

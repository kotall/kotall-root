package com.kotall.oms.weixin.config;

import com.github.aracwong.weixin.dto.accesstoken.WxAccountDto;
import com.github.aracwong.weixin.framework.core.WxConfigStorage;
import com.kotall.oms.weixin.service.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Component("wxConfigDbStorage")
public class WxConfigDbStorageImpl implements WxConfigStorage {

    @Autowired
    private WxAccountService wxAccountService;

    @Override
    public void saveWxAccount(WxAccountDto wxAccountDto) {

    }

    @Override
    public WxAccountDto getWxAccount(String url) {

        return this.wxAccountService.getAccountByUrl(url);
    }
}

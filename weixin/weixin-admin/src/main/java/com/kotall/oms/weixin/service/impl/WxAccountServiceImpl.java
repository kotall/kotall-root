package com.kotall.oms.weixin.service.impl;

import com.github.aracwong.weixin.dto.accesstoken.WxAccountDto;
import com.kotall.oms.weixin.dao.WxAccountDao;
import com.kotall.oms.weixin.entity.WxAccountEntity;
import com.kotall.oms.weixin.service.WxAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/4/28
 */
@Slf4j
@Service("wxAccountService")
public class WxAccountServiceImpl implements WxAccountService {

    @Autowired
    private WxAccountDao wxAccountDao;

    @Override
    public WxAccountEntity getAccountByUserId(String userId) {
        return wxAccountDao.getAccountByUserId(userId);
    }

    @Override
    public WxAccountDto getAccountByUrl(String url) {
        WxAccountEntity wxAccountEntity = this.wxAccountDao.getAccountByUrl(url);
        WxAccountDto wxAccountDto = new WxAccountDto();
        if (null != wxAccountEntity) {
            BeanUtils.copyProperties(wxAccountEntity, wxAccountDto);
        }
        return wxAccountDto;
    }
}

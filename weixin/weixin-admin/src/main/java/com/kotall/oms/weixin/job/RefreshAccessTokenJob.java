package com.kotall.oms.weixin.job;

import com.kotall.oms.weixin.dao.WxAccessTokenDao;
import com.kotall.oms.weixin.dao.WxAccountDao;
import com.kotall.oms.weixin.entity.WxAccessTokenEntity;
import com.kotall.oms.weixin.entity.WxAccountEntity;
import com.github.aracwong.weixin.api.WxAccessTokenApi;
import com.github.aracwong.weixin.dto.accesstoken.WxAccessTokenReq;
import com.github.aracwong.weixin.dto.accesstoken.WxAccessTokenResp;
import com.github.aracwong.weixin.framework.context.WxAppContext;
import com.github.aracwong.weixin.framework.exception.WxException;
import com.kotall.oms.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/2/13
 */
@Slf4j
public class RefreshAccessTokenJob implements org.quartz.Job {


    @Override
    public void execute(JobExecutionContext context) {
        log.info("======== 开始刷新AccessToken ========");
        WxAccountDao wxAccountDao = (WxAccountDao)ApplicationContextUtil.getBean("wxAccountDao");
        WxAccessTokenApi wxAccessTokenApi = (WxAccessTokenApi)ApplicationContextUtil.getBean("wxAccessTokenApi");
        List<WxAccountEntity> accounts = wxAccountDao.findAll();
        if (null == accounts || accounts.size() == 0) {
            log.warn("======== 没有查询到公众号配置，不刷新AccessToken");
            return ;
        }
        log.info("======== 查询到: {} 条微信公众号配置", accounts.size());
        for (WxAccountEntity wxAccountEntity : accounts) {
            try {
                log.info(wxAccountEntity.toString());
                WxAccessTokenReq wxAccessTokenReq = new WxAccessTokenReq();
                BeanUtils.copyProperties(wxAccountEntity, wxAccessTokenReq);
                WxAccessTokenResp wxAccessTokenResp = wxAccessTokenApi.refreshAccessToken(wxAccessTokenReq);
                if (null != wxAccessTokenResp) {
                    WxAppContext.setWxAccessToken(wxAccessTokenReq.getAppId(), wxAccessTokenResp);
                    log.info("success refresh accessToken:" + wxAccessTokenResp);
                    WxAccessTokenDao wxAccessTokenDao = (WxAccessTokenDao)ApplicationContextUtil.getBean("wxAccessTokenDao");
                    wxAccessTokenDao.save(new WxAccessTokenEntity(wxAccountEntity.getAppId(), wxAccessTokenResp.getAccessToken(), wxAccessTokenResp.getExpiresIn()));
                }
            } catch (WxException e) {
                log.error("failed to refresh accessToken, appId:{}", wxAccountEntity.getAppId(), e);
            }
        }
        log.info("======== 完成刷新AccessToken ========");
    }

}

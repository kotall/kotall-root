package com.kotall.oms.weixin.service;

import com.github.aracwong.weixin.dto.accesstoken.WxAccountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxAccountServiceTest {

    @Autowired
    private WxAccountService wxAccountService;

    @Test
    public void testGetWxAccountByUrl() {
        WxAccountDto wxAccountDto = this.wxAccountService.getAccountByUrl("http://lw.weiyudu.com/wx/request");
        System.out.println("======" + wxAccountDto);
    }
}

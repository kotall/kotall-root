package com.kotall.oms.weixin.dao;

import com.kotall.oms.weixin.entity.WxAccountEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/2/28 0028 下午 10:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxAccountDaoTest {

    @Autowired
    private WxAccountDao wxAccountDao;

    @Test
    public void testAddWxAccount() {
        WxAccountEntity wxAccountEntity = new WxAccountEntity();
        wxAccountEntity.setAppId("1acaewf");
        wxAccountDao.save(wxAccountEntity);
    }
}

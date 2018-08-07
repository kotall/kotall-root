package com.kotall.oms.weixin;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testDigestUtil() {
        String signature = "29be312bf187820f3fb8275a34ed90d02d2d5df0";
        String timestamp = "1524144176";
        String nonce = "1458226663";
        String token = "kotall2018";
        List<String> list = new ArrayList<>();
        list.add(timestamp);
        list.add(nonce);
        list.add(token);
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        for (String s : list) {
            sb.append(s);
        }
        String encryptStr = DigestUtils.sha1Hex(sb.toString());
        Assert.assertEquals(signature, encryptStr);

    }
}

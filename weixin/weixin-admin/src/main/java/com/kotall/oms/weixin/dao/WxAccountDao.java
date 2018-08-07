package com.kotall.oms.weixin.dao;

import com.kotall.oms.weixin.entity.WxAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/2/28 0028 下午 10:53
 */
public interface WxAccountDao extends JpaRepository<WxAccountEntity, String> {

    @Query(" from wx_account where uid =:userId")
    WxAccountEntity getAccountByUserId(@Param("userId") String userId);

    @Query(" from wx_account where url =:url")
    WxAccountEntity getAccountByUrl(@Param("url")String url);
}

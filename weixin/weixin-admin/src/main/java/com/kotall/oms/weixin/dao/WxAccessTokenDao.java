package com.kotall.oms.weixin.dao;

import com.kotall.oms.weixin.entity.WxAccessTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/2 0002 下午 9:17
 */
public interface WxAccessTokenDao extends JpaRepository<WxAccessTokenEntity, String> {

}

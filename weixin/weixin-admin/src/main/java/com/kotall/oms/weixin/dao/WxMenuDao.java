package com.kotall.oms.weixin.dao;

import com.kotall.oms.weixin.entity.WxMenuEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/4/28
 */
public interface WxMenuDao extends JpaRepository<WxMenuEntity, Integer> {

	@Query(" from wx_menu where app_id =:appId")
	List<WxMenuEntity> findByAppId(@Param("appId")String appId);
}

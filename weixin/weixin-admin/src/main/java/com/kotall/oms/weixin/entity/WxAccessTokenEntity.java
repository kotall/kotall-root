package com.kotall.oms.weixin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/2/22
 */
@Getter
@Setter
@Entity(name = "wx_access_token")
@EntityListeners(AuditingEntityListener.class)
public class WxAccessTokenEntity {

    /**
     * 微信公众号 appId
     */
    @Id
    private String appId;

    /**
     * access_token
     */
    private String accessToken;

    /**
     * 有效时间
     */
    private String expiresIn;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(updatable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateTime;

    public WxAccessTokenEntity() {}

    public WxAccessTokenEntity(String appId, String accessToken, String expiresIn) {
        this.appId = appId;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }


}

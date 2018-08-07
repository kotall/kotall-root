package com.kotall.oms.weixin.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/2/13
 */
@Data
@ToString
@Entity(name = "wx_account")
@EntityListeners(AuditingEntityListener.class)
public class WxAccountEntity {

    /**
     * 用户id
     */
    private String uid;

    /**
     * 微信公众号 appId
     */
    @Id
    private String appId;

    /**
     * 微信公众号名称
     */
    private String appName;

    /**
     * 微信公众号 appSecret
     */
    private String appSecret;

    /**
     * 微信公众号 encodingAesKey
     */
    private String aesKey;

    /**
     * 微信公众号 token
     */
    private String token;

    /**
     * 对微信公众号服务器开发的 url
     */
    private String url;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;


}

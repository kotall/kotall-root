package com.kotall.oms.weixin.entity;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/4 0004 上午 10:12
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sys_user_token")
public class SysAccessTokenEntity {

    @Id
    private String uid;

    /**
     * access token
     */
    private String accessToken;

    /**
     * 有效时间
     */
    private Long expiresIn;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateTime;

    public SysAccessTokenEntity() {
    }

    public SysAccessTokenEntity(String uid, String accessToken, Long expiresIn) {
        this.uid = uid;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

}

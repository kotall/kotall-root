package com.kotall.oms.weixin.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/4/28
 */
@Data
@Entity(name = "wx_menu")
@EntityListeners(WxMenuEntity.class)
public class WxMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int parentId;

    @Column(name = "menu_name")
    private String name;

    @Column(name = "menu_type")
    private String type;

    @Column(name = "menu_key")
    private String key;

    private String url;

    private String appId;

    private String pagePath;

}

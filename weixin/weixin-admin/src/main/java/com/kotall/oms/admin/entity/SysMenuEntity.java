package com.kotall.oms.admin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Data
@Entity
@Table(name = "sys_menu")
@EntityListeners(AuditingEntityListener.class)
public class SysMenuEntity {

    /**
     * 上级菜单 ID, 为 0 标识根菜单
     */
    private int pid;

    /**
     * 菜单 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单图表
     */
    private String iconClass;

    /**
     * 菜单 url
     */
    private String url;

    /**
     * 菜单收缩或展开标识
     */
    private String eventIconClass;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;


}

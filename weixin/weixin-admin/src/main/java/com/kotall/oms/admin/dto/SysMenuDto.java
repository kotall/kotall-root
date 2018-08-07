package com.kotall.oms.admin.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Data
public class SysMenuDto implements Serializable {

    private int pid;

    /**
     * 菜单 ID
     */
    @Id
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


    /**
     * 子菜单
     */
    @SerializedName("subMenuList")
    private List<SysMenuDto> subMenuList;


}

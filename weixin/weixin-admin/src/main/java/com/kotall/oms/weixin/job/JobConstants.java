package com.kotall.oms.weixin.job;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/2/13
 */
public class JobConstants {
    /**
     * 微信刷新 accessToken Job 名称
     */
    public static final String WX_ACCESS_TOKEN_REFRESH_JOB = "RefreshWeiXinAccessTokenJob";

    /**
     * 微信 刷新 accessToken Job 任务分组
     */
    public static final String WX_ACCESS_TOKEN_REFRESH_GROUP = "Group_WeiXin";

    /**
     * 微信 刷新 accessToken Job 调度名称
     */
    public static final String WX_ACCESS_TOKEN_REFRESH_TRIGGER = "trigger_WeiXin";

    /**
     * 微信 刷新 accessToken Job 调度名称 立即执行
     */
    public static final String WX_ACCESS_TOKEN_REFRESH_TRIGGER_SIMPLE = "trigger_WeiXin_simple";

}

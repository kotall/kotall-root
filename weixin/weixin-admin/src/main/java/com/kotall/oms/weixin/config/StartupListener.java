package com.kotall.oms.weixin.config;

import com.github.aracwong.weixin.framework.annotation.WxHandler;
import com.github.aracwong.weixin.framework.core.WxDelegateRequestFilter;
import com.github.aracwong.weixin.framework.core.WxHandlerDispatcher;
import com.github.aracwong.weixin.framework.core.WxMsgHandlerRegistration;
import com.kotall.oms.weixin.job.DaemonScheduler;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Map;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/2/13
 */
@Configuration
@Slf4j
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DaemonScheduler daemonScheduler;
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            log.info("======== 1. 加载系统初始配置 ======== ");
            // 注册 WxHandler 处理器
            ApplicationContext applicationContext = event.getApplicationContext();

            log.info("======== 2. 开始启动定时任务 ======== ");
            daemonScheduler.scheduleJobs();
        } catch (SchedulerException e) {
            log.error("failed to schedule jobs ", e);
        }
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        return schedulerFactoryBean;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        Map<String, Object> handlers = applicationContext.getBeansWithAnnotation(WxHandler.class);

        WxMsgHandlerRegistration wxMsgHandlerRegistration = new WxMsgHandlerRegistration();
        for (Map.Entry<String, Object> entry : handlers.entrySet()) {
            Object object = entry.getValue();
            if (object instanceof WxDelegateRequestFilter) {
                wxMsgHandlerRegistration.registerHandler(((WxDelegateRequestFilter) object));
            }
        }

        WxHandlerDispatcher wxHandlerDispatcher = new WxHandlerDispatcher(new WxConfigDbStorageImpl(), wxMsgHandlerRegistration);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(wxHandlerDispatcher);
        servletRegistrationBean.addUrlMappings("/wx/request");

        return servletRegistrationBean;
    }

}

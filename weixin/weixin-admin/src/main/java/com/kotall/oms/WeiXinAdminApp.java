package com.kotall.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 应用启动器
 *
 * @author aracwong
 * @date 2017年9月3日 上午1:53:12
 * @since 1.0.0
 */
@SpringBootApplication
@EnableJpaAuditing
public class WeiXinAdminApp  {

    public static void main(String[] args) {
        SpringApplication.run(WeiXinAdminApp.class, args).start();
    }
}

package com.alan.mall.portal.shop.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {
        "com.alan.mall.portal.shop",
        "com.alan.mall.common.core",
        "com.alan.mall.service.user.sdk"})
public class MallPortalShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallPortalShopApplication.class, args);
    }

}

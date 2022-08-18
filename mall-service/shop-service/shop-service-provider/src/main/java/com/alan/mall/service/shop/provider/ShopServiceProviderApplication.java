package com.alan.mall.service.shop.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCaching
@SpringBootApplication
@MapperScan("com.alan.mall.service.shop.provider.dal")
@ComponentScan(basePackages = {
        "com.alan.mall.service.shop.provider",
        "com.alan.mall.common.tool",
        "com.alan.mall.common.core"})
public class ShopServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceProviderApplication.class, args);
    }

}

package com.alan.mall.service.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCaching
@SpringBootApplication
@MapperScan("com.alan.mall.service.user.provider.dal")
@ComponentScan(basePackages = {
        "com.alan.mall.service.user.provider",
        "com.alan.mall.common.tool",
        "com.alan.mall.common.core"})
public class UserServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
    }

}

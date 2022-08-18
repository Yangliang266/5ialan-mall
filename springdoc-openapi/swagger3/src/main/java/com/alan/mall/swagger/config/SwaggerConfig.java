package com.alan.mall.swagger.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: mathyoung
 * @description:
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi userOpenApi() {
        String[] packagesToscan = {"com.alan.mall.portal.user.controller"};
        return GroupedOpenApi.builder().group("users").packagesToScan(packagesToscan)
                .build();
    }
}

package com.alan.mall.portal.user.config;

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
        return GroupedOpenApi.builder().group("users1").packagesToScan(packagesToscan)
                .build();
    }

    @Bean
    public GroupedOpenApi userOpenApi1() {
        String[] packagesToscan = {"com.alan.mall.portal.user.controller"};
        return GroupedOpenApi.builder().group("user2").packagesToScan(packagesToscan)
                .build();
    }
}

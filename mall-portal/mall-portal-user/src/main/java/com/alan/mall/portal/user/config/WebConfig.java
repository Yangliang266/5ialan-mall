package com.alan.mall.portal.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.alan.mall.service.user.sdk.intercepter.TokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/error");
    }
}

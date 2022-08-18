package com.alan.mall.common.starter.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mathyoung
 * @Description
 * @ClassName ReddissonProperties
 * @date 2020/7/8 20:37
 */
@Data
@ConfigurationProperties(prefix = "spring.redis",ignoreUnknownFields = false)
public class ReddissonProperties {
    private String host = "localhost";

    private String password;

    private int timeout = 3000;

    private int dataBase;

    private RedissonPoolProperties pool;

    private int RetryAttempts = 3;

    private int IdleConnectionTimeout = 10000;

    private int RetryInterval = 1500;
}

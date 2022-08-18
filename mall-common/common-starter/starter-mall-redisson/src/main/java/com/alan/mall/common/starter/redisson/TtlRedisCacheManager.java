package com.alan.mall.common.starter.redisson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * @Auther: mathyoung
 * @description:
 */
public class TtlRedisCacheManager extends RedisCacheManager {
    public TtlRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        String[] cells = StringUtils.delimitedListToStringArray(name, "=");
        name = cells[0];
        if (cells.length > 1) {
            long ttl = Long.parseLong(cells[1]);
            // 根据传参设置缓存失效时间
            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
        }
        return super.createRedisCache(name, cacheConfig);
    }


}
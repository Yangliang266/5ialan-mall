package com.alan.mall.common.starter.redisson;

import com.alan.mall.common.core.globalvar.RedisGlobal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


/**
 * @author mathyoung
 * @Description
 * @ClassName RedissonAutoConfigration
 * @date 2020/7/8 20:36
 */
@Configuration
@EnableConfigurationProperties({ReddissonProperties.class})
@ConditionalOnClass({Redisson.class})
public class RedissonAutoConfigration {
    @Autowired
    ReddissonProperties reddissonProperties;

//    @Bean
////    @Primary
////    @ConditionalOnClass({Redisson.class})
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        String node = reddissonProperties.getHost();
//        String[] strNode = node.split(",");
//
//        if (strNode.length > 1) {
//            // redisson集群设置
//            config.useClusterServers().setScanInterval(2000);
//            for(int i = 0; i < strNode.length; i++) {
//                strNode[i] = strNode[i].startsWith("redis://") ? strNode[i] : "redis://" + strNode[i];
//                config.useClusterServers().addNodeAddress(strNode[i]);
//            }
//        } else {
//            // redisson单机设置
//            node = node.startsWith("redis://") ? node : "redis://" + node;
//            SingleServerConfig singleServerConfig = config.useSingleServer()
//                    .setAddress(node)
//                    .setConnectionMinimumIdleSize(reddissonProperties.getPool().getMinIdel())
//                    .setTimeout(reddissonProperties.getTimeout());
//
//            if (StringUtils.isNotBlank(reddissonProperties.getPassword())) {
//                singleServerConfig.setPassword(reddissonProperties.getPassword());
//            }
//
//        }
//        return Redisson.create(config);
//    }

//    @Autowired
//    ReddissonProperties redissonProperties;
//
    @Bean
    RedissonClient redissonClient() {
        Config config = new Config();
        String node = reddissonProperties.getHost();
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout(reddissonProperties.getTimeout())
                .setIdleConnectionTimeout(reddissonProperties.getIdleConnectionTimeout())
                .setRetryAttempts(reddissonProperties.getRetryAttempts())
                .setRetryInterval(reddissonProperties.getRetryInterval());
        if (StringUtils.isNotBlank(reddissonProperties.getPassword())) {
            serverConfig.setPassword(reddissonProperties.getPassword());
        }
        return Redisson.create(config);
    }

    public RedisCacheConfiguration getRedisCacheConfigurationWithTtl() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.
                fromSerializer(new StringRedisSerializer())).entryTtl(Duration.ofSeconds(RedisGlobal.TtlDefaultTime));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.
                fromSerializer(jackson2JsonRedisSerializer)).entryTtl(Duration.ofSeconds(RedisGlobal.TtlDefaultTime));

        return config;
    }

//    @Bean
//    public RedisCacheConfiguration getRedisCacheConfiguration(CacheProperties cacheProperties) {
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
//
//        if (redisProperties.getTimeToLive() != null) {
//            config = config.entryTtl(redisProperties.getTimeToLive());
//        }
//        if (redisProperties.getKeyPrefix() != null) {
//            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
//        }
//        if (!redisProperties.isCacheNullValues()) {
//            config = config.disableCachingNullValues();
//        }
//        if (!redisProperties.isUseKeyPrefix()) {
//            config = config.disableKeyPrefix();
//        }
//        return config;
//    }

    @Primary
    @Bean
    public RedisCacheManager ttlCacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new TtlRedisCacheManager(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory),
                // 默认缓存配置
                this.getRedisCacheConfigurationWithTtl());
    }



//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(2*60))//过期超时时间 2分钟
//                .disableCachingNullValues();
//
//        return RedisCacheManager.builder(factory)
//                .cacheDefaults(config)
//                .transactionAware()
//                .build();
//    }
//
//    @Bean
//    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//
//        RedisSerializer keySerializer = new StringRedisSerializer(); // 设置key序列化类，否则key前面会多了一些乱码
//        template.setKeySerializer(keySerializer);
//        setValueSerializer(template);//设置value序列化
//        template.afterPropertiesSet();
//        template.setEnableTransactionSupport(true);
//        return template;
//    }
//
//        private void setValueSerializer(StringRedisTemplate template) {
//        @SuppressWarnings({"rawtypes", "unchecked"})
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//    }




}

package com.alan.mall.common.core.cache;

/**
 * @Auther: mathyoung
 * @description:
 */
public abstract class AbstractCacheConstants {
    public static String generatorKey(long userId, String cacheKey){
        return cacheKey + ":" + userId;
    }
}

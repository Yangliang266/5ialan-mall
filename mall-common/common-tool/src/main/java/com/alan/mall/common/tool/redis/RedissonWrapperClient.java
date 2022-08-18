package com.alan.mall.common.tool.redis;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedissonWrapperClient {

    //private static RedissonClient redissonClient = null;
    @Autowired
    RedissonClient redissonClient;

    public String getScript(String script) {
        return  redissonClient.getScript().scriptLoad(script);
    }

    public RScript redissonScript() {
        return redissonClient.getScript();
    }

    /**
     * @Author mathyoung
     * @Description: countLatch
     * @Param: [lockName, lockNum]
     * @Return: org.redisson.api.RCountDownLatch
     */
    public RCountDownLatch getMqLock(String lockName, int lockNum) {
        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(lockName);
        rCountDownLatch.trySetCount(lockNum);
        return rCountDownLatch;
    }

    /**
     * @Description 检查是否存在缓存
     * @Param key
     * @return str
     * @Author Mathyoung
     * @Date 2020.07.09 20:14
     **/
    public String checkCache(String key) {
        try {
            RBucket<String> rBucket = redissonClient.getBucket(key);
            return rBucket.get();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * @Description 设置到cache
     * @Param key val expire
     * @return
     * @Author Mathyoung
     * @Date 2020.07.09 20:17
     **/
    public RBucket<String> setCache(String key, String val) {
        RBucket<String> rBucket = redissonClient.getBucket(key);
        rBucket.set(val);
        return rBucket;
    }


    public void setStringCache(String key, String val) {
        RBucket<String> rBucket = redissonClient.getBucket(key);
        rBucket.set(val);
    }


    public String getCache(String key) {
        RBucket<String> rBucket = redissonClient.getBucket(key);
        return rBucket.get();
    }

    /**
     * @Description 设置缓存超时时间
     * @Param key expire
     * @return
     * @Author Mathyoung
     * @Date 2020.07.09 20:19
     **/
    public void expireSeconds(String key, int expire) {
        RBucket<String> rBucket = redissonClient.getBucket(key);
        rBucket.expire(expire, TimeUnit.SECONDS);
    }

    public void expireDay(String key, int expire) {
        RBucket<String> rBucket = redissonClient.getBucket(key);
        rBucket.expire(expire, TimeUnit.DAYS);
    }

    /**
     * @Description 设置hash set
     * @Param String key, Object field, Object value, int expire
     * @return
     * @Author Mathyoung
     * @Date 2020.07.14 10:07
     **/
    public void setMapCache(String key, String field, String value) {
        RMap<String, String> map = redissonClient.getMap(key);
        map.put(field, value);
    }

    public boolean checkMapCache(String key, String field) {
        RMap<String, String> map = redissonClient.getMap(key);
        return map.containsKey(field);
    }

    public void removeMapCache(String key, String field) {
        RMap<String, String> map = redissonClient.getMap(key);
        map.remove(field);
    }

    public RMap<String, String> getMap(String key) {
        return redissonClient.getMap(key);
    }

    public String getMapField(String key, String filed) {
        RMap<String, String> map = redissonClient.getMap(key);
        return map.get(filed);
    }

    /**
     * @Description
     * @Param String key, String field, String value
     * @return
     * @Author Mathyoung
     * @Date 2020.07.14 12:37
     **/
    public boolean checkMapHashCache(String key, String field, String value) {
        RMap<String, String> map = redissonClient.getMap(key);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey());
            if (stringStringEntry.getKey().equals(field)) {
                if (stringStringEntry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public <T> RScoredSortedSet<T> getScoredSortedSet(String hotCache) {
        return redissonClient.getScoredSortedSet(hotCache);
    }


}

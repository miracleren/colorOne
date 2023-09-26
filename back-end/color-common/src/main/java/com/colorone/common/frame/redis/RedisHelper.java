package com.colorone.common.frame.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/7/4
 * @备注：redis 工具类
 */
@Component
public class RedisHelper {
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 通过key缓存基本的对象
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间类型
     */
    public <T> void setObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 通过key缓存基本的对象
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 通过key获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 通过key清除缓存对象
     *
     * @param key
     */
    public boolean clearObject(String key) {
        return redisTemplate.delete(key);
    }
}

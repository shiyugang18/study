package com.syg.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key     the key
     * @param value   the value
     * @param timeout 过期时间
     */
    public static void setString(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取指定 key 的值
     *
     * @param key the key
     * @return key 对应的 value
     */
    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static String getString(String key) {
        Object o = get(key);
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    public static <T> T getClass(String key, Class<T> cls) {
        String string = getString(key);
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        return JsonUtils.toObject(string, cls);
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

}

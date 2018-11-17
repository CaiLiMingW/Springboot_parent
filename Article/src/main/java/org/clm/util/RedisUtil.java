package org.clm.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Ccc
 * @date 2018/11/17 0017 上午 9:54
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> void set(String type,String key,T data){
        key = type + key;
        try {
            redisTemplate.opsForValue().set(key,data);
        } catch (Exception e) {
            log.error("setError:key:{},value:{}",key,data);
        }
    }

    public <T> T get(String type,String key){
        key = type + key;
        try {
            T data = (T)redisTemplate.opsForValue().get(key);
            return data;
        } catch (Exception e) {
            log.error("setError:key:{},value:{}",key);
        }
        return null;
    }
}

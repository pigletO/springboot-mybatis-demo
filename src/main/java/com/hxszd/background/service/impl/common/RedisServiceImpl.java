package com.hxszd.background.service.impl.common;

import com.alibaba.fastjson.JSONObject;
import com.hxszd.background.service.common.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis通用操作类
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void saveStr(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void saveObj(String key, Object value){
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
    }

    @Override
    public void saveStr(String key, String value, Long time){
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void saveObj(String key, Object value, Long time){
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(value), time, TimeUnit.SECONDS);
    }

    @Override
    public void saveStr(String key, String value, Long time, TimeUnit unit){
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    @Override
    public void saveObj(String key, Object value, Long time, TimeUnit unit){
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(value), time, unit);
    }

    @Override
    public String getStr(String key){
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getObj(String key, Class<T> clazz){
        return JSONObject.parseObject(redisTemplate.opsForValue().get(key), clazz);
    }

    @Override
    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }


}

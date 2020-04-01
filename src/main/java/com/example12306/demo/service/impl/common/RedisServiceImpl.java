package com.example12306.demo.service.impl.common;

import com.example12306.demo.service.common.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void saveStr(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void saveStr(String key, String value, Long time){
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void saveStr(String key, String value, Long time, TimeUnit unit){
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    @Override
    public String getStr(String key){
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }
}

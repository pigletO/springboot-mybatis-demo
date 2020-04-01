package com.example12306.demo.service.common;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
public interface IRedisService {

    /**
     * 永久保存字符串
     * @param key
     * @param value
     */
    void saveStr(String key, String value);

    /**
     * 保存字符串并按秒设置过期时间
     * @param key
     * @param value
     * @param time
     */
    void saveStr(String key, String value, Long time);

    /**
     * 保存字符串并按{@link TimeUnit}设置过期时间
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    void saveStr(String key, String value, Long time, TimeUnit unit);

    /**
     * 获取字符串value
     * @param key
     * @return
     */
    String getStr(String key);

    /**
     * 删除指定key
     * @param key
     * @return
     */
    Boolean delete(String key);
}

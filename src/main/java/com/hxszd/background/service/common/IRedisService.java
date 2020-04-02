package com.hxszd.background.service.common;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
public interface IRedisService {

    /**
     * 永久保存对象
     * @param key
     * @param value
     */
    void saveStr(String key, String value);

    /**
     * 永久保存对象
     * @param key
     * @param value
     */
    void saveObj(String key, Object value);

    /**
     * 保存对象并按秒设置过期时间
     * @param key
     * @param value
     * @param time
     */
    void saveStr(String key, String value, Long time);

    /**
     * 保存对象并按秒设置过期时间
     * @param key
     * @param value
     * @param time
     */
    void saveObj(String key, Object value, Long time);

    /**
     * 保存字符串并按{@link TimeUnit}设置过期时间
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    void saveStr(String key, String value, Long time, TimeUnit unit);

    /**
     * 保存对象并按{@link TimeUnit}设置过期时间
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    void saveObj(String key, Object value, Long time, TimeUnit unit);

    /**
     * 获取字符串value
     * @param key
     * @return
     */
    String getStr(String key);

    /**
     * 获取对象value
     * @param key
     * @return
     */
    <T> T getObj(String key, Class<T> clazz);

    /**
     * 删除指定key
     * @param key
     * @return
     */
    Boolean delete(String key);
}

package com.hxszd.background.service.common;

/**
 * @description: RocketMqService
 * @author: pig1etO
 * @create: 2020-07-23 13:34
 **/
public interface IRocketMqService {
    /**
     * 异步推送mq消息
     * @param topic
     * @param tags
     * @param body
     */
    void asyncSendMessage(String topic, String tags, String body);
}

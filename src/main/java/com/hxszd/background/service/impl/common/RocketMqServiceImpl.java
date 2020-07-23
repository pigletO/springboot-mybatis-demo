package com.hxszd.background.service.impl.common;

import com.hxszd.background.service.common.IRocketMqService;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-23 13:35
 **/
@Slf4j
@Service
@RocketMQMessageListener(topic = "async-topic", consumerGroup = "PushConsumer2")
public class RocketMqServiceImpl implements IRocketMqService, RocketMQListener<MessageExt> {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void asyncSendMessage(String topic, String tags, String body) {

        /*Message message = new Message();
        message.setTopic(topic);
        message.setTags(tags);
        message.setBody(body.getBytes(CharsetUtil.UTF_8));*/
        rocketMQTemplate.asyncSend(topic, body, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("发送失败！");
            }
        });
    }

    @Override
    public void onMessage(MessageExt message) {

        log.info("【消费者】收到mq消息：{}", message);
    }
}

package com.hxszd.background.controller;

import com.hxszd.background.pojo.dto.RocketMqDTO;
import com.hxszd.background.service.common.IRocketMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-23 13:45
 **/
@Slf4j
@RestController
@RequestMapping("/mq")
public class RocketMqController {

    @Autowired
    private IRocketMqService rocketMqService;

    @PostMapping("/asyncMessage")
    public void asyncSendMqMessage(@RequestBody @Validated(RocketMqDTO.validate.class) RocketMqDTO rocketMqDTO) {
        rocketMqService.asyncSendMessage(rocketMqDTO.getTopic(), rocketMqDTO.getTags(), rocketMqDTO.getBody());
    }
}

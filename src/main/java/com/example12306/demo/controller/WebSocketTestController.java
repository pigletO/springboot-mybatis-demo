package com.example12306.demo.controller;

import com.example12306.demo.service.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-03-09 14:54
 **/
@Slf4j
@RestController
@RequestMapping("webSocket/")
public class WebSocketTestController {

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("sendMessage")
    public void sendMessage(@RequestParam String message){
        webSocketServer.sendAllMessage(message);
        log.info("【SEND】message:{}", message);
    }

    @GetMapping("setSessionTimeOut")
    public void sendMessage(@RequestParam long time){
        webSocketServer.updateSessionTimeOutParam(time);
        log.info("【SEND】SessionTimeOut:{}ms", time);
    }

    @GetMapping("sendMessageByToken")
    public void sendMessageByToken(@RequestParam String token, @RequestParam String message){
        webSocketServer.sendMessageByToken(token, message);
        log.info("【SEND】token:{} message:{}", token, message);
    }
}

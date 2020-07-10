package com.hxszd.background.netty.websocket;

import lombok.Data;
import lombok.ToString;

/**
 * @description: websocket交互实体
 * @author: pig1etO
 * @create: 2020-07-08 19:27
 **/
@Data
@ToString(callSuper = true)
public class SwalkWebSocketProtocol {

    /**
     * 传递浏览器端token用来校验用户身份和权限控制
     */
    private String token = "1";

    /**
     * 目前项目中还未有前端主动通过webSocket请求后端数据的内容，设置此字段方便以指定请求url
     */
    private String requestUrl;

    /**
     * 用来传递请求指定url时的入参
     */
    private Object params;

    /**
     * 状态码 600心跳检测 601初始化链接 602断开链接
     */
    private String status;
}

package com.hxszd.background.netty.chatroom3.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 19:00
 **/
public class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        System.out.println("【客户端" + ch.localAddress() + "】与服务器建立连接！");
        ch.pipeline().addLast(new ChannelInBoundHandler());
    }
}

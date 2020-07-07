package com.hxszd.background.netty.chatroom3.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 14:38
 **/
public class ChannelInit extends ChannelInitializer<SocketChannel> {

    private static ChannelInit channelInit;

    public static List<SocketChannel> socketChannelList = new ArrayList<>();

    private ChannelInit() {

    }

    public static ChannelInit getInstance() {
        if (channelInit == null) {
            channelInit = new ChannelInit();
        }
        return channelInit;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        System.out.println("【客户端连接】" + ch.remoteAddress() + "上线了");
        // 储存所有客户端连接
        socketChannelList.add(ch);
        // pipeline是与channel一一对应的，是一个存储流式处理的管道
        ch.pipeline().addLast(new ChannelInBoundHandler());
    }
}

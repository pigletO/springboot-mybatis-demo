package com.hxszd.background.netty.chatroom3.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 19:02
 **/
public class ChannelInBoundHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【客户端】" + ctx.channel().localAddress());
        // TODO 客户端怎么做到非阻塞读取消息呢？
    }
}

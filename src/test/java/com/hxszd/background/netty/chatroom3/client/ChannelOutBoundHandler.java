package com.hxszd.background.netty.chatroom3.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 19:06
 **/
public class ChannelOutBoundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("【客户端】请输入聊天信息");
        if (scanner.hasNext()) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(scanner.next().getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(byteBuf);
        }
    }
}

package com.hxszd.background.netty.codec.custom.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 19:02
 **/
public class ChannelInBoundHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器发来消息" + msg);
        //ctx.writeAndFlush(Unpooled.copiedBuffer("12345678abcdefgh".getBytes(CharsetUtil.UTF_8)));
        ctx.writeAndFlush("12345678abcdefgh");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【客户端】" + ctx.channel().localAddress());
        ctx.writeAndFlush(1234L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("与服务器断开连接");
    }
}

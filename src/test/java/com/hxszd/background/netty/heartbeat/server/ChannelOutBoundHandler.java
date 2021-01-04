package com.hxszd.background.netty.heartbeat.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-08 17:05
 **/
public class ChannelOutBoundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundConnect");
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务器主动发送信息".getBytes(CharsetUtil.UTF_8)));
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("disconnect");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("OutBoundAdded");
    }

    public static void main(String[] args) {
        Thread.interrupted();
    }
}

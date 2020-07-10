package com.hxszd.background.netty.heartbeat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-08 16:32
 **/
public class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 心跳包 空闲读 空闲写 空闲读写 时间单位，当指定时间内触发了空闲时间，则自动调用pipeline的下一个handler的trigger方法
        socketChannel.pipeline().addLast(new ChannelOutBoundHandler()).addLast(new StringDecoder(CharsetUtil.UTF_8)).addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS))
        .addLast(new ChannelInBoundHandler()).addLast(new StringEncoder(CharsetUtil.UTF_8));
    }
}

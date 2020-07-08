package com.hxszd.background.netty.chatroom3.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 19:00
 **/
public class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8))
                .addLast(new ChannelInBoundHandler())
                .addLast(new StringEncoder(CharsetUtil.UTF_8));
    }
}

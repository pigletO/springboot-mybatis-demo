package com.hxszd.background.netty.codec.custom.server;

import com.hxszd.background.netty.codec.custom.decode.CustomLongDecoder;
import com.hxszd.background.netty.codec.custom.encode.CustomLongEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.springframework.core.codec.ByteBufferDecoder;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 14:38
 **/
public class ChannelInit extends ChannelInitializer<SocketChannel> {

    /**
     * 每一个客户端连接进来之后，都会有一个自己的channel pipeline
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        // pipeline是与channel一一对应的，是一个存储流式处理的管道
        ch.pipeline().addLast(new CustomLongEncoder())
                .addLast(new CustomLongDecoder())
                .addLast(new ByteArrayDecoder())
                .addLast(new ChannelInBoundHandler())
                .addLast(new ChannelStringInBoundHandler());
    }
}

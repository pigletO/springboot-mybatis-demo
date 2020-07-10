package com.hxszd.background.netty.protobuf.server;

import com.hxszd.background.netty.protobuf.proto.pojo.ProtoBufPOJO;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.Random;

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

        int random = new Random().nextInt(3);
        // pipeline是与channel一一对应的，是一个存储流式处理的管道
        ch.pipeline().addLast(new ProtobufDecoder(ProtoBufPOJO.summaryMessage.getDefaultInstance())).addLast(new ChannelInBoundHandler());
    }
}

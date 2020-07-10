package com.hxszd.background.netty.codec.custom.encode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description: 自定义编码器
 * @author: pig1etO
 * @create: 2020-07-10 16:17
 **/
public class CustomLongEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("调用了Long编码器");
        out.writeLong(msg);
    }
}

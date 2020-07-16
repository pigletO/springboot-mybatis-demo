package com.hxszd.background.netty.review.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-16 17:59
 **/
public class LongEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {

        System.out.println("调用了encoder");
        out.writeLong(msg);
    }
}

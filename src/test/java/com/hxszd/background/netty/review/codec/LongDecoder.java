package com.hxszd.background.netty.review.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-16 18:39
 **/
public class LongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("调用了decoder");
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}

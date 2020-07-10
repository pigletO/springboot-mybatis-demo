package com.hxszd.background.netty.codec.custom.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-10 16:47
 **/
public class CustomLongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("调用了Long解码器");
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}

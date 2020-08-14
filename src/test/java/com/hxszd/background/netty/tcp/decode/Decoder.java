package com.hxszd.background.netty.tcp.decode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-05 16:21
 **/
public class Decoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("调用了解码器");

        /*int readableBytes = in.readInt();
        byte[] bytes = new byte[readableBytes];
        in.readBytes(bytes);

        out.add(Unpooled.copiedBuffer(bytes));*/

        out.add(in.readBytes(in.readInt()));

    }
}

package com.hxszd.background.netty.file.encode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-05 16:39
 **/
public class Encoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        System.out.println("调用了编码器");
        int readableBytes = msg.readableBytes();
        out.writeInt(readableBytes);
        out.writeBytes(msg.toString(CharsetUtil.UTF_8).getBytes(CharsetUtil.UTF_8));
    }
}

package com.hxszd.background.netty.review3.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

public class CustomEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) {
        int length;
        if (o instanceof String) {
            System.out.println("Object Type: String!" );
            length = ((String) o).getBytes(CharsetUtil.UTF_8).length;
            byteBuf.writeInt(length);
            byteBuf.writeBytes(Unpooled.wrappedBuffer(((String) o).getBytes(CharsetUtil.UTF_8)));
        } else {
            System.out.println("Object Type: Integer!");
            length = 0;
            byteBuf.writeInt(length);
            byteBuf.writeInt((Integer) o);
        }

    }
}

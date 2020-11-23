package com.hxszd.background.netty.review3.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class CustomDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        int length = byteBuf.readInt();

        if (length > 0) {
            System.out.println("Param Type: String!");
            ByteBuf byteBuf1 = byteBuf.readBytes(length);
            list.add(byteBuf1.toString(CharsetUtil.UTF_8));
        } else {
            System.out.println("Param Type: Integer!");
            list.add(byteBuf.readInt());
        }
    }
}

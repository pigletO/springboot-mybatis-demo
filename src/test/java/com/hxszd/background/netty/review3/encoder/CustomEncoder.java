package com.hxszd.background.netty.review3.encoder;

import com.hxszd.background.netty.review3.entity.ProtocolObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-11-20 17:04
 **/
public class CustomEncoder extends MessageToByteEncoder<ProtocolObject> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolObject msg, ByteBuf out) throws Exception {
//        out.writeBytes(msg.getArgs_0())
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}

package com.hxszd.background.netty.review2.encoder;

import com.hxszd.background.netty.review2.entity.ProtocolObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-11-20 14:36
 **/
public class CustomEncoder extends MessageToByteEncoder<ProtocolObject> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolObject msg, ByteBuf out) throws Exception {
        byte[] nameByte = msg.getName().getBytes(CharsetUtil.UTF_8);
        byte[] valueByte = msg.getValue().getBytes(CharsetUtil.UTF_8);
        int length1 = nameByte.length;
        int length2 = valueByte.length;
        out.writeInt(length1);
        out.writeBytes(Unpooled.copiedBuffer(nameByte));
        out.writeInt(length2);
        out.writeBytes(Unpooled.copiedBuffer(valueByte));
    }
}

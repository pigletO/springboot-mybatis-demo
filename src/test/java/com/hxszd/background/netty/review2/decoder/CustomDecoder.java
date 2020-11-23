package com.hxszd.background.netty.review2.decoder;

import com.hxszd.background.netty.review2.entity.ProtocolObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-11-20 14:46
 **/
public class CustomDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readInt();
        ByteBuf name = in.readBytes(length);
        length = in.readInt();
        ByteBuf value = in.readBytes(length);

        ProtocolObject protocolObject = new ProtocolObject();
        protocolObject.setName(name.toString(CharsetUtil.UTF_8));
        protocolObject.setValue(value.toString(CharsetUtil.UTF_8));
        out.add(protocolObject);
    }
}

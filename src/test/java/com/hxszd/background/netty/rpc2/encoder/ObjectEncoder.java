package com.hxszd.background.netty.rpc2.encoder;

import com.alibaba.fastjson.JSONObject;
import com.hxszd.background.netty.rpc2.entity.ProtocolObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: pig1etO
 * @create: 2021-01-04 16:05
 **/
public class ObjectEncoder extends MessageToByteEncoder<ProtocolObject> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolObject msg, ByteBuf out) throws Exception {

        byte[] jsonByts = JSONObject.toJSONString(msg).getBytes(CharsetUtil.UTF_8);
        int lenth = jsonByts.length;
        out.writeInt(lenth);
        out.writeBytes(jsonByts);

    }
}

package com.hxszd.background.netty.rpc2.decoder;

import com.alibaba.fastjson.JSONObject;
import com.hxszd.background.netty.rpc2.entity.ProtocolObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2021-01-04 16:13
 **/
public class ObjectDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int lenth = in.readInt();
        ByteBuf bytes = in.readBytes(lenth);
        ProtocolObject protocolObject = JSONObject.parseObject(bytes.toString(CharsetUtil.UTF_8), ProtocolObject.class);
        System.out.println("【ObjectDecoder】:" + protocolObject.toString());
        out.add(protocolObject);
    }
}

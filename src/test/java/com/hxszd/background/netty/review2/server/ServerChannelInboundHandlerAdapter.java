package com.hxszd.background.netty.review2.server;

import com.hxszd.background.netty.review2.entity.ProtocolObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-11-20 14:19
 **/
public class ServerChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            System.out.println(((ByteBuf) msg).toString(CharsetUtil.UTF_8));
        } else if (msg instanceof ProtocolObject) {
            System.out.println(msg.toString());
        } else {
            System.out.println("收到消息，但是不是Netty ByteBuf~");
        }
    }
}

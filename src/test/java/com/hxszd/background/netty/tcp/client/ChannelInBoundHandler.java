package com.hxszd.background.netty.tcp.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-05 15:52
 **/
public class ChannelInBoundHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        System.out.println(msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TCP粘包拆包之小数据块合成一个大包发送
        for (int i = 0; i < 10; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("客户端消息" + i, CharsetUtil.UTF_8);
            ctx.writeAndFlush(byteBuf);
        }

        /*// TCP粘包拆包之大数据块拆分成一个个小包发送
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("客户端消息" + i);
        }
        ByteBuf byteBuf = Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("与服务器断开连接");
        ctx.disconnect();
    }
}

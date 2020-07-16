package com.hxszd.background.netty.review.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.CharSetUtils;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-16 17:54
 **/
public class ChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到客户端消息:" + msg);
        if (msg instanceof String) {
            String message = (String) msg;
            if (message.contains("1")) {
                ctx.writeAndFlush(123L);
                //ctx.writeAndFlush(Unpooled.copiedBuffer("byteBuf类型的数据".getBytes(CharsetUtil.UTF_8)));
            }

        } else {
            ctx.writeAndFlush(Unpooled.copiedBuffer("byteBuf类型的数据".getBytes(CharsetUtil.UTF_8)));
        }
    }

    /**
     * 空闲心跳触发事件
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()) {
                case ALL_IDLE:
                    System.out.println("读写空闲！");
                    break;
                default:
                    System.out.println("1111");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.disconnect();
    }
}

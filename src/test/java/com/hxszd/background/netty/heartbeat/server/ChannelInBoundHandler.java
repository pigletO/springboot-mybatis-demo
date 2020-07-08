package com.hxszd.background.netty.heartbeat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-08 16:36
 **/
public class ChannelInBoundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("触发了读空闲操作");
                    break;
                case WRITER_IDLE:
                    System.out.println("触发了写空闲操作");
                    break;
                case ALL_IDLE:
                    System.out.println("触发了读写空闲操作");
                    break;
                default:
            }
        }
    }
}

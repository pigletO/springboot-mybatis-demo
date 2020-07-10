package com.hxszd.background.netty.websocket.server;

import com.alibaba.fastjson.JSONObject;
import com.hxszd.background.netty.websocket.SwalkWebSocketProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-08 18:24
 **/
public class ChannelInBoundHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private AtomicInteger heartBeatCount = new AtomicInteger(0);

    // public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("浏览器发送来数据:" + msg.text());
        SwalkWebSocketProtocol protocol = JSONObject.parseObject(msg.text(), SwalkWebSocketProtocol.class);
        if ("600".equals(protocol.getStatus())) {
            heartBeatCount.getAndSet(0);
        }
        // 返回类型也需要时TextWebSocketFrame
        ctx.writeAndFlush(new TextWebSocketFrame("【服务器返回】" + msg.text()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接成功");
        System.out.println("channelGroup:");
        ChannelInit.channels.forEach(channel -> {
            System.out.println(channel.id().asLongText());
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开连接");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("【userEventTriggered】");
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("触发了读空闲操作,channel:" + ctx.channel().id().asLongText());
                    int count = heartBeatCount.incrementAndGet();
                    System.out.println("heartBeatCount" + count);
                    ctx.channel().writeAndFlush("读空闲");
                    break;
                case WRITER_IDLE:
                    System.out.println("触发了写空闲操作,channel:" + ctx.channel().id().asLongText());
                    int count1 = heartBeatCount.incrementAndGet();
                    System.out.println("heartBeatCount" + count1);
                    ctx.channel().writeAndFlush("写空闲");
                    break;
                /*case ALL_IDLE:
                    System.out.println("触发了读写空闲操作");
                    System.out.println("channelGroup:");
                    ChannelInit.channels.forEach(channel -> {
                        System.out.println(channel.id().asLongText());
                    });
                    System.out.println();
                    ctx.channel().writeAndFlush("读写空闲");
                    break;*/
                default:
            }

            // 心跳触发三次后断开链接
            if (heartBeatCount.compareAndSet(3, 99)) {
                ctx.channel().disconnect();
            }
        }
    }
}

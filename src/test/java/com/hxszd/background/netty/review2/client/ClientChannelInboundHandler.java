package com.hxszd.background.netty.review2.client;

import com.hxszd.background.netty.review2.entity.ProtocolObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @description: 每个socketChannel有自己的ChannelInboundHandler，不会有线程安全问题
 * @author: pig1etO
 * @create: 2020-11-20 13:53
 **/
public class ClientChannelInboundHandler extends SimpleChannelInboundHandler<String> {

    private int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到消息:" + msg);
        TimeUnit.SECONDS.sleep(2L);
        // 加了channel就会走编码，在pipeline执行链执行完毕后发送，也就是会走Encoder
        ctx.channel().writeAndFlush(ctx.channel().localAddress() + "浪里个浪~:)" + count++);
        // 不加channel就是我在这就要用当前的channel发送数据，此时就不会走Encoder了
        ctx.writeAndFlush(Unpooled.copiedBuffer("那我发送一个ByteBuff是不是可以接到呀！".getBytes(CharsetUtil.UTF_8)));
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered!");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered!");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive!");
        ctx.channel().writeAndFlush(ctx.channel().localAddress() + "浪里个浪~:)" + count);
        ProtocolObject protocolObject = new ProtocolObject();
        protocolObject.setName("滴滴滴~");
        protocolObject.setValue("出发喽~");
        ctx.channel().writeAndFlush(protocolObject);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInavtive!");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("userEventTriggered!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
    }
}

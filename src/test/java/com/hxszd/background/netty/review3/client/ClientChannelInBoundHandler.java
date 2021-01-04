package com.hxszd.background.netty.review3.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class ClientChannelInBoundHandler extends ChannelInboundHandlerAdapter implements Callable {

    private Object result;

    private ChannelHandlerContext channel;

    private Object param;

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("invoke success！");
        result = msg;
        notify();
    }

    @Override
    public synchronized Object call() throws Exception {
        System.out.println("param:" + param);
        channel.channel().writeAndFlush(param);
        wait();
        System.out.println("result:" + result);

        return result;
    }

    public void setParam(Object param) {
        this.param = param;
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx;
//        channel.writeAndFlush("你是谁");
        System.out.println("channelActive!");
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

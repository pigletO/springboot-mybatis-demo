package com.hxszd.background.netty.rpc2.server;

import com.hxszd.background.netty.rpc2.entity.ProtocolObject;
import com.hxszd.background.netty.rpc2.service.RpcService;
import com.hxszd.background.netty.rpc2.service.RpcServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: pig1etO
 * @create: 2021-01-04 13:33
 **/
public class StringChannelInBoundHandler extends SimpleChannelInboundHandler<String> {

    private RpcService rpcService = new RpcServiceImpl();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelInactive");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("【String】ChannelInBoundHandler channelRead");

        if (msg instanceof String) {
            ctx.channel().writeAndFlush(rpcService.methodA((String) msg));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelReadComplete");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        System.out.println("userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelWritabilityChanged");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("exceptionCaught");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        System.out.println("handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        System.out.println("handlerRemoved");
    }
}

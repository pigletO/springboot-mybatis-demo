package com.hxszd.background.netty.rpc.server;

import com.hxszd.background.service.NormalService;
import com.hxszd.background.service.impl.NormalServiceImpl;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-11 17:05
 **/
public class ServerChannelInboundHandler extends ChannelInboundHandlerAdapter {

    private NormalService normalService = new NormalServiceImpl();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("收到客户端消息" + msg.toString());
        if (msg.toString().startsWith("MyProtocolBuf#")) {
            String result = normalService.methodA(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.channel().writeAndFlush(result);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("readComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

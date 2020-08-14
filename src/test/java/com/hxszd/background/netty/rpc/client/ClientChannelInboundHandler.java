package com.hxszd.background.netty.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-11 17:22
 **/
public class ClientChannelInboundHandler extends SimpleChannelInboundHandler<String> implements Callable {

    private String result;

    private String param;
    
    private ChannelHandlerContext handlerContext;

    /**
     * TODO 为啥要加锁
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("调用接口返回数据:【"+ msg +"】");
        result = msg.toString();
        notify();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        handlerContext = ctx;
        //ctx.channel().writeAndFlush("MyProtocolBuf#hello");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * TODO 为啥需要加锁
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        handlerContext.channel().writeAndFlush(param);
        wait();
        return result;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

package com.hxszd.background.netty.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 注意这里每次进行一次RPC调用都会创建一个新的pipeline通道和执行链，当前实例不会被复用
 * @author: pig1etO
 * @create: 2020-08-11 17:22
 **/
public class ClientChannelInboundHandler extends SimpleChannelInboundHandler<String> implements Callable {

    private AtomicInteger count = new AtomicInteger(0);

    private String result;

    private String param;
    
    private ChannelHandlerContext handlerContext;

    /**
     * TODO 为啥要加锁 个人理解 多次调用远程接口，但是使用的是同一handlerContext上下文对象 并发时候必须要保证当前notify唤醒的线程是刚刚RPC调用时传入的参数获得的结果
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
        count.incrementAndGet();
        System.out.println("当前调用次数:" + count + ",param:" + param);
        handlerContext.channel().writeAndFlush(param);
        System.out.println(count + "*" + handlerContext.channel());
        System.out.println(count + "#" + handlerContext);
        wait();
        return result;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

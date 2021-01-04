package com.hxszd.background.netty.rpc2.client;

import com.hxszd.background.netty.rpc2.entity.ProtocolObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import org.junit.platform.commons.util.StringUtils;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: pig1etO
 * @create: 2021-01-04 13:23
 **/
public class ChannelInBoundHandler implements ChannelInboundHandler, Callable {

    private Channel channel;

    /**
     * 异步调用参数
     */
    private String params;

    private Object objectParams;

    /**
     * 调用结果
     */
    private Object result;

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

        // 获取到channel方便异步调用传参
        channel = ctx.channel();
        System.out.println("channelActive");
//        ctx.channel().writeAndFlush("hello");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelInactive");
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof String) {
            result = msg;
            System.out.println("返回类型【String】");
        }
        if (msg instanceof ProtocolObject) {
            result = msg;
            System.out.println("返回类型【ProtocolObject】");
        }
        System.out.println("channelRead");
        // 唤醒异步调用方法的线程
        notify();
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

    /**
     * 一定要添加synchronize，保证整个操作的线程安全，因为内部调用了wait方法，
     * 加了锁才能保证触发wait操作的跟用有锁的线程是一个线程，这样唤醒的时候才能保证正确
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        if (StringUtils.isNotBlank(params)) {
            System.out.println("异步调用开始！params:" + params);
            channel.writeAndFlush(params);
        } else {
            System.out.println("异步调用开始！objectParams:" + objectParams);
            channel.writeAndFlush(objectParams);
        }
        // 调用wait方法等待netty异步调用拿到结果之后唤醒此线程
        wait();
        return result;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setObjectParams(Object objectParams) {
        this.objectParams = objectParams;
    }
}

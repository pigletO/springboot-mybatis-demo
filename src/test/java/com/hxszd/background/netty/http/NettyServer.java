package com.hxszd.background.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @description: netty处理浏览器请求
 * @author: pig1etO
 * @create: 2020-06-29 11:32
 **/
public class NettyServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        System.out.println("客户端连接成功！@" + ch.remoteAddress());

                        // 增加读写处理handler
                        ch.pipeline().addLast(new Handler());
                    }
                });

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 55533)).sync();

            // 添加绑定端口监听事件
            channelFuture.addListener(new ChannelFutureListener() {
                /**
                 * 绑定成功后执行
                 * @param future
                 * @throws Exception
                 */
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {

                    // 判断当前方法(这里指绑定方法)是否执行完成，不代表执行成功，这里.isDone()为true时执行当前监听方法
                    // future.isDone();

                    // 判断当前方法(这里指绑定方法)是否被取消
                    // future.isCancelled();
                    // 获取当前方法(这里指绑定方法)执行出错原因
                    // future.cause();

                    // 判断当前方法是否执行成功
                    if (future.isSuccess()) {
                        System.out.println("服务器启动，成功绑定主机端口号：" + future.channel().localAddress().toString());
                    }

                }
            });

            // gracefully shut down server.
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // gracefully shut down server.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
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
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("数据读取");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 客户端断开连接的时候，也会在调用一次readComplete
        System.out.println("readComplete");
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("服务端数据".getBytes(CharsetUtil.UTF_8)));
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Writable");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Exception happened");
    }
}

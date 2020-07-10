package com.hxszd.background.netty.protobuf;

import com.hxszd.background.netty.protobuf.server.ChannelFutureListenerCustom;
import com.hxszd.background.netty.protobuf.server.ChannelInit;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-06 14:24
 **/
public class ChatRoomServer {

    public static void main(String[] args) {
        // 每个EventLoopGroup包含一个selector选择器
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 默认取主机cpu个数*2，即核心线程数
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // Server端的启动类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 设置工作组，boss仅负责监听客户端连接的Accept事件后将建立的channel交给worker处理，一般单线程足矣，
        serverBootstrap.group(bossGroup, workerGroup)
                // 设置boss和worker交互类型
                .channel(NioServerSocketChannel.class)
                // 设置boss的连接处理队列容量为128
                .option(ChannelOption.SO_BACKLOG, 128)
                // 设置worker的channel保持链接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 设置worker处理类
                .childHandler(new ChannelInit());

        try {
            // Server采用异步方式绑定一个指定ip端口号，返回一个future异步对象
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 55533)).sync();

            // 添加监听器
            channelFuture.addListener(new ChannelFutureListenerCustom());
            // 关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅的关闭boss&worker
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}

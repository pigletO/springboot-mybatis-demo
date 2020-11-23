package com.hxszd.background.netty.review2;

import com.hxszd.background.netty.review2.decoder.CustomDecoder;
import com.hxszd.background.netty.review2.encoder.CustomEncoder;
import com.hxszd.background.netty.review2.server.ServerChannelInboundHandler;
import com.hxszd.background.netty.review2.server.ServerChannelInboundHandlerAdapter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-11-20 13:24
 **/
public class NettyServerBootStrap {

    public static void main(String[] args) {
        startNettyServer();
    }

    private static void startNettyServer() {

        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;
        try {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            .channel(NioServerSocketChannel.class)
                    // 这里指workerGroup处理完accept事件之后，生成哪种channle执行后续处理逻辑
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    System.out.println(pipeline.channel().remoteAddress() + ":进入聊天室！");
                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new CustomEncoder());
                    pipeline.addLast(new ServerChannelInboundHandler());
                    pipeline.addLast(new ServerChannelInboundHandlerAdapter());
                    pipeline.addLast(new CustomDecoder());
                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                }
            });


            ChannelFuture sync = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 55533)).sync();
            sync.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {

                    if (future.isSuccess()) {
                        System.out.println("Netty Review Server Start up!");
                    }
                }
            });

            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

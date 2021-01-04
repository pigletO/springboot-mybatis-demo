package com.hxszd.background.netty.review3;

import com.hxszd.background.netty.review3.decoder.CustomDecoder;
import com.hxszd.background.netty.review3.encoder.CustomEncoder;
import com.hxszd.background.netty.review3.server.ServerChannelInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class NettyServer {

    public static void main(String[] args) {

        startNettyServer();
    }

    private static void startNettyServer() {

        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;

        try {

            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new CustomDecoder());
                            pipeline.addLast(new ServerChannelInboundHandler());
                            pipeline.addLast(new CustomEncoder());
//                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        }

                    });


            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 55533)).sync();

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("Netty Server Start up~！");
                    }
                }
            });

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

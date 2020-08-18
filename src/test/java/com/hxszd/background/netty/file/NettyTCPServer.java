package com.hxszd.background.netty.file;

import com.hxszd.background.netty.file.decode.Decoder;
import com.hxszd.background.netty.file.encode.Encoder;
import com.hxszd.background.netty.file.server.FileChannelInBoundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @description: 学习tcp粘包拆包
 * @author: pig1etO
 * @create: 2020-08-05 11:08
 **/
public class NettyTCPServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 设置TCP参数配置
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new Encoder());
                            pipeline.addLast(new Decoder());
                            pipeline.addLast(new FileChannelInBoundHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 55533)).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("服务器启动成功");
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

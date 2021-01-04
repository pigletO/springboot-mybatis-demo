package com.hxszd.background.netty.rpc2;

import com.hxszd.background.netty.rpc2.decoder.ObjectDecoder;
import com.hxszd.background.netty.rpc2.encoder.ObjectEncoder;
import com.hxszd.background.netty.rpc2.server.ProtocolObjectChannelInBoundHandler;
import com.hxszd.background.netty.rpc2.server.StringChannelInBoundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: pig1etO
 * @create: 2021-01-04 10:42
 **/
public class RpcServer {

    public static void main(String[] args) {

        startServer();
    }

    private static void startServer() {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectDecoder());
//                            pipeline.addLast(new ObjectEncoder());
//                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new StringChannelInBoundHandler());
                            pipeline.addLast(new ProtocolObjectChannelInBoundHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 55533)).sync();
            // 执行了绑定方法产生的ChannelFuture，这里监听的就是绑定方法
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {

                    // 代表执行了绑定操作，但是成不成功不知道
                    if (future.isDone()) {
                        System.out.println("执行端口绑定");
                    }
                    // 代表绑定成功成功
                    if (future.isSuccess()) {
                        System.out.printf("服务启动成功！" + future.channel().localAddress());
                    }
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 一定不能忘记啊
            Future bossFuture = bossGroup.shutdownGracefully();
            Future workerFuture = workerGroup.shutdownGracefully();
            if (bossFuture.isDone()) {
                try {
                    System.out.println("bossFuture:" + bossFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            if (workerFuture.isDone()) {
                try {
                    System.out.println("workerFuture:" + workerFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

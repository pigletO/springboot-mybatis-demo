package com.hxszd.background.netty.tcp;

import com.hxszd.background.netty.tcp.client.ChannelInBoundHandler;
import com.hxszd.background.netty.tcp.decode.Decoder;
import com.hxszd.background.netty.tcp.encode.Encoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-05 13:11
 **/
public class NettyTCPClient {

    public static void main(String[] args) {
        EventLoopGroup client = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(client)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new Encoder());
                            pipeline.addLast(new Decoder());
                            pipeline.addLast(new ChannelInBoundHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 55533)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            client.shutdownGracefully();
        }

    }
}

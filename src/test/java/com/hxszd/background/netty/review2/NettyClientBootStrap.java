package com.hxszd.background.netty.review2;

import com.hxszd.background.netty.review2.client.ClientChannelInboundHandler;
import com.hxszd.background.netty.review2.decoder.CustomDecoder;
import com.hxszd.background.netty.review2.encoder.CustomEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-11-20 13:40
 **/
public class NettyClientBootStrap {
    public static void main(String[] args) {
        startNettyClient();
    }

    private static void startNettyClient() {

        EventLoopGroup client = null;

        try {
            client = new NioEventLoopGroup(1);

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(client)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // 这里指准备发起连接事件采用哪种channel
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new CustomEncoder());
                            pipeline.addLast(new ClientChannelInboundHandler());
                            pipeline.addLast(new CustomDecoder());
                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        }
                    });

            ChannelFuture sync = bootstrap.connect(new InetSocketAddress("127.0.0.1", 55533)).sync();

            sync.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("Netty Client Start up!");
                    }
                }
            });

            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            client.shutdownGracefully();
        }
    }
}

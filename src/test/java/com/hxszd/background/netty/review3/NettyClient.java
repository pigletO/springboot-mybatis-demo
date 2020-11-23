package com.hxszd.background.netty.review3;

import com.hxszd.background.netty.review3.client.ClientChannelInBoundHandler;
import com.hxszd.background.netty.review3.decoder.CustomDecoder;
import com.hxszd.background.netty.review3.encoder.CustomEncoder;
import com.hxszd.background.service.NormalService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static ClientChannelInBoundHandler handler = null;

    public static void main(String[] args) {

        NormalService normalService = getProxy(NormalService.class);
        String str = normalService.methodA("我是124ABD！");
        Integer integer = normalService.methodB(666);

        System.out.println(str);

        System.out.println(integer);

        for (; ; ) {

        }

    }

    private static <T> T getProxy(Class<T> clazz) {

        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (proxy, method, args) -> {

            Object param = args[0];


            if (handler == null) {
                NettyClientStarter();
            }
            handler.setParam(param);

            Future submit = executor.submit(handler);
            return submit.get();

//            return handler.setParam(param);
        });

    }

    private static void NettyClientStarter() {

        EventLoopGroup client = null;
        try {
            client = new NioEventLoopGroup(1);

            Bootstrap bootstrap = new Bootstrap();

            handler = new ClientChannelInBoundHandler();

            bootstrap.group(client)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
//                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 每建立一个连接，则生成一个channel，也对应应该生成一个handler

                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new CustomDecoder());
                            pipeline.addLast(handler);
                            pipeline.addLast(new CustomEncoder());
//                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        }
                    });


            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 55533)).sync();

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("Netty Client Start up~!");
                    }
                }
            });
//            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            client.shutdownGracefully();
        }
    }
}

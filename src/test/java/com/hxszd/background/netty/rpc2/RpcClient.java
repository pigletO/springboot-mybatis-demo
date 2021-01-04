package com.hxszd.background.netty.rpc2;

import com.hxszd.background.netty.rpc2.client.ChannelInBoundHandler;
import com.hxszd.background.netty.rpc2.decoder.ObjectDecoder;
import com.hxszd.background.netty.rpc2.encoder.ObjectEncoder;
import com.hxszd.background.netty.rpc2.entity.ProtocolObject;
import com.hxszd.background.netty.rpc2.service.RpcService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: pig1etO
 * @create: 2021-01-04 11:20
 **/
public class RpcClient {

    private static ChannelInBoundHandler handler;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        RpcService service = getBean(RpcService.class);
//        System.out.println(service.methodA("哈哈哈哈哈哈"));
        ProtocolObject object = new ProtocolObject();
        object.setArgs_0("真有意思！");
        object.setArgs_1(1000);
        object.setArgs_2(9999L);
        object.setArgs_3(new Date());
        object.setArgs_4(LocalDate.now());
        System.out.println(service.methodB(object));

        long start2 = System.currentTimeMillis();
        System.out.println("【第一次】执行时间:" + (start2 - start));

        ProtocolObject object2 = new ProtocolObject();
        object2.setArgs_0("再来一次！");
        object2.setArgs_1(1000);
        object2.setArgs_2(9999L);
        object2.setArgs_3(new Date());
        object2.setArgs_4(LocalDate.of(2021, 2, 14));
        System.out.println(service.methodB(object2));
        System.out.println("【第二次】执行时间:" + (System.currentTimeMillis() - start2));

    }

    private static <T> T getBean(Class clazz) {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, (Object proxy, Method method, Object[] args) -> {

            if (handler == null) {
                startClient();
            }

            String params;
            if (args.length > 0) {
                if (args[0] instanceof String) {
                    params = (String) args[0];
                    handler.setParams(params);
                }
                if (args[0] instanceof ProtocolObject) {
                    handler.setObjectParams(args[0]);
                }
            }

            FutureTask task = new FutureTask(handler);
            new Thread(task).start();

            return task.get();
        });
    }

    private static void startClient() {

        EventLoopGroup clientGroup = new NioEventLoopGroup(1);

        try {
            Bootstrap bootstrap = new Bootstrap();

            handler = new ChannelInBoundHandler();

            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new ObjectDecoder());
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
//                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(handler);
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 55533)).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("连接成功！");
                    }
                }
            });
//            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            clientGroup.shutdownGracefully();
        }
    }
}

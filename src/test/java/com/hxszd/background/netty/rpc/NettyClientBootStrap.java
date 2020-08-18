package com.hxszd.background.netty.rpc;

import com.hxszd.background.netty.rpc.client.ClientChannelInboundHandler;
import com.hxszd.background.service.NormalService;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-11 18:29
 **/
public class NettyClientBootStrap {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static ClientChannelInboundHandler clientChannelInboundHandler;

    public static void main(String[] args) {

        // 获取RCP接口方法代理对象
        NormalService proxyObject = getProxyBean(NormalService.class);

        /*String result = proxyObject.methodA("你好 dubbo~");

        System.out.println(result);*/

        for (int i=0;i<100;i++ ) {
            try {
                Thread.sleep( 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //通过代理对象调用服务提供者的方法(服务)
            String res = proxyObject.methodA("你好 dubbo~");
            System.out.println("调用的结果 res= " + res);
        }
        //startClient();

    }

    public static void startClient() {

        EventLoopGroup clientGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        clientChannelInboundHandler = new ClientChannelInboundHandler();

        try {
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    //.option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(clientChannelInboundHandler);
                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        }
                    });


            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 55533)).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("传输通道准备好，准备发送数据...");
                    }
                }
            });

            // 不能将主线程停在这
            //channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 不能关闭无客户端服务
            //clientGroup.shutdownGracefully();
        }
    }

    /**
     * 创建代理类
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getProxyBean(Class<T> clazz) {
        T bean = (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz},
                (Object proxy, Method method, Object[] args) -> {

                    System.out.println("调用代理方法");

                    args[0] = "MyProtocolBuf#" + args[0].toString();

                    if (clientChannelInboundHandler == null){
                        startClient();
                    }
                    clientChannelInboundHandler.setParam(args[0].toString());

                    Future future = executor.submit(clientChannelInboundHandler);

                    //Object result = method.invoke(object, args);
                    return future.get();
                }/*new Handler(executor, clientChannelInboundHandler)*/);
        return bean;
    }

}

/*class Handler implements InvocationHandler {

    private static ExecutorService executor;

    private static ClientChannelInboundHandler clientChannelInboundHandler;

    public Handler(ExecutorService executor, ClientChannelInboundHandler clientChannelInboundHandler) {
        this.executor = executor;
        this.clientChannelInboundHandler = clientChannelInboundHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理方法");

        args[0] = "MyProtocolBuf#" + args[0].toString();

        if (clientChannelInboundHandler == null){
            NettyClientBootStrap.startClient();
        }

        clientChannelInboundHandler.setParam(args[0].toString());

        Future future = executor.submit(clientChannelInboundHandler);

        //Object result = method.invoke(object, args);
        return future.get();
    }


}*/

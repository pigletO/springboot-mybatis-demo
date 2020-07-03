package com.hxszd.background.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @description: netty处理浏览器请求
 * @author: pig1etO
 * @create: 2020-06-29 11:32
 **/
public class NettyHttpServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInit());

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 55533)).sync();

            // 添加绑定端口监听事件
            channelFuture.addListener(new ChannelFutureListener() {
                /**
                 * 绑定成功后执行
                 * @param future
                 * @throws Exception
                 */
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {

                    // 判断当前方法(这里指绑定方法)是否执行完成，不代表执行成功，这里.isDone()为true时执行当前监听方法
                    // future.isDone();

                    // 判断当前方法(这里指绑定方法)是否被取消
                    // future.isCancelled();
                    // 获取当前方法(这里指绑定方法)执行出错原因
                    // future.cause();

                    // 判断当前方法是否执行成功
                    if (future.isSuccess()) {
                        System.out.println("服务器启动，成功绑定主机端口号：" + future.channel().localAddress().toString());
                    }

                }
            });

            // gracefully shut down server.
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // gracefully shut down server.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class ChannelInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("客户端连接成功！");
        System.out.println(ch.remoteAddress());

        ch.pipeline().addLast("HttpServerCodec", new HttpServerCodec()).addLast(new HttpHandler());

    }
}

class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println("请求数据类型" + msg.getClass());

        if (msg instanceof HttpRequest) {
            System.out.println("读取客户端数据");

            HttpRequest httpRequest = (HttpRequest) msg;
            //  /favicon.ico
            System.out.println(httpRequest.uri());
            if (!httpRequest.uri().contains("favicon.ico")) {
                ByteBuf byteBuf = Unpooled.copiedBuffer("服务器返回数据".getBytes(CharsetUtil.UTF_8));
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8")
                        .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

                ctx.writeAndFlush(response);
            } else {
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
                ctx.writeAndFlush(response);
            }
        } else if (msg instanceof HttpContent) {

            System.out.println(msg);
        }



    }
}

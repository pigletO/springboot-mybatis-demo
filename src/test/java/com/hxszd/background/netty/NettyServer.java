package com.hxszd.background.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @description: 初识Netty
 * @author: pig1etO
 * @create: 2020-06-06 08:51
 **/
public class NettyServer {

    public static void main(String[] args) {
        // The first one, often called 'boss', accepts an incoming connection.
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // The second one, often called 'worker', handles the traffic of the accepted connection
        // once the boss accepts the connection and registers the accepted connection to the worker.
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // ServerBootstrap is a helper class that sets up a server. You can set up the server using a Channel directly.
        // However, please note that this is a tedious process, and you do not need to do that in most cases.
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new Handler());
            }
        }).option(ChannelOption.SO_BACKLOG, 128)
        .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            // Bind and start to accept incoming connections.
            ChannelFuture f = bootstrap.bind(55533).sync();

            // Wait until the server socket is closed.

            // shut down your server.  gracefully
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // gracefully ^_^ haha
            workerGroup.shutdownGracefully();
            // gracefully ^_^ haha
            bossGroup.shutdownGracefully();
        }

    }
}

class Handler extends ChannelInboundHandlerAdapter {

    // We override the channelRead() event handler method here. This method is called with the received message,
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {


            ByteBuf buf = (ByteBuf) msg;
            // Discard the received data silently.

            /*String str;
            if (buf.hasArray()) { // 处理堆缓冲区
                str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes(), "UTF-8");
            } else { // 处理直接缓冲区以及复合缓冲区
                byte[] bytes = new byte[buf.readableBytes()];
                buf.getBytes(buf.readerIndex(), bytes);
                str = new String(bytes, 0, buf.readableBytes(), "UTF-8");
            }
            System.out.println(str);*/

            if (buf.isReadable()) { // (1)
                System.out.println(buf.toString(CharsetUtil.UTF_8));
                /*System.out.print((char) buf.readByte());
                System.out.flush();*/
            }
        } finally {
            // Please keep in mind that it is the handler's responsibility to release any reference-counted object passed to the handler.
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    // The exceptionCaught() event handler method is called with a Throwable when an exception was raised by Netty due to
    // an I/O error or by a handler implementation due to the exception thrown while processing events.
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
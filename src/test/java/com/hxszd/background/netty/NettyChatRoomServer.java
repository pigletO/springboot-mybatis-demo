package com.hxszd.background.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @description: 使用netty构建一个聊天服务
 * @author: pig1etO
 * @create: 2020-06-28 14:04
 **/
public class NettyChatRoomServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)
                // 选择主从reactor之间的交互通道类型
                .channel(NioServerSocketChannel.class)
                // 给bossGroup设定缓冲队列容量
                .option(ChannelOption.SO_BACKLOG, 128)
                // 给workerGroup设定持续保持链接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 设置workerGroup初始化方法
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 添加处理类
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });

        ChannelFuture channelFuture = null;
        try {
            channelFuture = serverBootstrap.bind(new InetSocketAddress(55533)).sync();

            // Wait until the server socket is closed.

            // shut down your server.
            channelFuture.channel().closeFuture().sync();
            System.out.println("服务端关闭了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}

class ServerHandler extends ChannelInboundHandlerAdapter {

    private long start;

    /**
     * 通读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        start = System.currentTimeMillis();
        System.out.println("读取数据");
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));

        // 待执行队列，队列中任务按顺序执行，先进先出FIFO
        ctx.channel().eventLoop().execute(() ->{
            try {
                Thread.sleep(5000);
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("延迟" + (System.currentTimeMillis() - start) + "ms后服务端发送数据！").getBytes(CharsetUtil.UTF_8)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 待执行队列
        ctx.channel().eventLoop().execute(() ->{
            try {
                Thread.sleep(10000);
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("延迟" + (System.currentTimeMillis() - start) + "ms后服务端发送数据！").getBytes(CharsetUtil.UTF_8)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 延迟执行队列
        ctx.channel().eventLoop().schedule(() -> {
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("延迟" + (System.currentTimeMillis() - start) + "ms后服务端发送数据！").getBytes(CharsetUtil.UTF_8)));
        }, 5, TimeUnit.SECONDS);

    }

    /**
     * 数据读取之后的处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName() + "读取数据处理结束");
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端返回数据".getBytes(CharsetUtil.UTF_8)));
    }

    /**
     * 异常捕获处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName() + "通道连接异常！已关闭");
        ctx.channel().close();
    }
}

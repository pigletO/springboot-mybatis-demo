package com.hxszd.background.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @description: netty聊天室客户端
 * @author: pig1etO
 * @create: 2020-06-28 16:53
 **/
public class NettyChatRoomClient {

    public static void main(String[] args) {
        EventLoopGroup clientGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(clientGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ClientHandler());
            }
        });


        try {
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 55533)).sync();

            channelFuture.channel().closeFuture().sync();
            System.out.println("客户端关闭了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
            clientGroup.shutdownGracefully();
        }

    }
}

class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器断开连接");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接成功");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入:");
        while (scanner.hasNext()) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(scanner.next().getBytes(CharsetUtil.UTF_8)));
            break;
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("读取服务器返回数据：" + byteBuf.toString(CharsetUtil.UTF_8));
    }
}

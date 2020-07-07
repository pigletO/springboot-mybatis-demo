package com.hxszd.background.netty.chatroom3;

import com.hxszd.background.netty.chatroom3.client.ChannelInit;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @description: netty聊天室客户端
 * @author: pig1etO
 * @create: 2020-07-07 15:56
 **/
public class ChatRoomClient {

    public static void main(String[] args) {

        EventLoopGroup clientLoopGroup = new NioEventLoopGroup(1);
        //EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 客户端启动类
        Bootstrap bootstrap = new Bootstrap();

        // 启动类托管
        bootstrap.group(clientLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInit());

        try {
            // 客户端连接服务器
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 55533)).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            clientLoopGroup.shutdownGracefully();
        }
    }
}

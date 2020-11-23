package com.hxszd.background.nio.review.chartroom;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @description: 复习nio-聊天室客户端
 * @author: pig1etO
 * @create: 2020-10-12 16:27
 **/
public class chatRoomClient {

    public static void main(String[] args) {

        try {
            // 开启一个nio客户端
            SocketChannel socketChannel = SocketChannel.open();
            // 设置为非阻塞
            socketChannel.configureBlocking(false);

            //boolean result = socketChannel.connect(new InetSocketAddress("127.0.0.1", 55533));
            boolean result = socketChannel.connect(new InetSocketAddress("127.0.0.1", 55533));

            if (result) {
                OutputStream out = socketChannel.socket().getOutputStream();
                out.write("客户端消息".getBytes(CharsetUtil.UTF_8));
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

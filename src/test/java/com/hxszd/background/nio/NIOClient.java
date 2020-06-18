package com.hxszd.background.nio;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-18 17:38
 **/
public class NIOClient {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            // 设置非阻塞IO
            socketChannel.configureBlocking(false);
            // 绑定通信端口号
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 55533));

            while (socketChannel.finishConnect()) {
                break;
            }

            /*if (!socketChannel.connect(new InetSocketAddress(55533))) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("表示在Channel连接过程中，线程是非阻塞的！");
                }
            }*/

            socketChannel.write(ByteBuffer.wrap("链接已建立，你好！".getBytes(CharsetUtil.UTF_8)));
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            socketChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array(), CharsetUtil.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

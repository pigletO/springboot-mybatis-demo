package com.hxszd.background.nio.review.chartroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @description: 复习练习-nio实现聊天室服务端
 * @author: pig1etO
 * @create: 2020-10-12 15:43
 **/
public class ChatRoomServer {

    private static Selector selector;

    private static ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {

        // 开启一个nio服务端
        try {
            serverSocketChannel = ServerSocketChannel.open();
            // 设置为一个非阻塞io
            serverSocketChannel.configureBlocking(false);
            // 绑定一个主机服务地址和端口
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 55533));
            // 开启一个多路复用器selector
            selector = Selector.open();
            // 将nio服务注册给selector，并监听accept事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 开始循环监听获取连接事件
            while (true) {
                // 阻塞式等待监听accept事件
                //selector.select();
                // 非阻塞式监听accept事件，1s为一个周期
                if (selector.select(1000) != 0){

                    Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                    while (selectedKeys.hasNext()) {
                        SelectionKey key = selectedKeys.next();

                        handler(key);
                        selectedKeys.remove();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handler(SelectionKey key) {
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
            // 相对于BIO，NIO通过selector多路复用器实现一个线程通过阻塞的方式去监听是否有accept事件打进来，一旦发现事件触发，
            // 则从SelectionKey中拿到当前连接的通道，然后在调用accept方法，此时能保证accept不会被阻塞在这里。
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel)key.channel();
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                // 读取信息
                socketChannel.read(byteBuffer);
                System.out.printf("收到消息：" + new String(byteBuffer.array(), "utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

package com.hxszd.background;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: NIO服务端
 * @author: pig1etO
 * @create: 2020-05-27 15:15
 **/
public class SocketChannelServer {

    public static void main(String[] args) {

        ServerSocketChannel serverSocketChannel = null;


        try {
            // 开启NIO
            serverSocketChannel = ServerSocketChannel.open();

            // 设置使用非阻塞方式
            serverSocketChannel.configureBlocking(false);

            // 获取一个selector选择器对象
            Selector selector = Selector.open();

            // 注册selector，监听accept事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                // 获取触发了IO操作的事件的key（accept、read、write）
                selector.select();

                // 拿到这些key的集合
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    // 需要移除对应的key，否则下次循环遍历会再次处理这个key
                    it.remove();
                    handler(key);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handler(SelectionKey key) {
        // 判断当前key是否为一个accept连接事件key
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);

                // 注册selector用来监听当前通道的InputStream读取事件
                socketChannel.register(key.selector(), SelectionKey.OP_READ);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (key.isReadable()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            byteBuffer.clear();

            try {
                SocketChannel socketChannel = (SocketChannel)key.channel();
                socketChannel.read(byteBuffer);
                byteBuffer.flip();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}

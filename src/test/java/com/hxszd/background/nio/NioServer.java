package com.hxszd.background.nio;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-16 17:36
 **/
public class NioServer {

    public static void main(String[] args) {
        // 开启nio
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置为非阻塞IO
            serverSocketChannel.configureBlocking(false);
            // 绑定监听的端口号
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 55533));
            // 创建一个selector对象 准备轮询监听io accept连接请求
            Selector selector = Selector.open();

            // 向NIO注册一个selector用来监听accept事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                // 等待触发accept事件
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // 如果有io进来触发了accept事件
                if (iterator.hasNext()) {
                    handler(iterator.next());
                    // 需要移除当前key否则会多处理一次当前连接
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handler(SelectionKey key) {

        // 当前事件是一个accept连接事件
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            Selector selector = key.selector();
            SocketChannel socketChannel = null;
            try {
                socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                // 注册可读事件
                socketChannel.register(selector, SelectionKey.OP_READ);
                System.out.printf("current connection is readable!");
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socketChannel.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        // 当前事件是一个读取数据事件
        else if (key.isReadable()) {

            SocketChannel channel = (SocketChannel)key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            try {
                channel.read(byteBuffer);
                byteBuffer.flip();
                System.out.printf("current io string follow is...");
                // 只截取有效位数字符串
                String str = new java.lang.String(byteBuffer.array(), 0, byteBuffer.limit(), CharsetUtil.UTF_8);
                System.out.println(str);

                // TODO 不知道为啥 已经关闭了连接 还是会进isReadable方法 所以暂时使用判断长度来关闭链接
                if (str.length() == 0) {
                    key.cancel();
                }
                /*ByteBuffer out = ByteBuffer.wrap("connection will disconnect! see you！".getBytes(CharsetUtil.UTF_8));
                out.flip();
                channel.write(out);*/
                //key.cancel();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    channel.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (!key.isConnectable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

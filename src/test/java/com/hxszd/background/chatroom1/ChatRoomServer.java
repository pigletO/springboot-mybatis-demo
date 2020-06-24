package com.hxszd.background.chatroom1;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @description: 聊天室 server端
 * @author: pig1etO
 * @create: 2020-06-18 18:06
 **/
public class ChatRoomServer {
    public static void main(String[] args) {

        try {
            // 开启NIO
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置非阻塞IO通信
            serverSocketChannel.configureBlocking(false);
            // 设置服务端监听端口
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 55533));

            // 创建selector大管家 要开始工作了
            Selector selector = Selector.open();
            // 向selector注册NIO主线程服务，并监听accept事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 开始轮询准备干活了
            while (true) {
                // 阻塞式等待事件发生
                selector.select();
                /*// 只等待1s
                selector.select(1000);
                // 不等待
                selector.selectNow();*/

                // 连接打进来了，获取发生了accept事件的SelectionKey
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()) {
                    // 做处理
                    handler(selectionKeys.next());
                    // 移除已经处理过的key
                    selectionKeys.remove();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handler(SelectionKey key) {
        // 监听到了accept事件，要建立连接
        if (key.isAcceptable()) {
            // 我的理解是，Selector监听的ServerSocketChannel的accept事件，所以需要用此类处理，另一方面接收链接请求需要此类
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();

            SocketChannel socketChannel = null;
            try {
                socketChannel = serverSocketChannel.accept();
                // 建立的通道也需要是非阻塞的，因为他的Server是非阻塞的
                socketChannel.configureBlocking(false);
                // 将此通道注册到Selector大管家（多路复用器），并监听读取事件
                socketChannel.register(key.selector(), SelectionKey.OP_READ);

                System.out.println("Client has connected!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (key.isReadable()) {
            // 监听到了socketChannel的读取事件
            // 获取SocketChannel
            SocketChannel socketChannel = (SocketChannel) key.channel();

            // 设置一个ByteBuffer数组，用以读取数据，数据会按顺序和大小读取到数组中
            ByteBuffer[] byteBuffers = new ByteBuffer[3];
            byteBuffers[0] = ByteBuffer.allocate(6);
            byteBuffers[1] = ByteBuffer.allocate(3);
            byteBuffers[2] = ByteBuffer.allocate(512);
            try {
                socketChannel.read(byteBuffers);

                System.out.printf("收到消息: ");
                for (ByteBuffer byteBuffer : byteBuffers) {
                    // 读取后需要反转position，limit，capacity指针
                    byteBuffer.flip();
                    System.out.printf(new String(byteBuffer.array(), CharsetUtil.UTF_8) + "/");
                }
                System.out.println();

                // TODO 如何保证长链接不断？
                byteBuffers[0].flip();
                String str1 = new String(byteBuffers[0].array(), CharsetUtil.UTF_8);
                if (str1.length() == 0) {
                    // 取消注册
                    key.cancel();
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

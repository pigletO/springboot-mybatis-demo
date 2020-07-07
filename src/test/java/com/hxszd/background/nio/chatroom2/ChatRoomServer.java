package com.hxszd.background.nio.chatroom2;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @description: NIO聊天室 server端
 * @author: pig1etO
 * @create: 2020-06-18 18:06
 **/
public class ChatRoomServer {

    private static Selector selector;

    private static ServerSocketChannel serverSocketChannel;

    private static LinkedList<SocketAddress> clientRemoteAddress = new LinkedList<>();

    public static void main(String[] args) {

        try {
            // 开启NIO
            serverSocketChannel = ServerSocketChannel.open();
            // 设置非阻塞IO通信
            serverSocketChannel.configureBlocking(false);
            // 设置服务端监听端口
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 55533));

            // 创建selector大管家 要开始工作了
            selector = Selector.open();
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

                // 保存连接上来的客户端地址
                clientRemoteAddress.add(socketChannel.getRemoteAddress());

                System.out.println(socketChannel.getRemoteAddress() + " Client has connected!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (key.isReadable()) {
            // 监听到了socketChannel的读取事件
            // 获取SocketChannel
            SocketChannel socketChannel = (SocketChannel) key.channel();

            // 设置一个ByteBuffer数组，用以读取数据，数据会按顺序和大小读取到数组中，但需要注意一个中文字符三个字节，若ByteBuffer数组的剩余空间不够，会将中文拆分存储到下一个节点中，会造成数据读取错误，难用
            /*ByteBuffer[] byteBuffers = new ByteBuffer[3];
            byteBuffers[0] = ByteBuffer.allocate(6);
            byteBuffers[1] = ByteBuffer.allocate(3);
            byteBuffers[2] = ByteBuffer.allocate(512);*/
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            try {
                socketChannel.read(byteBuffer);

                System.out.printf("收到来自@\"%s\"的消息: ", socketChannel.getRemoteAddress());
                System.out.println();
                //for (ByteBuffer byteBuffer : byteBuffers) {
                    // 读取后需要反转position，limit，capacity指针，ByteBuffer在被new String之后，ByteBuffer会被清空，不可以在使用
                    byteBuffer.flip();
                    String str = new String(byteBuffer.array(), CharsetUtil.UTF_8);
                    System.out.println(str);
                    // 此处遍历所有key
                    for (SelectionKey key1 : selector.keys()){
                        Channel channel = key1.channel();
                        // 需要判断channel类型，可能是ServerSocketChannel
                        if (channel instanceof SocketChannel) {
                            SocketChannel socketChannel1 = (SocketChannel) channel;
                            // 不发送给自己
                            if (socketChannel1 != socketChannel1) {
                                System.out.println("群发给@" + socketChannel1.getRemoteAddress());
                                socketChannel1.write(ByteBuffer.wrap(str.getBytes(CharsetUtil.UTF_8)));
                            }
                        } else {
                            // System.out.println("ServerSocketChannel!");
                        }
                    }
               // }
                System.out.println();
/*
                // TODO 如何保证长链接不断？
                byteBuffers[0].flip();
                String str1 = new String(byteBuffers[0].array(), CharsetUtil.UTF_8);
                if (str1.length() == 0) {
                    // 取消注册
                    key.cancel();
                    socketChannel.close();
                }*/
            } catch (IOException e) {
                // 取消注册
                key.cancel();
                try {
                    System.out.printf("用户@%s下线了!", socketChannel.getRemoteAddress());
                    System.out.println();
                    socketChannel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

    }
}

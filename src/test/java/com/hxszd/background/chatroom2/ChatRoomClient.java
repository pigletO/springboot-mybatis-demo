package com.hxszd.background.chatroom2;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @description: 聊天室客户端
 * @author: pig1etO
 * @create: 2020-06-18 18:48
 **/
public class ChatRoomClient {

    private static Selector selector;

    public static void main(String[] args) {
        // 建立Socket连接
        SocketChannel socketChannel = buildConnection();
        // 开启线程监听服务端发送的数据
        listener();
        // 发送消息
        sendMessage(socketChannel);
    }

    private static SocketChannel buildConnection() {
        try {
            // 开启一个通道
            SocketChannel socketChannel = SocketChannel.open();
            // 设置非阻塞连接
            socketChannel.configureBlocking(false);
            // 设置连接服务器地址
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 55533));

            // 准备多路复用器 大管家
            selector = Selector.open();

            // 将当前通道注册到多路复用器
            socketChannel.register(selector, SelectionKey.OP_READ);

            // 确保连接正确建立
            while (!socketChannel.finishConnect()) {
                System.out.println("Connecting...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return socketChannel;
        } catch (IOException e) {
            System.out.println("连接聊天服务器失败!");
            return null;
        }
    }

    private static void listener() {
        // 新启动一个线程用来接收服务端消息
        new Thread(() -> {
            System.out.println("waiting for message from server...");
            while (true) {
                try {
                    if (selector.select(1000) > 0) {

                        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();

                            if (selectionKey.isReadable()) {
                                SocketChannel readAbleChannel = (SocketChannel) selectionKey.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                                int count = readAbleChannel.read(byteBuffer);
                                if (count > 0) {
                                    System.out.printf("从%s读取到信息 " + new String(byteBuffer.array(), CharsetUtil.UTF_8), readAbleChannel.getRemoteAddress());
                                    System.out.println();
                                }
                            }
                            // 必不可少需要删除已经被轮询过的key
                            iterator.remove();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }).start();
    }

    private static void sendMessage(SocketChannel socketChannel) {
        try {
            // 主线程轮询监听控制台输入
            System.out.println("Please~! " + socketChannel.getLocalAddress());
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String keyboard = scanner.next();
                socketChannel.write(ByteBuffer.wrap(keyboard.getBytes(CharsetUtil.UTF_8)));
                System.out.println("Send this :");
                System.out.println(keyboard);
            }
        } catch (IOException e) {
            System.out.println("客户端异常！");
        }
    }
}

package com.hxszd.background;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @description: NIO聊天室客户端
 * @author: pig1etO
 * @create: 2020-06-18 18:48
 **/
public class ChatRoomClient {

    private static Selector selector;

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 55533));

            selector = Selector.open();

            socketChannel.register(selector, SelectionKey.OP_READ);

            while (!socketChannel.finishConnect()) {
                System.out.println("Connecting...");
            }

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
                                iterator.remove();
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }).start();

            System.out.println("Please~! " + socketChannel.getLocalAddress());
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String keyboard = scanner.next();
                socketChannel.write(ByteBuffer.wrap(keyboard.getBytes(CharsetUtil.UTF_8)));
                System.out.println("Send this :");
                System.out.println(keyboard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

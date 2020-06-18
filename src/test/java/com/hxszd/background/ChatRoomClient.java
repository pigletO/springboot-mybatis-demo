package com.hxszd.background;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @description: 聊天室客户端
 * @author: pig1etO
 * @create: 2020-06-18 18:48
 **/
public class ChatRoomClient {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 55533));

            while (!socketChannel.finishConnect()) {
                System.out.println("Connecting...");
            }
            System.out.println("Please~!");
            Scanner scanner = new Scanner(System.in);
            String keyboard = scanner.next();
            socketChannel.write(ByteBuffer.wrap(keyboard.getBytes(CharsetUtil.UTF_8)));
            System.out.println("Send this :");
            System.out.println(keyboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

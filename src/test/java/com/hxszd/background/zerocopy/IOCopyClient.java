package com.hxszd.background.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-24 17:17
 **/
public class IOCopyClient {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.bind(new InetSocketAddress("127.0.0.1", 55533));
            //DataInputStream dataInputStream = new DataInputStream("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

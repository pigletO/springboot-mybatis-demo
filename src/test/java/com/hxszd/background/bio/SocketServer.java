package com.hxszd.background.bio;

import com.alibaba.fastjson.JSONObject;
import com.hxszd.background.pojo.dto.common.ResponseResult;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-25 18:17
 **/
public class SocketServer {

    public static void main(String[] args) {
        // BIO
        ServerSocket serverSocket = null;

        try {
            // BIO
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1", 55533));

            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler(socket);
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void handler(Socket socket) {

        try {
            InputStream in = socket.getInputStream();
            byte[] b = new byte[1024];
            while (true) {
                if (in.read(b) == -1) {
                    break;
                }
            }
            String s1 = new String(b, "UTF-8");
            System.out.println(s1);

            ResponseResult result = JSONObject.parseObject(s1, ResponseResult.class);
            socket.getOutputStream().write(JSONObject.toJSONString(ResponseResult.Ok()).getBytes(CharsetUtil.UTF_8));
            socket.shutdownOutput();

        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

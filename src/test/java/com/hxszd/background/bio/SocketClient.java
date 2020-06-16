package com.hxszd.background.bio;

import com.alibaba.fastjson.JSONObject;
import com.hxszd.background.pojo.dto.common.ResponseResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-14 18:25
 **/
public class SocketClient {

    public static void main(String args[]) throws Exception {
        client2();

    }

    private static void client1() {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            for (int i = 0; i < 10; i++) {
                // 要连接的服务端IP地址和端口
                String host = "127.0.0.1";
                int port = 55533;
                // 与服务端建立连接
                socket = new Socket(host, port);
                // 建立连接后获得输出流
                outputStream = socket.getOutputStream();

                String message = "你好  yiwangzhibujian";
                socket.getOutputStream().write(JSONObject.toJSONString(ResponseResult.Ok(i)).getBytes("UTF-8"));
                socket.shutdownOutput();
                byte[] bytes = new byte[1024];
                InputStream inputStream = socket.getInputStream();
                while (inputStream.read(bytes) != -1) {
                }
                System.out.println(new java.lang.String(bytes, "UTF-8"));
                socket.shutdownInput();
            }

        } catch (Exception e) {

        } finally {
            try {
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void client2() {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
                // 要连接的服务端IP地址和端口
                String host = "127.0.0.1";
                int port = 55533;
                // 与服务端建立连接
                socket = new Socket(host, port);
                // 建立连接后获得输出流
                outputStream = socket.getOutputStream();

                String message = "你好  yiwangzhibujian";
                socket.getOutputStream().write(JSONObject.toJSONString(ResponseResult.Ok(1)).getBytes("UTF-8"));
                socket.shutdownOutput();

                byte[] bytes = new byte[1024];
                InputStream inputStream = socket.getInputStream();
                while (inputStream.read(bytes) != -1) {
                }
                System.out.println(new java.lang.String(bytes, "UTF-8"));
                socket.shutdownInput();

        } catch (Exception e) {

        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

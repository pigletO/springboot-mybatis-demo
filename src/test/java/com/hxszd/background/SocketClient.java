package com.hxszd.background;

import com.alibaba.fastjson.JSONObject;
import com.hxszd.background.pojo.dto.common.ResponseResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-14 18:25
 **/
public class SocketClient {

    public static void main(String args[]) throws Exception {
        for (int i = 0; i < 10; i++) {
            // 要连接的服务端IP地址和端口
            String host = "127.0.0.1";
            int port = 55533;
            // 与服务端建立连接
            Socket socket = new Socket(host, port);
            // 建立连接后获得输出流
            OutputStream outputStream = socket.getOutputStream();

            String message="你好  yiwangzhibujian";
            socket.getOutputStream().write(JSONObject.toJSONString(ResponseResult.Ok(i)).getBytes("UTF-8"));
            outputStream.close();
            socket.close();
        }
    }
}

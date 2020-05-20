package com.hxszd.background;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-14 16:50
 **/
public class test {

    public static void main(String[] args) throws IOException {
        /*String filePath = "H:\\a.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
        //BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        StringBuilder sb = new StringBuilder("");
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + ",");
        }
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());*/

//        // 1 true
//        float a = 0.125f; double b = 0.125d;
//        System.out.println((a - b) == 0.0);


//        // 2 false
//        double c = 0.8; double d = 0.7; double e = 0.6;
//        System.out.println((c-d) == (d-e));

        // 3 Infinity
//        System.out.println(1.0 / 0);

        // 4 NaN
//        System.out.println(0.0 / 0.0);

        // 5 >> 和 >>> 的区别是？ D
//        A. 任何整数没有区别
//        B. 负整数一定没有区别
//        C. 浮点数可以 >> 运算，但是不可以 >>> 运算
//        D. 正整数一定没有区别
        // 6 D java会根据类型匹配 String和Integer都可以为null 编译会报错
//        某个类有两个重载方法：void f(String s) 和 void f(Integer i)，那么 f(null) 的会调用哪个方法？
//
//        A. 前者
//        B. 后者
//        C. 随机调用
//        D. 编译出错

        // 7 A 跟上题一样，java根据类型匹配，1会被自动转换为基本数据类型，并进行自动匹配，1可以被转换为int double long 如果new Integer(1)则调用
//        某个类有两个重载方法：void g(double d) 和 void g(Integer i)，那么 g(1) 的会调用哪个方法？
//
//        A. 前者
//        B. 后者
//        C. 随机调用
//        D. 编译出错


       /* UnderWayOrder order = new UnderWayOrder();
        order.mothod(new Integer(1));


        String a = null;*/

        InputStream inputStream = null;
        ServerSocket server = null;
        Socket socket = null;

       try {
           // 监听指定的端口
           int port = 55533;
           server = new ServerSocket(port);

           while (true) {

               // server将一直等待连接的到来
               System.out.println("server将一直等待连接的到来");
               socket = server.accept();
               // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
               inputStream = socket.getInputStream();
               byte[] bytes = new byte[1024];
               int len;
               StringBuilder sb = new StringBuilder();
               while ((len = inputStream.read(bytes)) != -1) {
                   //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                   sb.append(new String(bytes, 0, len, "UTF-8"));
               }
               System.out.println("get message from client: " + sb);
           }
       }finally {
           inputStream.close();
           socket.close();
           server.close();
       }

    }
}

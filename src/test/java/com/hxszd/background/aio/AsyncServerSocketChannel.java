package com.hxszd.background.aio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: AIO 非阻塞IO模型，需要事先设定好accept建立连接后的handler钩子方法，和连接发生read时的handler钩子方法，
 *              核心思想是事先做好处理准备，等待监听的事件触发，自动执行handler，类似观察者模式，观察到了accept或read事件后触发特定方法去处理连接请求
 * @author: pig1etO
 * @create: 2020-06-04 20:03
 **/
public class AsyncServerSocketChannel {

    public static void main(String[] args) throws IOException {
        CountDownLatch count = new CountDownLatch(1);

        // 创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executor ,1);

        // 开启多线程模型使用AIO
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(threadGroup).bind(new InetSocketAddress("127.0.0.1", 55533));

        // AIO是非阻塞IO模型  执行accept方法会直接跳过 不进行阻塞
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            /**
             * 成功之后执行以下业务逻辑
             * @param client
             * @param attachment
             */
            @Override
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                // 特别注意，这里需要先执行accept方法，否则后面的连接会进不来
                serverSocketChannel.accept();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                // AsynchronousSocketChannel使用类似BIO中的Socket NIO中的SocketChannel
                client.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {

                    /**
                     * 读取成功调用
                     * @param result
                     * @param attachment
                     */
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {

                        attachment.flip();
                        try {
                            System.out.println(new String(attachment.array(), "UTF-8"));
                            client.write(ByteBuffer.wrap("accept and read success!".getBytes("UTF-8")));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    /**
                     * 同样，读取失败调用
                     * @param exc
                     * @param attachment
                     */
                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        System.out.println("read error!");
                        try {
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            /**
             * 失败自动调用
             * @param exc
             * @param attachment
             */
            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("accept error!");
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

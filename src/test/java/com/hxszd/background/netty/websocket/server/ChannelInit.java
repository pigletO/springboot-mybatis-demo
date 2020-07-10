package com.hxszd.background.netty.websocket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.catalina.Pipeline;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-08 17:46
 **/
public class ChannelInit extends ChannelInitializer<SocketChannel> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        channels.add(ch.pipeline().channel());

        ChannelPipeline pipeline = ch.pipeline();
        // http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 分块写
        pipeline.addLast(new ChunkedWriteHandler());

        // http传递数据如果较大时，会分段多次连接发送数据，此为聚合处理器
        pipeline.addLast(new HttpObjectAggregator(8192));

        // websocket是以帧(frame)的形式传递数据
        // 将数据解析为websocketFrame下有六个子类
        // 将http协议升级为websocket协议
        // 指定服务端websocket地址 ws://localhost:55533/xxxx
        pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

        pipeline.addLast(new IdleStateHandler(5, 10, 15, TimeUnit.SECONDS));

        pipeline.addLast(new ChannelInBoundHandler());
    }
}

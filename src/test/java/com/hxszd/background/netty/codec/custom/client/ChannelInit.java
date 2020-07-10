package com.hxszd.background.netty.codec.custom.client;

import com.hxszd.background.netty.codec.custom.decode.CustomLongDecoder;
import com.hxszd.background.netty.codec.custom.encode.CustomLongEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 19:00
 **/
public class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        // 编码器只会对指定类型的数据进行编码，如这里自定义了一个LongEncoder，则只会对Long进行编码，若没有进行编码处理，则默认为ByteBuf类型，我擦netty默认在ChannelPipeline中添加了ByteBuf的编解码器
        // ChannelHandler也跟编码器处理风格类似，如果处理器指定了处理的数据类型，则只会对特定类型的数据进行处理，ChannelInBoundHandlerAdapter则处理所有入站数据
        ch.pipeline().addLast(new CustomLongEncoder())
                // .addLast(new CustomLongDecoder())
                .addLast(new StringEncoder())
                .addLast(new ChannelInBoundHandler());
    }
}

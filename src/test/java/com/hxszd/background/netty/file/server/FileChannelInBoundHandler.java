package com.hxszd.background.netty.file.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-05 11:17
 **/
public class FileChannelInBoundHandler extends SimpleChannelInboundHandler<ByteBuf> {

    int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        byte[] bytes = new byte[1024];
        msg.readBytes(bytes);

        System.out.println(msg.toString(CharsetUtil.UTF_8));

        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\aaa.txt", "rw");

        //FileChannel channel = randomAccessFile.getChannel();

        randomAccessFile.write(bytes);

        randomAccessFile.close();

        /*channel.write(msg.nioBuffer());
        channel.close();*/




        /*byte[] bytes = new byte[msg.readableBytes()];

        msg.readBytes(bytes);
        System.out.println();*/
        /*System.out.println(new String(msg.toString(CharsetUtil.UTF_8)));

        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端消息", CharsetUtil.UTF_8));

        System.out.println("服务端收到消息数量" + ++count);*/
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("断开连接");
        ctx.disconnect();
    }
}

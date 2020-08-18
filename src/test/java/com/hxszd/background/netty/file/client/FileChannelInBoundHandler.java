package com.hxszd.background.netty.file.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.io.RandomAccessFile;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-05 15:52
 **/
public class FileChannelInBoundHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        System.out.println(msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        RandomAccessFile file = new RandomAccessFile("F:\\program files\\文件.txt", "r");

        byte[] bytes = new byte[1024];

        int i = 0;
        /*while (file.read(bytes) != -1) {
            System.out.println("发送数据包" + i++);
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(bytes));
        }*/
        file.read(bytes);
        file.close();
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(bytes));
        System.out.println("发送数据包");


        /*// TCP粘包拆包之大数据块拆分成一个个小包发送
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("客户端消息" + i);
        }
        ByteBuf byteBuf = Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("与服务器断开连接" + cause);
        ctx.disconnect();
    }
}

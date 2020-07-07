package com.hxszd.background.netty.chatroom3.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 19:02
 **/
public class ChannelInBoundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.print("【客户端】" + ctx.channel().remoteAddress() + "说：");
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO 客户端怎么做到非阻塞读取消息呢？
        Scanner scanner = new Scanner(System.in);
        System.out.println("【客户端】请输入聊天信息");
        if (scanner.hasNext()) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(scanner.next().getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(byteBuf);
        }
    }
}

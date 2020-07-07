package com.hxszd.background.netty.chatroom3.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @description: channel入站处理类
 *  S->C为出站操作，C->S为入站操作，入站处理客户端发来的数据
 * @author: pig1etO
 * @create: 2020-07-07 14:46
 **/
public class ChannelInBoundHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据
     * @param ctx 双向链表 存储处理对象方法
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("【服务器】收到来自‘" + ctx.channel().remoteAddress() + "’的消息");
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("消息内容 ：" + byteBuf.toString(CharsetUtil.UTF_8));

        // 发送消息给其他客户端
        ChannelInit.socketChannelList.stream().forEach(channel -> {
            if (channel != ctx.channel()) {
                System.out.println("【服务器】消息转发给" + channel.remoteAddress());
                channel.writeAndFlush(msg);
            }
        });
    }

    /**
     * channelRead处理结束之后执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    /**
     * 异常执行
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ChannelInit.socketChannelList.remove(ctx.channel());
        System.out.println("【"+ ctx.channel().remoteAddress() + "】下线！");
    }
}
